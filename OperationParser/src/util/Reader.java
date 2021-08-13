package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	private String filename = "";
	private BufferedReader br;

	public Reader(String filename) throws FileNotFoundException {
		this.filename = filename;
		this.initialize();
	}

	public void initialize() throws FileNotFoundException {
		this.br = new BufferedReader(new FileReader(this.filename));
	}

	public void close() throws IOException {
		if (this.br != null) {
			this.br.close();
		}
	}

	public int readNextChar() throws IOException {
		int i = br.read();
		return i;
	}
}
