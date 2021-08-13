package app;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import bll.FileIO;

public class Start {

	public static void main(String[] args) {
		String separator = FileSystems.getDefault().getSeparator();
		Path file = Paths.get("."+separator+"res"+separator+"library.csv");
		Path writeFile = Paths.get("."+separator+"data"+separator+"newData.csv");
		FileIO fio = new FileIO();
		fio.readFile(file);
		fio.writeFile(writeFile);
	}

}
