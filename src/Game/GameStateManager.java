package Game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Net.Packet.PacketType;


public class GameStateManager {

	private ArrayList<GameState> states;
	private int currentState;
	 
	public static final int NAMESTATE = 0;
	public static final int LOADINGSTATE = 1;
	public static final int PLAYSTATE = 2;

	
	public GameStateManager(){
		currentState = NAMESTATE;
		
		states = new ArrayList<GameState>();	
		states.add(new NameState(this));
		states.add(new LoadingState(this));
		states.add(new PlayState(this));
		
		states.get(currentState).init();
	}
	
	
	public void setState(int state){
		currentState = state;
		states.get(currentState).init();
	}
	
	public void update(){
		states.get(currentState).update();
	}
	
	public void draw(Graphics2D g){
		states.get(currentState).draw(g);
	}
	
	public void keyPressed(KeyEvent e){
		states.get(currentState).keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		states.get(currentState).keyReleased(e);
	}
	
	public void packetReceived(PacketType type, String msg){
		states.get(currentState).packetReceived(type, msg);
	}


	public void updateSec() {
		states.get(currentState).updateSec();
	}
}
