package Util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Net.Packet01List;
import Net.Packet06Points;

public class Stats {


	public static List<PlayerProfile> Connected = new ArrayList<PlayerProfile>();
	public static PlayerProfile localProfile = null;
	
	public static void updateConnected(String msg){
		Packet01List p = new Packet01List(msg);
		
		if(Vault.get("name") != null) for(PlayerProfile pp : p.getList()) if(pp.getName().contentEquals((String)Vault.get("name"))) localProfile = pp;
		
		Connected = p.getList();
	}
	
	public static void updatePoints(String data){
		Packet06Points d = new Packet06Points(data);
		
		for(PlayerProfile p : Connected){
			if(p.getName().contentEquals(d.getName())){
				p.addPoints(d.getPoints());
				return;
			}
		}
	}
}
