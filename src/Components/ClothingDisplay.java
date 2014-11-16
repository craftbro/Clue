package Components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Util.PlayerProfile;

public class ClothingDisplay extends GComponent{

	private PlayerProfile profile;
	
	BufferedImage hat;
	BufferedImage vest;
	BufferedImage pants;
	BufferedImage boots;
	
	public ClothingDisplay(int x, int y, PlayerProfile profile) {
		super(x, y);
		
		this.profile = profile;
		
		hat = Main.Main.imageloader.getClothing("Hat.png", profile.getClothing()[0]);
		vest = Main.Main.imageloader.getClothing("Vest.png", profile.getClothing()[1]);
		pants = Main.Main.imageloader.getClothing("Pants.png", profile.getClothing()[2]);
		boots = Main.Main.imageloader.getClothing("Boots.png", profile.getClothing()[3]);
	}

	@Override
	public void draw(Graphics2D g) {

		
		g.drawImage(hat, x,  y, 32, 32, null);	
		g.drawImage(vest, x, y+17 , 32, 32, null);	
		g.drawImage(pants, x, y+34, 32, 32, null);	
		g.drawImage(boots, x, y+51, 32, 32, null);	

	}
	
	public PlayerProfile getProfile(){
		return profile;
	}

}
