package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Net.Packet.PacketType;
import Util.PlayerProfile;
import Util.Stats;
import Util.Util;
import Util.Vault;

public class LoadingState extends GameState{

	private String text = "Loading...";

	
	int addX = 0;
	int cx = 0;
	
	boolean drawSend = false;
	int sendA = 254;
	
	public LoadingState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		text = "Welkom "+Vault.get("name");
	}

	@Override
	public void update() {
		if(cx >= 180){
			if(addX >= 610){
				addX = 0;
				cx = 0;

			}else{
				addX++;
			}
		}else{
			cx++;
			if(cx >= 180) addX = 200;
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		
		
		g.setColor(Main.Main.bg.getColor());
		g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
		
		g.setColor(new Color(255, 255, 255));
		
		int addWidth = (g.getFontMetrics().stringWidth(text))/2;
		g.drawString(text, 400-addWidth, 100);
		
		
		g.setColor(new Color(100, 100, 100, 150));
		
		g.fillRect(200, 150, 400, 10);
		
		int aw = 10;
		
		if(addX > 0){
			g.setColor(new Color(255, 255, 255, 255));
			
			if(addX == 405) drawSend = true;
			
			if(addX < aw+200){
				g.fillRect(200, 150, 200-addX, 10);
			}else if(addX > 600){
				g.fillRect(addX-aw, 150, 600-(addX-aw), 10);
			}else{
				g.fillRect(addX-aw, 150, aw, 10);
			}
		}
		
		if(drawSend){
			
			sendA-=3;
			
			if(sendA <= 40){
				sendA = 254;
				drawSend = false;
			}
			
			g.setColor(new Color(255, 255, 255, sendA));
			
			int width = (255-sendA)+10;
			
			g.fillRect(405-(width/2), 150-(width/2), width, width);
		}
		
		int x = 290;
		int y = 250;
		int count = 0;
		boolean switched = false;
		
		for(PlayerProfile p : Stats.Connected){
			g.setColor(p.getColor());
			Util.drawRoundedRect(g, x, y, 101, 30);
			
			g.setFont(new Font("Modern No. 20", Font.BOLD, 20));
			g.setColor(Color.WHITE);
			int add = (g.getFontMetrics().stringWidth(p.getName()))/2;
			g.drawString(p.getName(), x+51-add, y+21);
			
			y+=35;
			count++;
			
			if(!switched && count >= Stats.Connected.size()/2){
				y = 250;
				x = 410;
				switched = true;
			}
		}
		
		super.drawComponents(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.updateTextBoxes(e);
	}

	@Override
	public void packetReceived(PacketType type, String msg) {
		if(type == PacketType.START){
			gsm.setState(GameStateManager.PLAYSTATE);
		}

		
		
	}

}
