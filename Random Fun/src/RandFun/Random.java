package RandFun;



public class Random {
	private int[] anzahl = new int[6];
	
	public Random() {
		for(int i = 0; i < 6; i++){
			anzahl[i] = 0;
		}
		for(int i = 0; i < 100; i++) {
			int rand = rand(6, 1);
			
			if(rand == 1) anzahl[0]++;
			else if(rand == 2) anzahl[1]++;
			else if(rand == 3) anzahl[2]++;
			else if(rand == 4) anzahl[3]++;
			else if(rand == 5) anzahl[4]++;
			else if(rand == 6) anzahl[5]++;
			else{
				System.err.println("FEHLER" + rand);
			}
		}
		for(int i = 0; i < 6; i++) {
			System.out.println(i+": "+anzahl[i]);
		}
	}
	
	public int rand(int max, int min) {
		int range = (max - min) +1;
		return (int) ((Math.random() * range) + min);
	}
	
	public static void main(String[] args) {
		new Random();
	}
}
