package junittest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

import org.junit.Test;

public class testFiles {
	
	@Test
	public void testWrittenFiles() {
		String separator = FileSystems.getDefault().getSeparator();
		BufferedReader inputStream = null;
		int linecounter = 0;
		try {
			inputStream = new BufferedReader(new FileReader("." + separator + "res" + separator + "Umsatzauswertung.csv"));
			while (inputStream.readLine() != null) {
				linecounter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try { inputStream.close(); } catch (IOException e) { e.printStackTrace(); }
		}
		assertEquals(7, linecounter);
	}

}
