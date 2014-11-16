package Net;




import Net.GameClient;
import Util.Stats;

public class Packet04Win extends Packet {

	private static int id = 04;
	
	private String name;
	
	public Packet04Win() {
		super(id);
		
		this.name = Stats.localProfile.getName();
	}


	@Override
	public void writeData(GameClient c) {
		c.sendData(getData());
	}


	@Override
	public byte[] getData() {
		return ("04"+name).getBytes();
	}
	
	

}
