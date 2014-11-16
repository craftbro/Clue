package Util;

import java.awt.Color;
import java.net.InetAddress;

public class PlayerProfile{

	private String name;
	private Color c;
	private int points = 0;
	
	private Color[] clothing;
	
	public PlayerProfile(String name, Color c, Color[] clothing) {
		this.name = name;
		this.c = c;
		this.clothing = clothing;
	}

	public String getName(){
		return name;
	}
	
	public Color getColor(){
		return c;
	}
	
	public Color[] getClothing(){
		return clothing;
	}
	
	public void addPoints(int add){
		points+=add;
	}
	
	public int getPoints(){
		return points;
	}
	
}
