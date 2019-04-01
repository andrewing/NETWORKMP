package driver;

import javax.swing.JOptionPane;
import controller.BlackjackController;
import model.*;
import udpserverclient.TCPClient;
import udpserverclient.TCPServer;
import view.BlackjackGUI;

public class BlackJackGameDriver {
	public static void main(String[] args) {
		String[] options = {"Server", "Client"};
		int a = JOptionPane.showOptionDialog(null, "***************************", "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);
		
		if(a == 0) {
			BlackJackGame bjg2 = new BlackJackGame();
			TCPServer ser = new TCPServer(5000, bjg2);
			new Thread(ser, "server").start();
		}else if(a == 1){
			BlackJackGame bjg = new BlackJackGame();
			TCPClient client = new TCPClient("localhost", 5000, bjg);
			BlackjackController bc = new BlackjackController(new BlackjackGUI(), client);
		}
	}
}
