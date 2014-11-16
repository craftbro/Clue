package Minigame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import Net.Packet04Win;



public abstract class Minigame {
	

	//Global Game Managing
	private static HashMap<String, Minigame> games = new HashMap<String, Minigame>();
	private static Object[] gameNames = {"Math", new MinigameMath()};
	
	//Variables
	private String name;
	private String help;

	
	public Minigame(String name, String help){
		this.name = name;
		this.help = help;
	}
	
	
	public abstract void init(HashMap<String, String> args);
	public abstract void draw(Graphics g);
	public abstract void keypressed(KeyEvent e);
	public abstract void keyrelease(KeyEvent e);
	

	protected void win(){
		new Packet04Win().writeData(Main.Main.client);
	}
	
	
	public static final Minigame getMinigame(String name, HashMap<String, String> args){
		Minigame m = games.get(name);
		m.init(args);
		return m;
	}
	
	public final String getName(){
		return name;
	}
	
	public final String getHelp(){
		return help;
	}
	
	public static final void setup(){ 
		String name = null;
		
		for(Object o : gameNames){
			if(name == null){
				name = (String)o;
			}else{
				games.put(name, (Minigame)o);
			}
		}
	}
	
	
}
