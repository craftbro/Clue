package Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Util.Mouse;

public class Button extends GComponent{

	
	//Test
	
	private String text;
	private int textSize;
	private Runnable click;
	private Color c = new Color(64, 64, 64, 200);
	private Rectangle rect;
	private boolean centered = false;
	
	private boolean hovering = false;
	private boolean clicking = false;
	
	int width = -1;
	
	int addWhite = 0;
	
	public Button(int x, int y, String text, int textSize, Runnable click) {
		super(x, y);
		
		this.text = text;
		this.textSize = textSize;
		this.click = click;
	}
	
	public Button(int x, int y, String text, int textSize, boolean centered, Runnable click) {
		super(x, y);
		
		this.centered = centered;
		this.text = text;
		this.textSize = textSize;
		this.click = click;
	}
	
	private void calcWidth(Graphics2D g){
		FontMetrics fm = g.getFontMetrics();
		
		int w = fm.stringWidth(text);
		width = w + 10;
		
		int rx = x;
		int ry = y;
		
		if(centered){
			rx = (int) (x-((double)width/2));
			ry = (int) (y-((double)textSize/2));
		}
		
		rect = new Rectangle(rx, ry, width, textSize);		
	}
	
	private void checkColors(Graphics2D g){
		hovering = rect.contains(Mouse.getMouseLocation());
	
		if(hovering){
			if(addWhite < 40){
				addWhite ++;
			}else if(addWhite > 30){
				addWhite --;
			}
		}else{
			if(addWhite > 0){
				addWhite--;
			}
		}
		
		if(hovering && Mouse.isMouseDown()){
			if(!clicking){
				addWhite = 80;
				clicking = true;
				click.run();
			}
		}else if(clicking){
			clicking = false;
		}
		
		g.setColor(new Color(c.getRed()+addWhite, c.getGreen()+addWhite, c.getBlue()+addWhite, 200));
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(new Font("Modern No. 20", Font.BOLD, textSize));
						
		if(width < 0) calcWidth(g);		
		
		int rx = x;
		int ry = y;
		
		if(centered){
			rx = (int) (x-((double)width/2));
			ry = (int) (y-((double)textSize/2));
		}

		checkColors(g);
		
		g.fillRect(rx, ry, width, textSize);
		
		g.setColor(new Color(255, 255, 255));
		
		g.drawString(text, rx+5, ry+textSize-(textSize/6));		
	}

	
	
}
