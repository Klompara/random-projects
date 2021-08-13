package app;

import util.*;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reader reader = null;
		Writer writer = null;
		Parser parser = null;

		try {
			System.out.println("Start of compiling: ");
			reader = new Reader("res/filetocompile.txt");
			writer = new Writer("res/generatedfile.txt");
			parser = new Parser(reader, writer);
			parser.execute();
			System.out.println("End of compiling");
		} catch (Exception ex) {
			System.out.println("error in compiler: " + ex.getMessage());
		}

	}

}
