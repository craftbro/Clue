package Net;




import java.util.HashMap;

public class Packet03Minigame extends Packet {

	private static int id = 03;
	
	private String name;
	private HashMap<String, String> args = new HashMap<String, String>();
	 
	public Packet03Minigame(String data) {
		super(id);
		
		System.out.println("MSG: "+data);
		
		String[] base = data.split(";");
		
		name = base[0];
		
		String[] args = base[1].split(",");
		
		for(String arg : args){
			String[] a = arg.split(":");
			this.args.put(a[0], a[1]);
		}
	
	}
	
	public String getName(){
		return name;
	}
	
	public HashMap<String, String> getArgs(){
		return args;
	}
	

	@Override
	public void writeData(GameClient c) {
		c.sendData(getData());
	}


	@Override
	public byte[] getData() {
		return (id+"").getBytes();
	}
	
	

}
