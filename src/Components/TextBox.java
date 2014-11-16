package Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Util.Mouse;

public class TextBox extends GComponent{
	
	private int maxChars = 10;
	private int textSize = 10;
	private Rectangle rect;
	
	private boolean centered = false;
	
	private String text = "";
	
	private int width = -1;
	
	private boolean selected = false;
	
	private int c = 195;

	public TextBox(int x, int y, int maxChars, int textSize) {
		super(x, y);
		
		this.maxChars = maxChars;
		this.textSize = textSize;
	}
	
	public TextBox(int x, int y, int maxChars, int textSize, boolean centered) {
		super(x, y);
		
		this.centered = centered;
		this.maxChars = maxChars;
		this.textSize = textSize;
	}
	
	private void calculateDimensions(Graphics2D g) {	
		FontMetrics fm = g.getFontMetrics();
		
		String s = "";
		
		for(int i=0; i<maxChars; i++) s+="1";
		
		
		width = 10+fm.stringWidth(s);
		
		int rx = x;
		int ry = y;
		
		if(centered){
			rx = x-(width/2);
			ry = y-(textSize/2);
		}
		
		rect = new Rectangle(rx, ry, width, textSize);		
	}
	
	private void checkColors(Graphics2D g){
		if(Mouse.isMouseDown() && rect.contains(Mouse.getMouseLocation())){
			selected = true;
		}else if(Mouse.isMouseDown()){
			selected = false;
		}
		
		if(selected){
			if(c < 255) c+=4;
		}else if(c > 195){
			c-=4;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(new Font("Modern No. 20", Font.PLAIN, textSize));
		
		if(width < 0) calculateDimensions(g);
		
		int rx = x;
		int ry = y;
		
		if(centered){
			rx = (int) (x-((double)width/2));
			ry = (int) (y-((double)textSize/2));
		}
		
		checkColors(g);
		
		g.setColor(Color.BLACK);
		g.drawRect(rx-1, ry-1, width+2, textSize+2);
		
		g.setColor(new Color(c, c, c, c));
		
		g.fillRect(rx, ry, width, textSize);
		
		g.setColor(new Color(0, 0, 0));
		
		g.drawString(text, rx+5, ry+textSize-(textSize/6));
	}
	
	public String getText(){
		return text;
	}
	
	public void addChar(char c){
		if(text.length() >= maxChars) return;
		text+=c;
	}
	
	public void backSpace(){
		if(text.length() < 1) return;
		text = text.substring(0, text.length()-1);
	}

	public boolean isSelected() {
		return selected;
	}

	
	
}
