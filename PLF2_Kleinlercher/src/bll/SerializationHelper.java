package bll;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationHelper {
	
	public static void Serializable( Mitarbeiter m, String filename) throws IOException, ClassNotFoundException{
		filename = m.getNachname()+".bin";
		try( FileOutputStream fos = new FileOutputStream( filename )){
			ObjectOutputStream oos = new ObjectOutputStream( fos ); 
			oos.writeObject(m);
			oos.flush(); 
		}
	}	

}
