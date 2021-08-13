package com.Game.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends Canvas{
	private static final long serialVersionUID = 5320084688065457496L;
	
	private BufferedImage icon;
	
	public Window(int width, int height, String title, Game game){
		try{
			icon = ImageIO.read(getClass().getResourceAsStream("/icon.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame(title);
		
		frame.setIconImage(icon);
		
		frame.setPreferredSize(new Dimension(width, height ));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

}
