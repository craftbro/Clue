package Util;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	private static HashMap<String, Map<Color, BufferedImage>> buffer = new HashMap<String, Map<Color, BufferedImage>>();

	public  BufferedImage getClothing(String path, Color c){
		
		if(buffer.containsKey(path) && buffer.get(path).containsKey(c)){
			return buffer.get(path).get(c);
		}else{
		
		BufferedImage b = loadImage(path);
	
		
		for(int x=0; x<b.getWidth(); x++){
			for(int y=0; y<b.getHeight(); y++){
				Color cc = new Color(b.getRGB(x, y));
				
				int alpha = (cc.getRGB()>>24) & 0xff;
				
				if(cc.getGreen() == 255 && cc.getRed() == 0 && cc.getBlue() == 255 && alpha == 255){
					b.setRGB(x, y, c.getRGB());
				}
			}
		}
		
		buffer.get(path).put(c, b);
		
		return b;
		}
	}
	
	
	public  BufferedImage loadImage(String path){
		BufferedImage b;
		try {
			
			URL url = ImageLoader.class.getResource("/"+path);
			b = ImageIO.read(url);
			
			buffer.put(path, new HashMap<Color, BufferedImage>());
			return b;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
