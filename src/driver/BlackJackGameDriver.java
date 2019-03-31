package driver;

import java.util.Scanner;
import java.util.*;
import java.util.concurrent.TimeUnit;

import controller.BlackjackController;
import event.*;
import model.*;
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
		}
		
		BlackJackGame bjg = new BlackJackGame();
		TCPClient client = new TCPClient("localhost", 5000, bjg);
		BlackjackController bc = new BlackjackController(new BlackjackGUI(), client);
	}
}
