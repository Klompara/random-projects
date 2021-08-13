package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Main {
	
	
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("C:/"));
		int Checker = chooser.showOpenDialog(null);
		String path = null;
		if(Checker == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getAbsolutePath();
			System.out.println(path);
		} else {
			System.out.println("Du hast abgebrochen");
			System.exit(0);
		}
		
		BufferedImage image = new BufferedImage(1024, 1920, BufferedImage.TYPE_INT_ARGB);
		try {
			ArrayList<Integer> ifPositions = new ArrayList<Integer>();
			
			Graphics2D g = (Graphics2D) image.getGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setColor(Color.white);
			g.fillRect(0, 0, 1024, 1920);
			g.setColor(Color.black);
			g.setStroke(new BasicStroke(3f));
			//g.drawLine(5, 5, 1024-5, 5);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			
			FileReader fReader = new FileReader(new File(path));
			BufferedReader reader = new BufferedReader(fReader);
			String line = reader.readLine();
			int lines = 2;
			boolean startDrawing = false, drawLine;
			while(line != null) {
				line = line.replaceAll("\t", "");
				System.out.println(line);
				
				drawLine = true;
				if(line.startsWith("//")) {
					drawLine = false;
				}else if(line.startsWith("}")) {
					drawLine = false;
					g.drawLine(5, (lines-1)*20, 1024-5, (lines-1)*20);
				}else if(line.startsWith("{")) {
					drawLine = false;
					g.drawLine(5, (lines-1)*20, 1024-5, (lines-1)*20);
				}
				
				if(startDrawing && drawLine) {

					if(line.startsWith("if")) {
						g.drawString(line, (int) (1024/2-g.getFontMetrics().getStringBounds(line, g).getWidth()/2), lines*20);
						g.drawString("J", 10, lines*20);
						g.drawString("N", 1024-20, lines*20);
						
						g.drawLine(5, (lines-1)*20, 1024-5, (lines-1)*20);
						g.drawLine(5, (lines-1)*20, 5, (lines+1)*20);
						g.drawLine(5, (lines-1)*20, 1024/2, (lines+1)*20);
						g.drawLine((1024)/2, (lines+1)*20, 1024-5, (lines-1)*20);
						g.drawLine(5, (lines+1)*20, 1024-5, (lines+1)*20);
						lines+=2;
						ifPositions.add(lines);
					} else if(line.startsWith("else")) {
						lines = ifPositions.get(ifPositions.size()-1);
						ifPositions.remove(ifPositions.size()-1);
					} else {
						g.drawString(line, 10, lines*20);
						g.drawLine(5, (lines-1)*20, 5, lines*20);
						lines++;
					}
				}
				if(line.startsWith("int main()")) {
					startDrawing = true;
				}

				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    File outputfile = new File("C:\\Users\\Michael\\Desktop\\struktogramm1.png");
	    try {
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
