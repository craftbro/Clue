package Net;






public class Packet06Points extends Packet {

	private static int id = 06;
	
	private String name;
	private int points;
	
	public Packet06Points(String data) {
		super(id);
		
		String[] d = data.split(":");
		
		name = d[0];
		points = Integer.parseInt(d[1]);
	}
	
	
	public String getName(){
		return name;
	}
	
	public int getPoints(){
		return points;
	}


	@Override
	public void writeData(GameClient c) {
	//	c.broadcastPacket(getData());
	}


	@Override
	public byte[] getData() {
		return ("06"+name+":"+points).getBytes();
	}



}
