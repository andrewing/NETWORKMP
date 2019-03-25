package view;


import javax.swing.ImageIcon;

import controller.BlackjackController;

public class BlackjackDriver {
	
	public static void main(String args[]) {
		
		BlackjackGUI gameView = new BlackjackGUI();
		BlackjackController bc = new BlackjackController(gameView);
		
	}
}
