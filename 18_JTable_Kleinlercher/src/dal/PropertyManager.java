package dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	private static PropertyManager instance = null;
	private String configFile = "./res/properties.config";
	private Properties props = new Properties();

	public static PropertyManager getInstance() {
		if (PropertyManager.instance == null) {
			instance = new PropertyManager();
			try {
				instance.fillProperties();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	private void fillProperties() throws FileNotFoundException, IOException {
		try (FileReader reader = new FileReader(this.configFile)) {
			this.props.load(reader);
		}
	}

	public String readProperty(String name) {
		return this.props.getProperty(name);
	}

	public boolean configFileExists() {
		File f = new File(configFile);
		return (f.exists() && !f.isDirectory());
	}

	public void writeProperty(String key, String value) throws IOException {
		this.props.setProperty(key, value);
		this.writeToFile();
	}

	private void writeToFile() throws IOException {
		try (FileWriter writer = new FileWriter(this.configFile)) {
			props.store(writer, "Properties for DAL");
		}
	}

}
