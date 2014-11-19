package Util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Util {

	
	public static void drawRoundedRect(Graphics g, int x, int y, int width, int height){
		g.fillRect(x+2, y, width-3, height);
		
		g.drawLine(x+1, y+1, x+1, y+height-2);
		g.drawLine(x, y+3, x, y+height-3);
		

		g.drawLine(x+width-1, y+1, x+width-1, y+height-2);
		g.drawLine(x+width, y+3, x+width, y+height-3);
	}
	
	public static void drawClothes(Graphics g, PlayerProfile profile, int x,  int y){
		BufferedImage hat = Main.Main.imageloader.getClothing("Hat.png", profile.getClothing()[0]);
		BufferedImage vest = Main.Main.imageloader.getClothing("Vest.png", profile.getClothing()[1]);
		BufferedImage pants = Main.Main.imageloader.getClothing("Pants.png", profile.getClothing()[2]);
		BufferedImage boots = Main.Main.imageloader.getClothing("Boots.png", profile.getClothing()[3]);

		
		g.drawImage(hat, x,  y, 32, 32, null);	
		g.drawImage(vest, x, y+17 , 32, 32, null);	
		g.drawImage(pants, x, y+34, 32, 32, null);	
		g.drawImage(boots, x, y+51, 32, 32, null);	


	}
	
	public static void drawCompact(Graphics g, String s, int x, int y){
		List<String> lines = new ArrayList<String>();
		
		while(s.length() > 50){
			int i = 49;
			
			while(s.toCharArray()[i] != ' ' || g.getFontMetrics().stringWidth(s.substring(0, i)) >= Main.Main.WIDTH){
				i--;
			}
			
			String a = s.substring(0, i);
			
			lines.add(a);
			
			s = s.substring(i, s.length());
		}
		
		lines.add(s);
		
		int i = 0;
		
		for(String line : lines){
			int w = g.getFontMetrics().stringWidth(line)/2;
			g.drawString(line, x-w, y+i);
			
			i+=g.getFont().getSize();
		}
	}
	
}
