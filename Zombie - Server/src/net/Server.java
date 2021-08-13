package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;


public class Server {
	
	private DatagramSocket socket;
	private int port;
	private LinkedList<ClientData> connectedClients;
	
	public Server() {
		port = 1234;
		System.out.println("Server gestartet auf port: "+port);
		connectedClients = new LinkedList<ClientData>();
		
		try {
			this.socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
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
				boolean isconnected = false;
				for(int i = 0; i < connectedClients.size(); i++) {
					ClientData c = connectedClients.get(i);
					if(splitData[1].trim().equalsIgnoreCase(c.getUsername())) {
						isconnected = true;
					}
				}
				
				if(!isconnected && connectedClients.size() != 8) {
					
					boolean[] numbers = new boolean[8];
					for(int i = 0; i < connectedClients.size(); i++) {
						ClientData c = connectedClients.get(i);
						if(c.getType() == 1)numbers[0] = true;
						if(c.getType() == 2)numbers[1] = true;
						if(c.getType() == 3)numbers[2] = true;
						if(c.getType() == 4)numbers[3] = true;
						if(c.getType() == 5)numbers[4] = true;
						if(c.getType() == 6)numbers[5] = true;
						if(c.getType() == 7)numbers[6] = true;
						if(c.getType() == 8)numbers[7] = true;
					}
					int type = 0;
					if(!numbers[0]) type = 1;
					else if(!numbers[1]) type = 2;
					else if(!numbers[2]) type = 3;
					else if(!numbers[3]) type = 4;
					else if(!numbers[4]) type = 5;
					else if(!numbers[5]) type = 6;
					else if(!numbers[6]) type = 7;
					else if(!numbers[7]) type = 8;
					
					connectedClients.add(new ClientData(splitData[1], packet.getAddress(), packet.getPort(), type));
					sendToAll(("00;"+splitData[1]+";"+type+";").getBytes());
					sendData(("01;"+splitData[1]+";false;false;false;false;"+random(1063, 336)+";"+random(1296, 336)+";").getBytes(), packet.getAddress(), packet.getPort());
					for(int i = 0; i < connectedClients.size(); i++) {
						ClientData c = connectedClients.get(i);
						if(!c.getUsername().trim().equalsIgnoreCase(splitData[1])) {
							sendData(("00;"+c.getUsername()+";"+c.getType()+";").getBytes(), packet.getAddress(), packet.getPort());
							sendData(("01;"+c.getUsername()+";false;false;false;false;"+c.getX()+";"+c.getY()+";").getBytes(), packet.getAddress(), packet.getPort());
						}
					}
					
					System.out.println(splitData[1] +" hat sich verbunden...");
				}else{
					sendData("You are already connected or the server is full!".getBytes(), packet.getAddress(), packet.getPort());
					System.out.println(splitData[1] +" konnte sich nicht verbinden; voll oder schon verbunden mit namen");
				}
				break;
				
			case "01": // movement
				sendToAll(message.getBytes());
				System.out.println(splitData[1] + " hat sich bewegt: "+splitData[6]+"/"+splitData[7]);
				for(int i = 0; i < connectedClients.size(); i++) {
					ClientData c = connectedClients.get(i);
					if(c.getUsername().trim().equalsIgnoreCase(splitData[1])) {
						c.setX(Integer.parseInt(splitData[6]));
						c.setY(Integer.parseInt(splitData[7]));
					}
				}
				break;
			
			case "02": // Disconnect
				sendToAll(message.getBytes());
				for(int i = 0; i < connectedClients.size(); i++) {
					ClientData c = connectedClients.get(i);
					if(c.getUsername().trim().equalsIgnoreCase(splitData[1].trim())) {
						connectedClients.remove(c);
					}
				}
				System.out.println(splitData[1] +" hat die Verbindung getrennt");
				break;
				
			case "03": // Shoot
				for(int i = 0; i < connectedClients.size(); i++) {
					ClientData c = connectedClients.get(i);
					if(c.getUsername().trim().equalsIgnoreCase(splitData[1].trim())) {
						switch (splitData[2]) {
						case "PISTOL":
							if(c.getAmmo_pistol() > 0) {
								c.setAmmo_pistol(c.getAmmo_pistol() - 1);
								System.out.println(c.getAmmo_pistol());
								sendToAll(message.getBytes());
							}
							break;
						case "UZI":
							if(c.getAmmo_uzi() > 0) {
								c.setAmmo_uzi(c.getAmmo_uzi() - 1);
								sendToAll(message.getBytes());
							}
							break;
						case "SHOTGUN":
							if(c.getAmmo_shotgun() > 0) {
								c.setAmmo_shotgun(c.getAmmo_shotgun() - 1);
								sendToAll(message.getBytes());
							}
							break;
						case "GRANADE":
							if(c.getAmmo_granade() > 0) {
								c.setAmmo_granade(c.getAmmo_granade() - 1);
								sendToAll(message.getBytes());
							}
							break;

						default:
							System.out.println(c.getUsername() +", unbekannte waffe...");
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
	
	private void sendToAll(byte[] data) {
		for(int i = 0; i < connectedClients.size(); i++) {
			DatagramPacket packet = new DatagramPacket(data, data.length, connectedClients.get(i).getIpAddress(), connectedClients.get(i).getPort());
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void sendData(byte[] data, InetAddress ipAdress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int random(int max, int min) {
		int range = (max - min) + 1;
		return (int) ((Math.random() * range) + min);
	}
	
	public static void main(String[] args) { new Server(); }
}
