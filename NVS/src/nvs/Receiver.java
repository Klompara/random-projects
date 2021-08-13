package nvs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver {

	public static void main(String[] args) {
		int port = 4343;
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
		}
	}
}
