package app;

public class Ping {
	
	public Ping() {
		for(int i = 0; i < 26; i ++) {
			System.out.println("E" + (char) (65+i) + "win");
		}
	}
	
	public static void main(String[] args) {
		new Ping();
		/*
		for(int i = 0; i < 1000; i++) {
			try {
				System.out.println(Runtime.getRuntime().exec("ping RENE-LAPTOP"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
	}

}
