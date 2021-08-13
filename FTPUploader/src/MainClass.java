import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;


public class MainClass {
	public static void main(String[] args) {
		String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
		String host = "riptosscreencorn.bplaced.net";
		String user = "riptosscreencorn_screenuploader";
		String pass = "passwort";
		String filePath = "C:\\Users\\Michael\\Desktop\\test.txt";
		String uploadPath = "screenshot" + new Random().nextInt(500) + ".bmp";

		ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
		try {
		    URL url = new URL(ftpUrl);
		    System.out.println(url);
		    URLConnection conn = url.openConnection();
		    OutputStream outputStream = conn.getOutputStream();
		    FileInputStream inputStream = new FileInputStream(filePath);
		    
		    byte[] buffer = new byte[1024];
		    int bytesRead = -1;
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        outputStream.write(buffer, 0, bytesRead);
		    }

		    inputStream.close();
		    outputStream.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
}
