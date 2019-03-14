package View;

public class BlackjackDriver {
	
	public static void main(String args[]) {
		
		BlackjackGUI gameView = new BlackjackGUI();
		
		BlackjackController bc = new BlackjackController(gameView);
		
		
	}
}
