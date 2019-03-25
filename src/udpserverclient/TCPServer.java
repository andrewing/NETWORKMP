package udpserverclient;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.BlackJackGame;
import model.Player;


public class TCPServer implements Runnable{
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private BlackJackGame bjg;
	private volatile boolean exit = false;
	public TCPServer(int port, BlackJackGame bjg) {
		try {
			this.bjg = bjg;
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		int counter = 0;
		while(!exit) {
			counter++;
			try {
				connectionSocket = serverSocket.accept();
				System.out.println("CLIENT # [" +counter+ "] ACCEPTED");
			} catch (IOException e) {
				e.printStackTrace();
			}
			new Thread(new Responder(connectionSocket,counter,bjg)).start();
		}
	}
	
	public void stop() {
		exit = true;
	}
	
	public static void main(String args[]) throws IOException {
//		String clientSentence;
//		String capitalizedSentence;
//		
//		ServerSocket welcomeSocket = new ServerSocket(6789);
//		
//		while(true) {
//			Socket connectionSocket = welcomeSocket.accept();
//			 
//			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//			
//			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//			
//			clientSentence = inFromClient.readLine();
//			
//			capitalizedSentence = clientSentence.toUpperCase() + '\n';
//			
//			outToClient.writeBytes(capitalizedSentence);
//			
//		}
		
		
		BlackJackGame bjg = new BlackJackGame();
		TCPServer ser = new TCPServer(5000, bjg);
		new Thread(ser, "server").start();
		try {
			TimeUnit.MILLISECONDS.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(bjg.getPlayers().size());
		for(Player a: bjg.getPlayers()){
			System.out.println(a.getName());
			System.out.println(a.getPoints());
		}
	}

	
}
