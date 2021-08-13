package app;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Main {

	// Wir nehmen an, diese daten werden von den Server angeboten
	private int g = 7;
	private int n = 15487151;
	
	private DatagramSocket socket = null;
	private InetAddress ipAdress = null;
	private int port = 9999;
	
	public Main() {
		try {
			ipAdress = InetAddress.getByName(JOptionPane.showInputDialog(null, "IP-Addrese: ", "localhost", JOptionPane.QUESTION_MESSAGE));
			socket = new DatagramSocket();
		} catch (HeadlessException | UnknownHostException | SocketException e1) {
			e1.printStackTrace();
		}
		
		int dataInput = Integer.parseInt(JOptionPane.showInputDialog("Your Data (int)"));
		int sendingData = (int) ((Math.pow(g, dataInput))%n);
		System.out.println(sendingData);
		sendData(""+sendingData);
		
		
		try {
			socket = new DatagramSocket(port);
			System.out.println("Warte auf Pakete... Port: "+port);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		boolean running = true;
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		while(running) {
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData(), 0, packet.getLength());
			message = message.replace("", "");
			System.out.println(message);
		}
	}
	
	public static void main(String[] args) { new Main(); }
	
	private void sendData(String command) {
		DatagramPacket packet = new DatagramPacket(command.getBytes(), command.getBytes().length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Send data to \t" + ipAdress +":" + port +"\t message: " + command);
	}

}
