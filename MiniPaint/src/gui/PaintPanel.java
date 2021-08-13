package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private Color color = null;
	private boolean straight = false;
	private int xCoordinate = -1;
	private int yCoordinate = -1;
	private int size = 1;
	private BufferedImage bimage;

	public PaintPanel() {
		bimage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		this.color = Color.black;
		this.setBackground(Color.white);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isStraight() {
		return straight;
	}

	public void setStraight(boolean straight) {
		this.straight = straight;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.straight) {
			this.xCoordinate = e.getX();
			this.yCoordinate = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Graphics2D g = (Graphics2D) bimage.getGraphics();
		if (this.straight) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(color);
			g.setStroke(new BasicStroke(size));
			g.drawLine(xCoordinate, yCoordinate, e.getX(), e.getY());
		}
		xCoordinate = -1;
		yCoordinate = -1;
		drawBufferedImage();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Graphics2D g = (Graphics2D) bimage.getGraphics();
		if (!this.straight) {
			if (this.xCoordinate != -1 && this.yCoordinate != -1) {
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setColor(color);
				g.setStroke(new BasicStroke(size));
				g.drawLine(xCoordinate, yCoordinate, e.getX(), e.getY());
			}
			this.xCoordinate = e.getX();
			this.yCoordinate = e.getY();
		}
		drawBufferedImage();
	}

	public void mouseMoved(MouseEvent arg0) {
	}

	public void setSize(int size) {
		this.size = size;
	}

	private void drawBufferedImage() {
		Graphics g = this.getGraphics();
		g.drawImage(bimage, 0, 0, bimage.getWidth(), bimage.getHeight(), null);
	}

	public void saveImage() {
		File outputfile = chooseFile();
		System.out.println("Write File to: " + outputfile.getAbsolutePath());
		try {
			ImageIO.write(bimage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadImage() {
		this.repaint();
		bimage = chooseImage();
		drawBufferedImage();
	}

	private File chooseFile() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Please choose an image...");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png", "bmp", "gif");
		fc.addChoosableFileFilter(filter);
		File selectedFile = null;
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			selectedFile = fc.getSelectedFile();
		}
		return selectedFile;
	}

	private BufferedImage chooseImage() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Please choose an image...");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png", "bmp", "gif");
		fc.addChoosableFileFilter(filter);
		BufferedImage origImage = null;
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			try {
				origImage = ImageIO.read(selectedFile);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return origImage;
	}

	public void clearImage() {
		this.repaint();
		bimage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		drawBufferedImage();
	}
}
