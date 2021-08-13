package Server;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UserData {
	private InetAddress ipAdress;
	private int port;
	private String username;
	public UserData(DatagramPacket packet) {
		try {
			ipAdress = InetAddress.getByName(packet.getAddress().getHostAddress());
			port = packet.getPort();
			username = new String(packet.getData(), 0, packet.getLength()).split(";")[2];
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public InetAddress getIpAdress() {
		return ipAdress;
	}
	public int getPort() {
		return port;
	}
	public String getUsername() {
		return username;
	}
}
