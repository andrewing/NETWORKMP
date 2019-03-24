package model;

import java.util.*;

import udpserverclient.UDPClient;
import udpserverclient.UDPServer;

public class BlackJackGame implements Runnable{
	private Dealer dealer;
	private List<Player> players;
	
	private UDPClient udpClient;
	private UDPServer udpServer;
	
	private boolean isServer;
	public BlackJackGame(boolean isServer) {
		dealer = new Dealer();
		players = new ArrayList<>();
		this.isServer = isServer;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public void run() {
		new Thread(this).start();
		if(isServer) {
			udpServer = new UDPServer(this);
			udpServer.start();
		}else {
			udpClient = new UDPClient(this, "localhost");
			udpClient.start();
		}
	}
	
	
	
	
}
