package PacMan.net.Packets;

import PacMan.net.NetClient;
import PacMan.net.NetServer;

public class Packet00Login extends Packet{

	private String username;
	
	public Packet00Login(byte[] data) {
		super(00);
		this.username = readData(data);
	}
	
	public Packet00Login(String username) {
		super(00);
		this.username = username;
	}
	
	
	
	public void writeData(NetClient client) {
		client.sendData(getData());
	}

	public void writeData(NetServer server) {
		server.sendDataToAllC(getData());
	}

	public byte[] getData() {
		return ("00"+this.username).getBytes();
	}
	
	public String getUsername() {
		return this.username;
	}
}
