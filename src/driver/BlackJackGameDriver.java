package driver;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import controller.BlackjackController;
import event.JoinEvent;
import model.BlackJackGame;
import model.Player;
import udpserverclient.TCPClient;
import udpserverclient.TCPServer;
import view.BlackjackGUI;

public class BlackJackGameDriver {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to be a server? (yes/no)");
		String a = sc.nextLine();
		sc.close();
		if(a.equalsIgnoreCase("yes")) {
			BlackJackGame bjg2 = new BlackJackGame();
			TCPServer ser = new TCPServer(5000, bjg2);
			new Thread(ser, "server").start();
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			System.out.println(bjg2.getPlayers().size());
//			for(Player player: bjg2.getPlayers()){
//				System.out.println(player.getName());
//				System.out.println(player.getPoints());
//			}
		}
		
		BlackJackGame bjg = new BlackJackGame();
		TCPClient client = new TCPClient("localhost", 5000, bjg);
		BlackjackController bc = new BlackjackController(new BlackjackGUI(), client);
		
		
	}
}
