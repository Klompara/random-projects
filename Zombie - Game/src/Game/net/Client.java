package Game.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;

import Game.Main;
import Game.Entitys.Bullet;
import Game.Entitys.PlayerMP;

public class Client extends Thread{
	
	public InetAddress ipAdress;
	private DatagramSocket socket;
	
	public LinkedList<PlayerMP> otherPlayers;
	
	public Client(InetAddress ipAdress) {
		otherPlayers = new LinkedList<PlayerMP>();
		this.ipAdress = ipAdress;
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		start();
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
			String[] splitData = message.split(";");
			
			
			switch(splitData[0]) {
			
			case "00": // login
				otherPlayers.add(new PlayerMP(splitData[1], Integer.parseInt(splitData[2]), this));
				break;
				
			case "01": // movement
				for(int i = 0; i < otherPlayers.size(); i++) {
					PlayerMP p = otherPlayers.get(i);
					if(p.getUsername().trim().equalsIgnoreCase(splitData[1]) && p != Main.handler.getPlayer()) {
						p.setMLeft(Boolean.parseBoolean(splitData[2]));
						p.setMRight(Boolean.parseBoolean(splitData[3]));
						p.setMDown(Boolean.parseBoolean(splitData[4]));
						p.setMUp(Boolean.parseBoolean(splitData[5]));
						p.setX(Integer.parseInt(splitData[6]));
						p.setY(Integer.parseInt(splitData[7]));
					}
				}
				break;
				
			case "02": // Disconnect
				for(int i = 0; i < otherPlayers.size(); i++) {
					PlayerMP p = otherPlayers.get(i);
					if(p.getUsername().trim().equalsIgnoreCase(splitData[1].trim())) {
						p.remove();
					}
				}
				break;
			
			case "03": // Shoot
				for(int i = 0; i < otherPlayers.size(); i++) {
					PlayerMP p = otherPlayers.get(i);
					if(p.getUsername().trim().equalsIgnoreCase(splitData[1].trim())) {
						switch (splitData[2]) {
						case "PISTOL":
							Main.handler.bullets.add(new Bullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Double.parseDouble(splitData[3]), Double.parseDouble(splitData[4]), 1));
							break;

						case "UZI":
							Main.handler.bullets.add(new Bullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Double.parseDouble(splitData[3]), Double.parseDouble(splitData[4]), 2));
							break;
						
						case "SHOTGUN":
							Main.handler.bullets.add(new Bullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Double.parseDouble(splitData[3]), Double.parseDouble(splitData[4]), 3));
							break;
						
						case "GRANADE":
							Main.handler.bullets.add(new Bullet(p.getX()+p.getWidth()/2, p.getY()+p.getHeight()/2, Double.parseDouble(splitData[3]), Double.parseDouble(splitData[4]), 4));
							break;
							
						default:
							break;
						}	
					}
				}
				break;
				
			default:
				System.out.println(packet.getAddress()+":"+packet.getPort()+" hat ein nicht identifizierbares packet gesendet!: "+message);
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
