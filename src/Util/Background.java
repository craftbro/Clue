package Util;

import java.awt.Color;
import java.util.Random;

public class Background {

	private Color background;
	private int blue = 40;
	private int targetBlue = 40;
	private int red = 0;
	private int targetRed = 0;
	private int count = 0;
	private Random r;
	private boolean murd = false;
	
	
	public Background(){
		r = new Random();
		targetBlue = r.nextInt(40)+21;
	}
	
	public void update(){
		if(!murd){
		if(targetBlue == blue){
			targetBlue = r.nextInt(40)+21;
		}else if(count == 40){
			if(targetBlue > blue){
				blue++;
			}else{
				blue--;
			}
			count = 0;
		}else{
			count++;
		}
		}
		
			if(targetRed == red && murd){
				targetRed = r.nextInt(50)+185;
			}else if(count == 10){
				if(targetRed > red){
					red++;
				}else if(targetRed < red){
					red--;
				}
				count = 0;
			}else{
				count++;
			}
		
		background = new Color(red, 51, blue);
	}
	
	public void setMurdered(){
		murd = true;
		targetBlue = 0;
		targetRed = r.nextInt(50)+185;
	}
	
	public void setNormal(){
		murd = false;
		targetRed= 0;
		targetBlue = 40;
	}
	
	public Color getColor(){
		return background;
	}
	
	
}
