package Main;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.GameStateManager;
import Minigame.Minigame;
import Net.GameClient;
import Util.Background;
import Util.ImageLoader;
import Util.Mouse;

public class Main extends Applet implements Runnable, KeyListener{
	
	//Game Thread
	Thread thread;
	private boolean running = false;;
	private int FPS = 2000;
	private double skipTicks = 1e+9 / FPS;
	private long startSec = System.currentTimeMillis();
	
	//Double Buffering
	private Image i;
	private Graphics dg;
	
	//Dimensions
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static Dimension SIZE = new Dimension(WIDTH, HEIGHT);
	
	
	//Net
	public static GameClient client;
	
	//Util
	public static GameStateManager gsm;
	public static Mouse mouse;
	public static Background bg;
	public static ImageLoader imageloader;
	
	@Override
	public void init(){

	}
	
	@Override
	public void start(){
		if(running) return;
		running = true;
		
		
		imageloader = new ImageLoader();
		mouse = new Mouse();
		bg = new Background();
		gsm = new GameStateManager();
		
		
		Minigame.setup();
		
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(this);
		
		client = new GameClient(this, this.getParameter("ServerIP"));
		client.start();
		
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void stop(){
		running = false;
	}
	
	@Override
	public void destroy(){
		running = false;
	}
	
	@Override
	public void update(Graphics g){
		
		gsm.update();
		bg.update();
		
		
		if(i==null){
			i = createImage(WIDTH, HEIGHT);
			dg = i.getGraphics();
		}
		
		dg.setColor(getBackground());
		dg.fillRect(0, 0, WIDTH, HEIGHT);
		
		dg.setColor(getForeground());
		paint(dg);
		
		g.drawImage(i, 0, 0, this);
	}
	
	private void updateSec(){
		gsm.updateSec();
	}
	
	@Override
	public void paint(Graphics g1){
		Graphics2D g = (Graphics2D) g1;
		
		if(g!=null && gsm != null) gsm.draw(g);
	}
	
	
	@Override
	public void run() {
		long start = System.nanoTime();
		long elapsed = 0;
		long wait = 0;

		
		while(running){
			
			
			start = System.nanoTime();
			
			repaint();
			
			elapsed = System.nanoTime() - start;
			
			wait = (long) (skipTicks - elapsed);
			
			
			
			if(System.nanoTime()-startSec >= 1e+9){
				updateSec();
				startSec = System.nanoTime();
			}
			
		//	System.out.println("wait: "+wait+", skipTicks: "+skipTicks+", elapsed: "+elapsed);
			
			if(wait>0)
				try {
					Thread.sleep((long) (wait/1e+6));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		gsm.keyPressed(k);
	}

	@Override
	public void keyReleased(KeyEvent k) {
		gsm.keyReleased(k);
	}

	@Override
	public void keyTyped(KeyEvent k) {
		
	}
	

}
