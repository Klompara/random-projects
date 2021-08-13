package app;

import gui.MainFrame;

public class BlockBreaker {

	public static boolean RUNNING;
	
	public BlockBreaker() {
		MainFrame frame = new MainFrame();
		frame.setUpFrame(new Thread());
	}
	
	public static void main(String[] args) { new BlockBreaker(); }
}
