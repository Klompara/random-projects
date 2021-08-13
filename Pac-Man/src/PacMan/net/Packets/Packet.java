package PacMan.net.Packets;

import PacMan.net.NetClient;
import PacMan.net.NetServer;

public abstract class Packet {
	
	public static enum PacketTypes {
		INVALID(-1),
		LOGIN(00),
		DISCONNECT(01);
		
		private int packetID;
		private PacketTypes(int packetID) {
			this.packetID = packetID;
		}
		
		public int getID() {
			return packetID;
		}
	}
	
	public byte packetID;
	public Packet(int packetID) {
		this.packetID = (byte) packetID;
	}
	
	public abstract void writeData(NetClient client);
	public abstract void writeData(NetServer server);
	
	public abstract byte[] getData();
	
	public String readData(byte[] data) {
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public static PacketTypes lookUpPacket(String packetID) {
		try {
			return lookUpPacket(Integer.parseInt(packetID));
		}catch(NumberFormatException e) {
			return PacketTypes.INVALID;
		}
	}
	
	public static PacketTypes lookUpPacket(int ID) {
		for(PacketTypes p : PacketTypes.values()) {
			if(p.getID() == ID){
				return p;
			}
		}
		return PacketTypes.INVALID;
	}
}
