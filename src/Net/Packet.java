package Net;

public abstract class Packet {

	public static enum PacketType{
		INVALID(-1), CONNECT(00), LIST(01), START(02), MINIGAME(03), WIN(04), WINNER(05), POINTS(06), END(07), MURDERER(10), HINT(11), VICTIM(12);
		
		private int id;
		
		private PacketType(int id){
			this.id = id;
		}
		
		public int getId(){
			return id;
		}
		
	}
	
	public byte packetId;
	
	public Packet(int packetId){
		this.packetId = (byte)packetId;
	}
	
	public abstract void writeData(GameClient c);
//	public abstract void writeData(GameServer c);
	public abstract byte[] getData();
	
	public String readData(byte[] data){
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public static PacketType getPacket(String id){
		try{
			return getPacket(Integer.parseInt(id));
		}catch(NumberFormatException e){
			return PacketType.INVALID;
		}
	}
	
	public static PacketType getPacket(int id){
		for(PacketType p : PacketType.values()){
			if(p.getId() == id) return p;
		}
		return PacketType.INVALID;
	}
	
}
