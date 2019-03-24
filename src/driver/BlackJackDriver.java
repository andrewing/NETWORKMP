package driver;


import model.BlackJackGame;

public class BlackJackDriver {
	public static void main(String[] args) throws InterruptedException {
		BlackJackGame bjg = new BlackJackGame(false);
		Thread t = new Thread(bjg);
		t.start();
		bjg.end();
		
		

	}
}
