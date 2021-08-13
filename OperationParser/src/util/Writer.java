package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	private BufferedWriter bw;
	private String fileName = "";
	private static final String key = "Wert: ";
	private static final String error = "Error: ";

	public Writer(String fileName) throws IOException {
		this.fileName = fileName;
		this.initialize();
	}

	private void initialize() throws IOException {
		this.bw = new BufferedWriter(new FileWriter(this.fileName));
	}

	public void writeStringResult(String result) throws IOException {
		this.bw.write(key);
		this.bw.write(result);
		this.bw.newLine();

	}

	public void writeStringError(String error) throws IOException {
		this.bw.write(Writer.error);
		this.bw.write(error);
		this.bw.newLine();
	}

	public void close() throws IOException {
		if (this.bw != null) {
			this.bw.flush();
			this.bw.close();
		}
	}

}
