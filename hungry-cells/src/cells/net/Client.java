package cells.net;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import cells.Entity.PlayerMP;
import cells.Main.Handler;

public class Client extends Thread{
	
	private InetAddress ipAdress;
	private DatagramSocket socket;
	
	private Handler handler;
	
	public Client(String ipAdress, Handler handler) {
		this.handler = handler;
		try {
			this.socket = new DatagramSocket();
			this.ipAdress = InetAddress.getByName(ipAdress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
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
			String message = new String(packet.getData());
			System.out.println("[SERVER] ["+packet.getAddress().getHostAddress()+"] ["+packet.getPort()+"] - "+message);
			
			String firstnum = message.substring(0, 2);
			switch(firstnum) {
			case "00": // login
				String[] splitName = message.split(";");
				PlayerMP player = new PlayerMP(0, 0, new Color(Integer.parseInt(splitName[2]), Integer.parseInt(splitName[3]), Integer.parseInt(splitName[4])), splitName[1], packet.getAddress(), packet.getPort());
				handler.otherPlayers.add(player);
				System.out.println(player.getUsername() + " hat sich verbunden...");
				break;
			case "01": // infos
				for(int i = 0; i < handler.otherPlayers.size(); i++) {
					PlayerMP p = handler.otherPlayers.get(i);
					if(p.getIpAddress().toString().trim().equalsIgnoreCase(packet.getAddress().toString())) {
						String[] splitInfos = message.split(";");
						p.setR(Integer.parseInt(splitInfos[1]));
						p.setX(Integer.parseInt(splitInfos[2]));
						p.setY(Integer.parseInt(splitInfos[3]));
					}
				}
				break;
			case "02": // disconnect
				for(int i = 0; i < handler.otherPlayers.size(); i++) {
					PlayerMP p = handler.otherPlayers.get(i);
					if(p.getIpAddress().toString().trim().equalsIgnoreCase(packet.getAddress().toString())) {
						System.out.println(p.getUsername() +" hat die Verbindung getrennt...");
						handler.otherPlayers.remove(p);
					}
				}
				break;
			}
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, 1234);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
