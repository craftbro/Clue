package Net;




import Net.GameClient;

public class Packet00Connect extends Packet {

	private static int id = 00;
	
	private String name;
	
	public Packet00Connect(String name) {
		super(id);
		
		this.name = name;
	}


	@Override
	public void writeData(GameClient c) {
		c.sendData(getData());
	}


	@Override
	public byte[] getData() {
		return ("00"+name).getBytes();
	}
	
	

}
