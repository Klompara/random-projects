package RandFun;

public class WürfelFun {
	private int[] wuerfel = new int[6];
	
	
	public WürfelFun() {
		for(int i = 0; i < 6; i++){
			wuerfel[i] = 0;
		}
		
		for(int i = 0; i < 100; i++) {
			for(int i2 = 0; i2 < 6; i2++){
				wuerfel[i2] = rand(6, 1);
			}
			for(int i2 = 0; i2 < 6; i2++){
				//if(wuerfel[i2] ==)
			}
		}
	}
	
	public int rand(int max, int min) {
		int range = (max - min) +1;
		return (int) ((Math.random() * range) + min);
	}
	
	public static void main(String[] args) { new WürfelFun(); }
}
