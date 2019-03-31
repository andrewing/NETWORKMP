package udpserverclient;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.BlackJackGame;
import model.Player;
import java.util.*;

public class TCPServer implements Runnable{
	private List<Responder> responders;
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private BlackJackGame bjg;
	private volatile boolean exit = false;
	private static int counter = 0;
	public TCPServer(int port, BlackJackGame bjg) {
		try {
			this.bjg = bjg;
			serverSocket = new ServerSocket(port);
			responders = new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		int counter = 0;
		while(!exit) {
			counter++;
			TCPServer.counter = counter;
			try {
				connectionSocket = serverSocket.accept();
				handshake();
				System.out.println("CLIENT # [" +counter+ "] ACCEPTED");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Responder r = new Responder(connectionSocket,counter,bjg);
			synchronized(responders) {
				responders.add(r);
			}
			new Thread(r).start();
		}
	}
	
	public void stop() {
		exit = true;
	}
	
	public void handshake() throws IOException {
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		boolean connected = false;
		while(!connected) {
			int synFromClient = inFromClient.read();
			System.out.println("RECEIVED FROM CLIENT # [" + counter + "]: SYN # " + synFromClient);
			int synServer = new Random().nextInt(127);
			int ackToClient = synFromClient + 1;
			outToClient.write(ackToClient);
			outToClient.write(synServer);
			System.out.println("SENDING TO CLIENT # [" + counter + "]: SYN # " + synServer + " / ACK # " + ackToClient);
			int ackFromClient = inFromClient.read();
			System.out.println("RECEIVED FROM CLIENT # [" + counter + "]: ACK # " + ackFromClient);
			if(ackFromClient == synServer + 1){
				connected = true;
			}
		}
		outToClient.flush();
	}
	
	public List<Responder> getResponders() {
		return responders;
	}

	public void setResponders(List<Responder> responders) {
		this.responders = responders;
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
		
	}

	
}
