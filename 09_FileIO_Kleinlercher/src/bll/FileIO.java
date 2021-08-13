package bll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class FileIO {
	
	private Set<Buch> booksSorted = new TreeSet<Buch>();
	
	public FileIO() {}
	
	public void readFile(Path path) {
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(path.toString()));
			String l;
			Boolean firstLine = true;
			while ((l = inputStream.readLine()) != null) {
				if(!firstLine) {
					String[] data = l.split(";");
					data[3] = data[3].replace(",", ".");
					Buch b = new Buch(data[0], data[1], data[2], Double.parseDouble(data[3]), data[4], data[5], Integer.parseInt(data[6]));
					booksSorted.add(b);
				}else{
					firstLine = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try { inputStream.close(); } catch (IOException e) { e.printStackTrace(); }
		}
		
	}
	
	public void writeFile(Path path) {
		PrintWriter outputStream = null;
		String salePriceWithoutComma = "";
		try {
			outputStream = new PrintWriter(new FileWriter(path.toString()));
			Iterator<Buch> it = booksSorted.iterator();
			outputStream.write("ISBN;Title;Publisher;Sale Price;Author Lastname;Author Firstname;Amount\n");
			while(it.hasNext()) {
				Buch b = it.next();
				salePriceWithoutComma = b.getSalePrice() + ""; // + "€";
				salePriceWithoutComma = salePriceWithoutComma.replace(".", ",");
				outputStream.write(b.getISBN()+";"+b.getTitle()+";"+b.getPublisher()+";"+salePriceWithoutComma+";"+b.getAuthorLastName()+";"+b.getAuthorFirstName()+";"+b.getAmount()+"\n");
				System.out.println(b.toString());
			}
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
