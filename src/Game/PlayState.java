package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import Components.Button;
import Components.ClothingDisplay;
import Components.PlayerButton;
import Minigame.Minigame;
import Net.Packet.PacketType;
import Net.Packet03Minigame;
import Net.Packet11Hint;
import Util.Mouse;
import Util.PlayerProfile;
import Util.Stats;
import Util.Util;

public class PlayState extends GameState{
	
	private ClothingDisplay pd;
	private Rectangle rec;
	private Rectangle rec1;
	private boolean down = false;
	private boolean down1 = false;
	
	private boolean murd = false;
	private BufferedImage knife = Main.Main.imageloader.loadImage("Knife.png");
	private BufferedImage scoreboard = Main.Main.imageloader.loadImage("Scoreboard.png");
	private boolean drawMurdSelect = false;
	
	private boolean dead = false;
	private boolean choice = false;
	
	private Minigame cm = null;
	private String winner = "[ERROR]";
	private String end = "Einde";
	private String murdText = "Jij bent de Moordenaar!";
	
	int disMurd = 0;
	int disWinner = 0;
	int disEnd = 0;
	Button b;
	int disText;
	
	String text;
	Color disC;
	int textSize;
	
	int cd = 0;

	int disHint = 0;
	String slotName = "Error";
	BufferedImage hintImg = null;
	
	int count = 0;
	
	private HashMap<PlayerProfile, PlayerButton> buttons = new HashMap<PlayerProfile, PlayerButton>();
	private HashMap<PlayerProfile, ClothingDisplay> cds = new HashMap<PlayerProfile, ClothingDisplay>();

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		pd = new ClothingDisplay(756, 0, Stats.localProfile);
		rec = new Rectangle(753, 2, 39, 57+15);
		rec1 = new Rectangle(683, 2, 70, 70);
		cm = null;
		murd = false;
		down = false;
		down1 = false;
		cd = 0;
		count = 0;
		disText = 0;
		disHint = 0;
		
		
		this.setupClothingDisplays();
		
		
		b = new Button(400, 400, "Oke", 30, true, new Runnable(){
			@Override
			public void run(){
				disText = 0;
			}
		});
	}

	@Override
	public void update(){
		boolean d = !Stats.localProfile.isAlive();
		
		if(d != dead){
			System.out.println("dead");
			if(!dead){
				Main.Main.bg.setMurdered();
			}else{
				Main.Main.bg.setNormal();
			}
		}
		
		dead = d;
	}
	
	@Override
	public void updateSec() {
//		if(cd > 0){
//			cd--;
//		}
	}

	@Override
	public void draw(Graphics2D g) {
			g.setColor(Main.Main.bg.getColor());
			g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
		
		
		g.setColor(new Color(40, 40, 40, 185));
		g.fillRect(0, 0, Main.Main.WIDTH, 75);
	
		if(pd != null) pd.draw(g);
		
		g.drawImage(scoreboard, 686, 5, 64, 64, null);
		if(murd) g.drawImage(knife, 20, 5, 64, 64, null);
		
		
		if(drawMurdSelect){
			g.setColor(new Color(20, 20, 20, 140));
			g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
			
			int x=100;
			int y=250;
			
			for(PlayerProfile p : cds.keySet()){
				
				g.setColor(new Color(20, 20, 20, 140));
				g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
				
				if(buttons.containsKey(p)) buttons.get(p).draw(g);
			}
			
			
			g.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		
			g.setColor(Color.white);
			
			Util.drawCompact(g, "Jij hebt de Minigame gewonnen! Nu kan je iemand vermoorden, maar wie?", 400, 200);
		}else if(choice){
			g.setColor(new Color(20, 20, 20, 140));
			g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
			
			int x=100;
			int y=250;
			
			for(PlayerProfile p : cds.keySet()){
				
				g.setColor(new Color(20, 20, 20, 140));
				g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
				
				if(buttons.containsKey(p)) buttons.get(p).draw(g);
			}
			
			
			g.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		
			g.setColor(Color.white);
			
			Util.drawCompact(g, "Wie denk je dat de moordenaar is?", 400, 200);
		}else{
		
		if(cm != null){
			if(cd <= 0){
				cm.draw(g);
			}else{
				g.setFont(new Font("Modern No. 20", Font.BOLD, 40));
				g.setColor(Color.RED);
				g.drawString(""+cd, 390, 120);
				
				g.setFont(new Font("Modern No. 20", Font.BOLD, 30));
				g.setColor(Color.GREEN);
				
				int w = g.getFontMetrics().stringWidth(cm.getName())/2;
						
				g.drawString(cm.getName(), 400-w, 200);
				
				Util.drawCompact(g, cm.getHelp(), 400, 300);
			}
		}else if(disHint > 0){
			g.setColor(new Color(255, 179, 0));
			
			g.fillRect(200, 100, 400, 400);
			
			g.setFont(new Font("Modern No. 20", Font.BOLD, 40));
			
			g.setColor(Color.BLACK);
			
			int w = g.getFontMetrics().stringWidth("Hint")/2;
			
			g.drawString("Hint", 400-w, 130);
			
			g.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
			
			Util.drawCompact(g, "De Moordenaar heeft "+slotName+" aan:", 400, 200);
			
			g.drawImage(hintImg, 336, 240, 128, 128, null);
		}else 			
			if(disText > 0){
				
				b.draw(g);

				g.setFont(new Font("Modern No. 20", Font.BOLD, textSize));
				
				int a = disText>255?255:disText;
				
				g.setColor(new Color(disC.getRed(), disC.getGreen(), disC.getBlue(),  a));
				
				int w = g.getFontMetrics().stringWidth(text)/2;
				
				g.drawString(text, 400-w, 300);

			}
		
		
		if(!down && Main.Main.mouse != null &&
				rec.contains(Mouse.getMouseLocation())){
			down = true;

		}else if(down && 
				!rec.contains(Mouse.getMouseLocation())){
			down = false;
		}
		
		if(!down1 && Main.Main.mouse != null &&
				rec1.contains(Mouse.getMouseLocation())){
			down1 = true;

		}else if(down1 && 
				!rec1.contains(Mouse.getMouseLocation())){
			down1 = false;
		}
		
		if(down){
			g.setColor(new Color(20, 20, 20, 140));
			g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
			
			int x=100;
			int y=250;
			
			for(PlayerProfile p : Stats.Connected){
				
				Util.drawClothes(g, p, x, y);
				
				g.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
				
				String s = p.getName();
				
				if(p.getName().contentEquals(Stats.localProfile.getName())){
					s = "Jij";
					
					if(p.getClothing()[0] != pd.getProfile().getClothing()[0]){
						System.out.println("!");
					}
				}
				
				if(s.length() > 5){
					g.setFont(new Font("Modern No. 20", Font.PLAIN, 10));
				}
				
				g.setColor(Color.white);
				
				g.drawString(s, x, y-10);
				
				
				
				x+=50;
				if(x>700) y+=150;
			}
			
			cds.clear();
			
			this.setupClothingDisplays();
		}else if(down1){
			g.setColor(new Color(20, 20, 20, 140));
			g.fillRect(0, 0, Main.Main.WIDTH, Main.Main.HEIGHT);
			
			int y=0;
			
			g.setColor(Color.WHITE);
			
			for(PlayerProfile p : Stats.Connected){
				g.setFont(new Font("Modern No. 20", Font.BOLD, 20));
				
				String draw = p.getName()+": "+p.getPoints();
				
				Util.drawCompact(g, draw, 400, 100+y);
				
					y+=30;
			}
		}
		
		}
		
	}
	
	private boolean anyoneAlive(){
		boolean t = false;
		
		for(PlayerProfile p : Stats.Connected){
			if(p.getName().contentEquals(Stats.localProfile.getName()) || !p.isAlive()) continue;
			t = true;
			break;
		}
		
		return t;
	}
	
	public void murdWin(){
		if(anyoneAlive()) drawMurdSelect = true;
	}
	
	public void selectPlayer(PlayerButton b){
		if(murd && !choice){
			drawMurdSelect = false;
			Main.Main.client.sendData(("12"+b.getName()).getBytes());
			System.out.print("Chose: "+b.getName());
		}else if(choice){
			Main.Main.client.sendData(("13"+Stats.localProfile.getName()+","+b.getName()).getBytes());
			
			choice = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(cm != null) cm.keypressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(cm != null) cm.keyrelease(e);
	}
	
	private void setupClothingDisplays(){
		cds.clear();
		
		pd = new ClothingDisplay(756, 0, Stats.localProfile);
		
	//	cds.put(Stats.localProfile, new ClothingDisplay(100,250,Stats.localProfile));
		
		int x=100;
		int y=250;
		
		int x1=x;
		int y1=y;

		for(PlayerProfile p : Stats.Connected){


			ClothingDisplay d = new ClothingDisplay(x,y,p);
			cds.put(p, d);
			
			if(!p.getName().contentEquals(Stats.localProfile.getName()) && p.isAlive()){
			
			final PlayerButton b =  new PlayerButton(x1, y1, p.getName(),null);
			
			b.setClick( new Runnable(){
				@Override
				public void run(){
					selectPlayer(b);
				}
			});
			
			buttons.put(p, b);
			
			x1+=150;
			if(x1>700) y1+=50;
			}
			
			x+=50;
			if(x>700) y+=150;
		}
	}

	@Override
	public void packetReceived(PacketType type, String msg) {

		
		switch(type){
		case LIST:{
			Stats.updateConnected(msg);
			this.setupClothingDisplays();
		}
		break;
		case MINIGAME:{
			Packet03Minigame pm = new Packet03Minigame(msg);
			
			cm = Minigame.getMinigame(pm.getName(), pm.getArgs());
			cd = 5;
		}
		break;
		case WINNER:{
			cm = null;
			
			winner = msg+" heeft gewonnen!";
			
			
			text = winner;
			disC = Color.green;
			textSize = 20;
			
			if(msg.contentEquals("De Moordenaar") && murd){
				murdWin();
			}else if(msg.contentEquals(Stats.localProfile.getName()) && dead){
				text = "Je leeft weer!";
			}

			disText = 400;
		}
		break;
		case END:{
			text = "De Moordenaar was "+msg;
			disC = Color.red;
			textSize = 30;
			disText = 400;
			disHint = 0;
		}
		break;
		case START:{
			init();
			disText = 0;
		}
		break;
		case MURDERER:{
			murd = true;
			
			text = this.murdText;
			disC = Color.red;
			textSize = 30;
			disText = 600;
		}
		break;
		case HINT:{
			Packet11Hint d = new Packet11Hint(msg);
			
			int hintSlot = d.getSlot();
			Color hintC = d.getColor();
			
			switch(hintSlot){
			case 0:{
				hintImg = Main.Main.imageloader.getClothing("Hat.png", hintC);
				slotName = "deze kleur pet";
			}
			break;
			case 1:{
				hintImg = Main.Main.imageloader.getClothing("Vest.png", hintC);
				slotName = "deze kleur vest";
			}
			break;
			case 2:{
				hintImg = Main.Main.imageloader.getClothing("Pants.png", hintC);
				slotName = "deze kleur broek";
			}
			break;
			case 3:{
				hintImg = Main.Main.imageloader.getClothing("Boots.png", hintC);
				slotName = "deze kleur schoenen";
			}
			break;
		}
			
			disHint = 1200;
		}
		break;
		case VICTIM:{
			if(msg.contentEquals(Stats.localProfile.getName())) msg = "Jij";
			text = msg+" is vermoord!";
			disC = Color.red;
			textSize = 40;
			disText = 400;
			disHint = 0;
		}
		break;
		case STARTCHOICE:{
			if(!murd) choice = true;
		}
		break;
		case COUNTDOWN:{
			cd = Integer.parseInt(msg);
		}
		break;
		}
	}

}
