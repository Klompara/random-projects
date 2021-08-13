package cells.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import cells.Entity.PlayerMP;
import cells.Main.Handler;
import cells.Main.Main;

public class Server extends Thread{
	
	private DatagramSocket socket;
	
	private Handler handler;
	
	public Server(Handler handler) {
		
		this.handler = handler;
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
			String message = new String(packet.getData());
			
			sendtoall(data, packet.getAddress());
			
			String firstnum = message.substring(0, 2);
			switch(firstnum) {
			case "00": // login
				String[] splitName = message.split(";");
				PlayerMP player = new PlayerMP(0, 0, handler.colors.get(Main.random(handler.colors.size()-1, 0)), splitName[1], packet.getAddress(), packet.getPort());
				handler.otherPlayers.add(player);
				System.out.println(player.getUsername() + " hat sich verbunden...");
				for(int i = 0; i < handler.otherPlayers.size(); i++) {
					sendData(("00"+handler.otherPlayers.get(i).getUsername()+";"+handler.otherPlayers.get(i).getColor().getRed()+";"+handler.otherPlayers.get(i).getColor().getGreen()+";"+handler.otherPlayers.get(i).getColor().getBlue()+";").getBytes(), packet.getAddress(), packet.getPort());
				}
				try {
					sendData(("00;"+handler.player.getUsername()+";").getBytes(), InetAddress.getByName("localhost"), packet.getPort());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				break;
			case "01": // infos
				for(int i = 0; i < handler.otherPlayers.size(); i++) {
					PlayerMP p = handler.otherPlayers.get(i);
					if(p.getIpAddress().toString().trim().equalsIgnoreCase(packet.getAddress().toString())) {
						String[] splitInfos = message.split(";");
						p.setR(Integer.parseInt(splitInfos[1]));
						p.setX(Integer.parseInt(splitInfos[2]));
						p.setY(Integer.parseInt(splitInfos[3]));
						System.out.println("Spieler: "+p.getUsername()+", Radius: "+p.getR()+", Koordinaten: "+p.getX()+", "+p.getY() + "["+p.getIpAddress()+":"+p.getPort()+"]");
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
	
	public void sendtoall(byte[] data, InetAddress address) {
		for(int i = 0; i < handler.otherPlayers.size(); i++) {
			PlayerMP player = handler.otherPlayers.get(i);
			if(!player.getIpAddress().toString().trim().equalsIgnoreCase(address.toString())) {
				DatagramPacket packet = new DatagramPacket(data, data.length, player.getIpAddress(), player.getPort());
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void sendData(byte[] data, InetAddress ipAdress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
