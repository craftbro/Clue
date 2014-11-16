package Game;

import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import Components.Button;
import Components.TextBox;
import Game.GameState;
import Game.GameStateManager;
import Net.Packet.PacketType;
import Net.Packet00Connect;
import Util.Vault;

public class NameState extends GameState{

	
	private String text = "Voer je naam in";
	private TextBox box;
	
	public NameState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		box = (TextBox) super.addComponent(new TextBox(400, 300, 10, 20, true));
		super.addComponent(new Button(400, 350, "Klaar", 15, true, new Runnable(){
			public void run(){
				if(box.getText().length() > 0){
					System.out.println("Text Box: "+box.getText());
					Vault.store("name", box.getText());
					System.out.println("Name stored in Vault! Sending connection packet...");
					new Packet00Connect(box.getText()).writeData(Main.Main.client);
					System.out.println("Switching to loadingstate!");
					gsm.setState(GameStateManager.LOADINGSTATE);
				}else{
					System.out.println("Text Box Empty");
				}
			}
		}));
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics2D g) {	
		g.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		
		g.setColor(Main.Main.bg.getColor());
		g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
		
		
		int a = (g.getFontMetrics().stringWidth(text))/2;	
		g.setColor(Color.WHITE);
		g.drawString(text, 400-a, 250);
		
		
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
	
		
		
	}

}
