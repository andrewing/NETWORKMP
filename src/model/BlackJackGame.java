package model;

import java.io.Serializable;
import java.util.*;
import event.*;
public class BlackJackGame implements Serializable{
	private static int games = 0;
	private Dealer dealer;
	private List<Player> players;

	public BlackJackGame() {
		games++;
		dealer = new Dealer();
		players = new ArrayList<>();
	}
	
	public static int getGames() {
		return games;
	}

	public static void setGames(int games) {
		BlackJackGame.games = games;
	}

	public synchronized Dealer getDealer() {
		return dealer;
	}

	public synchronized void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public synchronized void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public Player findPlayer(int id) {
		for(Player a: players) {
			if(a.getId() == id) {
				return a;
			}
		}
		return null;
	}


}
