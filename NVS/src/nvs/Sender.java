package nvs;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Sender {
	public static void main(String[] args) {
		int port = 4343;
		String ip = "192.168.195.216";
		while (!false) {
			try {
				sendData(JOptionPane.showInputDialog(null, "Message: ", "", JOptionPane.QUESTION_MESSAGE), InetAddress.getByName(ip),
						port, new DatagramSocket());
			} catch (HeadlessException | UnknownHostException | SocketException e) {
				e.printStackTrace();
			}
		}
	}

	private static void sendData(String msg, InetAddress ipAdress, int port, DatagramSocket socket) {
		DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
