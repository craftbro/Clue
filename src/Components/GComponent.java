package Components;

import java.awt.Graphics2D;

public abstract class GComponent {

	protected int x, y;
	
	public GComponent(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw(Graphics2D g);
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}
