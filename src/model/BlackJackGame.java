package model;

import java.util.*;

public class BlackJackGame {
	private Dealer dealer;
	private List<Player> players;
	
	public BlackJackGame() {
		dealer = new Dealer();
		players = new ArrayList<>();
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
	
	
	
	
}
