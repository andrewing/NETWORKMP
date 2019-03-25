package udpserverclient;

import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import event.BetEvent;
import event.EditProfileEvent;
import event.Event;
import event.HitEvent;
import event.JoinEvent;
import event.LeaveEvent;
import model.BlackJackGame;
import model.Player;

public class TCPClient implements Runnable{
	private static final long delay = 2000L;
	private Socket socket;
	private String ip;
	private int port;
	private BlackJackGame bjg;
	private volatile boolean exit = false;
	public TCPClient(String ip, int port,BlackJackGame bjg) {
		this.bjg = bjg;
		this.ip = ip;
		this.port = port;
		try {
			this.socket = new Socket(InetAddress.getByName(ip), port);
		} catch (IOException e) {
		}
	}
	public void run() {
		while(!exit) {
			try {
				ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
				Event event =(Event)inFromServer.readObject();
				event.execute(bjg);
				System.out.println("CLIENT EXECUTE");
			}catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		exit = true;
	}

	public void send(Event event) {
		try {
			ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
			
			outToServer.writeObject(event);
			outToServer.flush();
			event.statusPrint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public BlackJackGame getBJG() {
		return bjg;
	}






	public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
		BlackJackGame bjg = new BlackJackGame();
		Player player = new Player("Andrew", "img");

		TCPClient client = new TCPClient("localhost", 5000, bjg);
		new Thread(client, "Client").start();
		Event join = new JoinEvent(player);
		client.send(join);
		//		TimeUnit.MILLISECONDS.sleep(5000);
//		player.setName("Rae");
		Event edit = new EditProfileEvent(player);
//		client.send(edit);
		//		TimeUnit.MILLISECONDS.sleep(5000);

		//		Event leave = new LeaveEvent(player);
		//		client.send(leave);
//		Event bet = new BetEvent(20, player);
//		client.send(bet);
		TimeUnit.MILLISECONDS.sleep(2000);

		System.out.println(bjg.getPlayers().size());
		for(Player a: bjg.getPlayers()){
			System.out.println(a.getName());
			System.out.println(a.getPoints());
		}
	}




}
