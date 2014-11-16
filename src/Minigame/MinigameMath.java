package Minigame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MinigameMath extends Minigame{

	int i1;
	int i2;
	int result;
	
	String mod;
	
	private List<Character> nums;
	private String text;
	private int white;
	private boolean type;
	
	public MinigameMath() {
		super("Rekenen",
			  "Typ zo snel mogelijk het antwoord van de som in en druk op enter om te kijken of het goed is");
		
		nums = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	}

	@Override
	public void init(HashMap<String, String> args) {
		i1 =  Integer.parseInt(args.get("i1"));
		i2 = Integer.parseInt(args.get("i2"));
		result = Integer.parseInt(args.get("result"));
		mod = args.get("mod");
		
		text = "";
		white = 255;
		type = true;
	}

	@Override
	public void draw(Graphics g) {
		g.setFont(new Font("Modern No. 20", Font.BOLD, 60));
		
		g.setColor(new Color(255,255,255,200));
		
		String draw = i1+""+mod+""+i2;
		
		int w = (g.getFontMetrics().stringWidth(draw))/2;
		
		g.drawString(draw, 400-w, 300);
		
		
		if(white < 255){
			white +=15;
			if(white >= 255){
				white = 255;
				type = true;
				text = "";
			}
		}
		
		int a = type?255:255-white;
		int c = type?255:0;
		
		g.setColor(new Color(255,c,c,a));
		
		int w1 = (g.getFontMetrics().stringWidth(text))/2;
		
		g.drawString(text, 400-w1, 400);
	}
	
	private void onEnter(){
		int answer = Integer.parseInt(text);
		
		if(answer == result){
			win();
		}else{
			white = 0;
			type = false;
		}
	}

	@Override
	public void keypressed(KeyEvent e) {
		char c = e.getKeyChar();
		
		if(!type) return;
		
		if(nums.contains(c)){
			if(text.length() < 4) text = text + c;
		}else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			if(text.length() > 0) text = text.substring(0, text.length()-1);
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			onEnter();
		}
	}

	@Override
	public void keyrelease(KeyEvent e) {
		
	}

}
