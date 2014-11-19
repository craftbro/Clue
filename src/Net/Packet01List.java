package Net;




import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Util.PlayerProfile;




public class Packet01List extends Packet {

	private static int id = 01;
	
	private List<PlayerProfile> list = new ArrayList<PlayerProfile>();
	
	public Packet01List(String data) {
		super(id);
		
		String[] ps = data.split(",");
		
		for(String s : ps){
			String[] ds = s.split(";");
			String name = ds[0];
			
			
			String[] cs = ds[1].split(":");
			
			int red = Integer.parseInt(cs[0]);
			int green = Integer.parseInt(cs[1]);
			int blue = Integer.parseInt(cs[2]);
			
			Color c = new Color(red, green, blue);
			
			Color[] colors = null;
			
			if(ds.length > 3){
				String[] cls = ds[2].split("/");
				colors = new Color[4];
				int cu = 0;
				
				for(String ss : cls){
					String[] ccs = ss.split(":");
					
					
					int red1 = Integer.parseInt(ccs[0]);
					int green1 = Integer.parseInt(ccs[1]);
					int blue1 = Integer.parseInt(ccs[2]);
					
					colors[cu] = new Color(red1, green1, blue1);
					cu++;
				}
			}
				
				
				PlayerProfile p = new PlayerProfile(name, c, colors);
				p.setAlive(Boolean.parseBoolean(ds[ds.length-1]));
				list.add(p);
			
		}
	}
	
	public List<PlayerProfile> getList(){
		return list;
	}


	@Override
	public void writeData(GameClient c) {
		//c.broadcastPacket(getData());
	}


	@Override
	public byte[] getData() {
		String data = "";
		
		for(PlayerProfile p : list){
			data += p.getName();
			
			String color = p.getColor().getRed() + "." + p.getColor().getGreen() + "." + p.getColor().getBlue();
			
			data += ";"+color+",";
		}
	
		
		
		return ("01"+data).getBytes();
	}




}
