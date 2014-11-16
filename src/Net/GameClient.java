package Net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Main.Main;
import Net.Packet.PacketType;
import Util.PlayerProfile;
import Util.Stats;


public class GameClient extends Thread {

	InetAddress address;
	private DatagramSocket socket;
	private Main main;
	int port = 25565;
	
	public GameClient(Main main, String ip){
		this.main = main;
		try {
			this.socket = new DatagramSocket();
			this.address = InetAddress.getByName(ip);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public GameClient(Main main, InetAddress address2, int port) {
		this.main = main;
		this.port = port;
		try {
			this.socket = new DatagramSocket();
			this.address = address2;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void run(){
		while(true){
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
		String message = new String(data).substring(0, 2).trim();
		PacketType type = Packet.getPacket(message);
		
		String d = new String(data).substring(2, data.length).trim();
		
		System.out.println("client received: "+type.name());
		
		switch(type){
			case LIST:{
				Stats.updateConnected(d);
			}
			break;
			case POINTS:{
				Stats.updatePoints(d);
			}
			break;
		default:
			break;
		}
		
		main.gsm.packetReceived(type, d);
	}
	
	public void sendData(byte[] data){		
		DatagramPacket packet = new DatagramPacket(data, data.length, address, 25565);
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

	
	

