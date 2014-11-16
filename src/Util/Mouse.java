package Util;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{

	private static Point location = new Point(-1, -1);
	private static boolean down = false;
	
	public static Point getMouseLocation(){
		return location;
	}
	
	public static boolean isMouseDown(){
		return down;
	}
	
	@Override
	public void mouseDragged(MouseEvent m) {
		
	}

	@Override
	public void mouseMoved(MouseEvent m) {
		location.setLocation(m.getX(), m.getY());
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		
	}

	@Override
	public void mouseEntered(MouseEvent m) {
		
	}

	@Override
	public void mouseExited(MouseEvent m) {
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		down = true;	
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		down = false;
	}

}
