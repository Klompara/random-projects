#version 400 core

in vec4 clipSpace;
in vec3 toCameraVector;
in vec3 fromLightVector;
in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D refractionTexture;
uniform sampler2D reflectionTexture;
uniform sampler2D dudvMap;
uniform sampler2D depthMap;
uniform sampler2D normalMap;
uniform vec3 lightColor;
uniform float moveFactor;

const float waveStrength = 0.02;
const float shineDamper = 20;
const float reflectivity = 0.6;

void main(void) {

	vec2 ndc = (clipSpace.xy/clipSpace.w) / 2.0 + 0.5;
	vec2 refractionCoords = vec2(ndc.x, ndc.y);
	vec2 reflectionCoords = vec2(ndc.x, -ndc.y);
	
	float near = 0.1;
	float far = 1000;
	float depth = texture(depthMap, refractionCoords).r;
	float floorDistance = 2.0 * near * far / (far + near - (2.0 * depth - 1.0) * (far - near));
	
	depth = gl_FragCoord.z;
	float waterDistance = 2.0 * near * far / (far + near - (2.0 * depth - 1.0) * (far - near));
	float waterDepth = floorDistance - waterDistance;
	
	vec2 distortedTexCoords = texture(dudvMap, vec2(textureCoords.x + moveFactor, textureCoords.y)).rg*0.1;
	distortedTexCoords = textureCoords + vec2(distortedTexCoords.x, distortedTexCoords.y+moveFactor);
	vec2 totalDistort = (texture(dudvMap, distortedTexCoords).rg * 2.0 - 1.0) * waveStrength;
	
	refractionCoords += totalDistort;
	refractionCoords = clamp(refractionCoords, 0.001, 0.999);
	
	reflectionCoords += totalDistort;
	reflectionCoords.x = clamp(reflectionCoords.x, 0.001, 0.999);
	reflectionCoords.y = clamp(reflectionCoords.y, -0.999, 0.001);

	vec4 refractColor = texture(refractionTexture, refractionCoords);
	vec4 reflectColor = texture(reflectionTexture, reflectionCoords);
	
	vec3 viewVector = normalize(toCameraVector);
	float refractiveFactor = dot(viewVector, vec3(0.0, 1.0, 0.0));
	refractiveFactor = pow(refractiveFactor, 1);
	
	vec4 normalMapColor = texture(normalMap, totalDistort);
	vec3 normal = vec3(normalMapColor.r * 2.0 - 1.0, normalMapColor.g, normalMapColor.b * 2.0 - 1.0);
	normal = normalize(normal);
	
	vec3 reflectedLight = reflect(normalize(fromLightVector), normal);
	float specular = max(dot(reflectedLight, viewVector), 0.0);
	specular = pow(specular, shineDamper);
	vec3 specularHighlights = lightColor * specular * reflectivity;

	out_Color = mix(reflectColor, refractColor, refractiveFactor);
	out_Color = mix(out_Color, vec4(0.0, 0.3, 0.5, 1.0), 0.2) + vec4(specularHighlights, 0.0);
	out_Color.a = clamp(waterDepth, 0, 1);
}