import java.util.Random;


public class Main {
	private Random r;
	public int max = 0;
	public static void main(String args[]){
		new Main();
	}
	private Main(){
		for(int i = 0; i < 2147483647; i++){
			r = new Random();
			int random = r.nextInt((6 - 1) + 1) + 1;
			int Zähler = 0;
			while(random != 6){
				Zähler++;
				random = r.nextInt((6 - 1) + 1) + 1;
			}
	
			if(Zähler > max){
				max = Zähler;
				System.out.println("versuche: " + max);
			}
		}
	}
}
