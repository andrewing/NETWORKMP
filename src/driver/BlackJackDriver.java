package driver;


import java.util.concurrent.TimeUnit;

import model.BlackJackGame;
import model.Player;

public class BlackJackDriver {
	public static void main(String[] args) throws InterruptedException {
		BlackJackGame bjg = new BlackJackGame(false);
		Thread t = new Thread(bjg);
		t.start();

		bjg.playerJoin(new Player("Hello", ""));
		bjg.end();
	}
}
