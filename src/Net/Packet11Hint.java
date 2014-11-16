package Net;




import java.awt.Color;

public class Packet11Hint extends Packet {

	private static int id = 11;

	private int slot;
	private Color c;
	
	public Packet11Hint(String data) {
		super(id);
		
		String[] d = data.split(",");
		
		slot = Integer.parseInt(d[0]);
		
		c = new Color(Integer.parseInt(d[1]));
	}
	
	public int getSlot(){
		return slot;
	}
	
	public Color getColor(){
		return c;
	}


	@Override
	public void writeData(GameClient c) {
	//	c.sendData(getData());
	}


	@Override
	public byte[] getData() {
		return ("11").getBytes();
	}
	
	

}
