package model;

import java.util.*;

public class Player {
	private String name;
	private ArrayList<Card> cardsOnHand;
	
	public Player(String name, ArrayList<Card> hand) {
		this.name = name;
		this.cardsOnHand = hand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Card> getCardsOnHand() {
		return cardsOnHand;
	}

	public void setCardsOnHand(ArrayList<Card> cardsOnHand) {
		this.cardsOnHand = cardsOnHand;
	}
	
	public int totalValue() {
		int s;
		int sum = 0;
		
		for(int i=0; i<cardsOnHand.size(); i++) {
			s = Integer.parseInt(cardsOnHand.get(i).getSuit());
			sum = sum+s;	
		}
		
		return sum;
	}
}
