package RandFun;

import javax.swing.JFrame;


public class Würfel {
	public int max = 0;
	
	public Würfel(){
		JFrame frame = new JFrame();
		frame.setSize(600, 0);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		for(int i2 = 0; i2 < 2147483647; i2++){
			for(int i = 0; i < 2147483647; i++){
				int random = rand(6, 1);
				int Zähler = 0;
				while(random != 6){
					Zähler++;
					random = rand(6, 1);
				}
		
				if(Zähler > max){
					max = Zähler;
					System.out.println(max + " Versuche eine 6 zu würfeln. Anzatz: "+(i+i2));
				}
				frame.setTitle("Anzatz: "+(i + i2));
			}
		}
	}
	
	public int rand(int max, int min) {
		int range = (max - min) +1;
		return (int) ((Math.random() * range) + min);
	}
	
	public static void main(String args[]){ new Würfel(); }
}
