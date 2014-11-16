package Util;

import java.awt.Color;
import java.util.Random;

public class Background {

	private Color background;
	private int blue = 40;
	private int targetBlue = 40;
	private int count = 0;
	private Random r;
	
	
	public Background(){
		r = new Random();
		targetBlue = r.nextInt(40)+21;
	}
	
	public void update(){
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
		background = new Color(0, 51, blue);
	}
	
	public Color getColor(){
		return background;
	}
	
	
}
