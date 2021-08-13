package PacMan.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import PacMan.Entity.PlayerMP;
import PacMan.net.Packets.Packet;
import PacMan.net.Packets.Packet.PacketTypes;
import PacMan.net.Packets.Packet00Login;

public class NetServer extends Thread{
	
	private DatagramSocket socket;
	public List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	public NetServer() {
		try {
			this.socket = new DatagramSocket(1234);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes types = Packet.lookUpPacket(message.substring(0, 2));
		
		switch (types) {
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet00Login packet = new Packet00Login(data);
			System.out.println("["+address.getHostAddress()+"] "+ packet.getUsername() + " hat sich verbunden...");
			if(address.getHostAddress() == "127.0.0.1"){
			}else{
				PlayerMP player = new PlayerMP(packet.getUsername(), address, port);
				connectedPlayers.add(player);
			}
			break;
		case DISCONNECT:
			for(PlayerMP p : connectedPlayers){
				if(p.ipAddress == address){
					connectedPlayers.remove(p);
					System.out.println("disconnect "+address +" "+ p.ipAddress);
				}
			}
			break;
		}
	}

	public void sendData(byte[] data, InetAddress ipAdress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDataToAllC(byte[] data) {
		for(int i = 0; i < connectedPlayers.size(); i++) {
			PlayerMP p = connectedPlayers.get(i);
			sendData(data, p.ipAddress, p.port);
		}
	}
}
