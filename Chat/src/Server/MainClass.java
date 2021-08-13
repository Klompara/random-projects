package Server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class MainClass {
	
	private JFrame frame;
	private JPanel panel;
	private JTextArea text_connectedUsers;
	private JScrollPane scrollPane_connectedUsers;
	
	private DatagramSocket socket;
	private int port;
	private ArrayList<UserData> users = new ArrayList<UserData>();
	
	public MainClass() {
		loadConfig();
		setUpGui();
		setUpSocket();
		receiveData();
	}

	private void receiveData() {
		boolean running = true;
		while(running) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData(), 0, packet.getLength());
			System.out.println(message);
			if(message.startsWith("login")) {
				boolean isConnected = false;
				for(int i = 0; i < users.size(); i++) {
					try {
						if(InetAddress.getByName(packet.getAddress().getHostAddress()) == users.get(i).getIpAdress()) {
							isConnected = true;
							break;
						}
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
				}
				if(!isConnected) {
					users.add(new UserData(packet));
					refreshUserList();
				}
			}
		}
	}

	private void setUpSocket() {
		try {
			this.socket = new DatagramSocket(port);
			System.out.println("Server gestartet auf port: "+port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private void setUpGui() {
		frame = new JFrame("Chat Server v 0.1");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		
		text_connectedUsers = new JTextArea();
		text_connectedUsers.setEditable(false);
		
		scrollPane_connectedUsers = new JScrollPane(text_connectedUsers);
		scrollPane_connectedUsers.setPreferredSize(new Dimension(300, 500));
		scrollPane_connectedUsers.setMinimumSize(new Dimension(300, 500));
		scrollPane_connectedUsers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_connectedUsers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		
        frame.setVisible(true);
	}
	
	private void loadConfig() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("config.ini"));
			port = Integer.parseInt(p.getProperty("Port"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void refreshUserList() {
		text_connectedUsers.setText(null);
		for(int i = 0; i < users.size(); i++) {
			UserData d = users.get(i);
			text_connectedUsers.append(d.getIpAdress()+":"+d.getPort()+" nickname: "+d.getUsername()+"\n");
		}
	}
	
	public static void main(String[] args) { new MainClass(); }
}