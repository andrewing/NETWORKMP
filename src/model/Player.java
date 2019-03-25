package model;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable{
	private int id;
	private static int players = 0;
	private String name;
	private String imgPath;
	private int wins;
	private int games;
	private int points;
	private List<Card> hand;
	
	public Player(String name, String imgPath) {
		id = players;
		players++;
		hand = new ArrayList<>();
		this.name = name;
		this.imgPath = imgPath;
		wins = 0;
		games = 0;
		points = 300;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getPlayers() {
		return players;
	}

	public static void setPlayers(int players) {
		Player.players = players;
	}

	public void setAll(Player player) {
		this.games = player.getGames();
		this.hand = player.getHand();
		this.imgPath = player.getImgPath();
		this.name = player.getName();
		this.points = player.getPoints();
		this.wins = player.getWins();
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
	
	public int getHandCount() {
		return hand.size();
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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
	
	public void hit(Card card) {
		hand.add(card);
	}
	
	public void stand() {
		
	}
	
	public void reset() {
		hand.removeAll(hand);
	}

	public int bet(int bet) {
		games++;
		if(this.points > 0) {
			this.points -= bet;
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
			if(face == "A") {
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
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Player)obj).id;
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
