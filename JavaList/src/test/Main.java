package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.JFileChooser;


public class Main {
	private static String path;
	LinkedList<String> liste = new LinkedList<String>();
	public Main(){
		BufferedReader ReadIn;
		try {
			ReadIn = new BufferedReader
			        (new FileReader(path));
        	while (true) {
        		String Container = ReadIn.readLine();
        		if (Container == null) 
        			break;
        		liste.add(Container);
        	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	     Collections.sort(liste, new Comparator<String>() {
	         public int compare(String o1, String o2) {
	             return Collator.getInstance().compare(o1, o2);
	         }
	     });
		
		
		try {
			PrintWriter writer;
			writer = new PrintWriter(path + "_SORTED.txt");
			for(String name : liste)
				writer.println(name);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
		path = chooser.getSelectedFile().getAbsolutePath();
		new Main();
	}
}
