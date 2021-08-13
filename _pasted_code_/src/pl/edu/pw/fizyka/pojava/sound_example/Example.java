package pl.edu.pw.fizyka.pojava.sound_example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteBuffer;

public class Example {

	/**
	 * Beeps. Currently half-assumes that the format the system expects is
	 * "PCM_SIGNED unknown sample rate, 16 bit, stereo, 4 bytes/frame, big-endian"
	 * I don't know what to do about the sample rate. Using 11025, since that
	 * seems to be right, by testing against A440. I also can't figure out why I
	 * had to *4 the duration. Also, there's up to about a 100 ms delay before
	 * the sound starts playing.
	 * 
	 * @param freq
	 * @param millis
	 */

	public static void beep(double freq, final double millis) throws InterruptedException, LineUnavailableException {
		final Clip clip = AudioSystem.getClip();
		AudioFormat af = clip.getFormat();
		if (af.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
			throw new UnsupportedOperationException("Unknown encoding");
		}

		if (af.getSampleSizeInBits() != 16) {
			System.err.println("Weird sample size.  Dunno what to do with it.");
			return;
		}
		int bytesPerFrame = af.getFrameSize();
		double fps = af.getFrameRate();
		int frames = (int) (fps * (millis / 1000));
		ByteBuffer data = ByteBuffer.allocate(frames * bytesPerFrame);
		double freqFactor = (Math.PI / 2) * freq / fps;
		double ampFactor = Short.MAX_VALUE;
		short sample;
		for (int frame = 0; frame < frames; frame++) {
			sample = (short) (ampFactor * Math.sin(frame * freqFactor));
			data.putShort(sample);
		}
		clip.open(af, data.array(), 0, data.position());
		clip.addLineListener(new LineListener() {
			@Override
			public void update(LineEvent event) {
				if (event.getType() == LineEvent.Type.START) {
					Timer t = new Timer((int) millis + 1, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							clip.close();
						}
					});
					t.setRepeats(false);
					t.start();
				}
			}
		});
		clip.start();

		Thread.sleep((long) millis);
	}

	public static void main(String[] args) throws Exception {
		beep(2000, 50);
	}
}