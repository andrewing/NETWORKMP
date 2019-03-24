package model;

import java.util.*;

import udpserverclient.UDPClient;
import udpserverclient.UDPServer;

public class BlackJackGame{
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

	public synchronized Dealer getDealer() {
		return dealer;
	}

	public synchronized void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public synchronized List<Player> getPlayers() {
		return players;
	}

	public synchronized void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void start() {
		if(isServer) {
			udpServer = new UDPServer(this);
			udpServer.start();
		}else {
			udpClient = new UDPClient(this, "localhost");
			udpClient.start();
		}
	}
	
	public void end() {
		
		udpClient.sendData("end".getBytes());
		
	}
	
	
	
	
}
