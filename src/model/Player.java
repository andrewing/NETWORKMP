package model;

import java.util.*;

public class Player {
	private String name;
	private int points;
	private List<Card> hand;
	
	public Player(String name) {
		this.name = name;
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
	
	public void hit(Card card) {
		hand.add(card);
	}

	
	public int totalValue() {
		int sum = 0;
		int aces = 0;
		for(int i=0; i<hand.size(); i++) {
			String face = hand.get(i).getFace();
			if(face == "A") {
				aces++;
			}
			sum += Integer.parseInt(face);
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
	
	
}
