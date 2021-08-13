
public class Main {

	public static void main(String[] args) {
		int genauigkeit = 999999999;
		double divident = 2;
		double pi = 3;
		double factor;
		for(int i = 0; i < genauigkeit; i++) {
			factor = 4/(divident*(divident+1)*(divident+2));
			//System.out.println(factor);
			if(i%2 == 0) {
				pi += factor;
			} else {
				pi -= factor;
			}
			divident+=2;
			//System.out.println(pi);
		}
		System.out.println("Wirkliches Pi: "+Math.PI);
		System.out.println("Gerechnet Pi:  "+pi);
	}

}
