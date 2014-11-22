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
		}else if(blue > 20) blue-=4;
		else if(blue > 0) blue--;
		if(murd){	
			if(targetRed == red){
				targetRed = r.nextInt(50)+185;
			}else if(count == (red>180?10:3)){
				if(targetRed > red){
					red++;
				}else{
					red--;
				}
				count = 0;
			}else{
				count++;
			}
		}else if(red > 20){
			red-=4;
		}else if(red > 0){
			red--;
		}
		
		int g = red>51?0:50-red;
			
		background = new Color(red, g, blue);
	}
	
	public void setMurdered(){
		System.out.println("murd");
		murd = true;
		count = 0;
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
