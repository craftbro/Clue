package Game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Components.GComponent;
import Components.TextBox;
import Net.Packet.PacketType;

public abstract class GameState {
	
	protected GameStateManager gsm;
	private List<GComponent> components = new ArrayList<GComponent>();
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}

	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	
	public abstract void packetReceived(PacketType type, String msg);
	
	protected List<GComponent> getComponents(){
		return components;
	}
	
	protected GComponent addComponent(GComponent c){
		components.add(c);
		return c;
	}
	
	
	protected void removeComponent(int i){
		components.remove(i);
	}
	
	protected void removeComponent(GComponent c){
		components.remove(c);
	}
	
	protected void drawComponents(Graphics2D g){
		for(GComponent c : components) c.draw(g);
	}
	
	protected void updateTextBoxes(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_SHIFT
				|| e.getKeyCode() == KeyEvent.VK_CONTROL
				|| e.getKeyCode() == KeyEvent.VK_ALT
				|| e.getKeyCode() == KeyEvent.VK_ENTER){
					return;
		}
		
		for(GComponent c : components){
			if(c instanceof TextBox){
				TextBox b = (TextBox)c;
				
				if(!b.isSelected()) return;
				
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					b.backSpace();
					continue;
				}
				
				
				b.addChar(e.getKeyChar());
				
			}
		}
	}

	public void updateSec() {
		
	}
	
}
