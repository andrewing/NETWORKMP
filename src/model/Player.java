package model;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable{
	private String name;
	private String imgPath;
	private int wins;
	private int games;
	private int points;
	private int bet;
	private int handCount;
	private boolean turn;
	private boolean stand;
	private List<Card> hand;
	
	public Player(String name, String imgPath) {
		hand = new ArrayList<>();
		this.name = name;
		this.imgPath = imgPath;
		wins = 0;
		games = 0;
		points = 300;
		bet = 0;
		turn = false;
		stand = false;
		handCount = 0;
	}

	
	public void setAll(Player player) {
		this.name = player.getName();
		this.imgPath = player.getImgPath();
		this.wins = player.getWins();
		this.games = player.getGames();
		this.hand = player.getHand();
		this.points = player.getPoints();
		this.bet = player.getBet();
		this.turn = player.isTurn();
		this.handCount = player.getHandCount();
	}

	
	public int getHandCount() {
		return handCount;
	}

	public boolean isTurn() {
		return turn;
	}


	public void setTurn(boolean turn) {
		this.turn = turn;
	}


	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> cardsOnHand) {
		this.hand = cardsOnHand;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}
	
	public synchronized void hit(Card card) {
		hand.add(0, card);
		handCount++;
	}
	
	public boolean stand() {
		stand = true;
		return true;
	}
	
	public void reset() {
		hand.removeAll(hand);
		turn = false;
		stand = false;
		bet = 0;
	}

	public int bet(int bet) {
		games++;
		if(this.points >= bet) {
			this.points -= bet;
			this.bet = bet;
			return bet;
		}
		return 0;
	}
	
	public void win(int winnings) {
		wins++;
		this.points += winnings;
	}

	public int totalValue() {
		int sum = 0;
		int aces = 0;
		for(int i=0; i<hand.size(); i++) {
			String face = hand.get(i).getFace();
			if(face.equalsIgnoreCase("A")) {
				aces++;
			}else {
				sum += Integer.parseInt(face);
			}
		}				
		while(aces > 0) {
			if(sum + 11 <= 21) {
				sum += 11;
			}else {
				sum += 1;
			}
			aces--;
		}
		return sum;
	}
	
	
	/*test*/
	public static void main(String[] args) {
		Player a = new Player("a", "");
		a.hit(new Card("A", "H"));
		a.hit(new Card("10", "H"));
		System.out.println(a.totalValue());
		a.bet(20);
		System.out.println(a.getPoints());
		a.win(40);
		System.out.println(a.getPoints());
		a.reset();
		System.out.println(a.getHandCount());
	}

	
}
