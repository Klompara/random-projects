package Game.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client implements Runnable {

	public static boolean running = false;
	private boolean waiting = false;
	public Thread thread;
	private InetAddress ipAdress;
	private DatagramSocket socket;
	private int port;
	
	public Client(int port) { // Server
		waiting = true;
		this.port = port;
		try {
			this.socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public Client(String adress, int port) { // Client
		waiting = false;
		this.port = port;
		try {
			this.socket = new DatagramSocket();
			this.ipAdress = InetAddress.getByName(adress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		sendData(("JOIN&"+JOptionPane.showInputDialog("nickname:")+"&").getBytes());
	}
	
	public void run() {
		while(running) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			if(message.split("&")[0].equalsIgnoreCase("JOIN") && waiting) {
				waiting = false;
				System.out.println(message.split("&")[1] + " has joined");
			}
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sendData(byte[] data) { // Client send
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendData(byte[] data, InetAddress ipAdress, int port) { // Server send
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
