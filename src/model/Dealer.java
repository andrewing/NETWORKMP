package model;

import java.io.Serializable;

public class Dealer extends Player implements Serializable{
	private Deck deck;
	
	public Dealer() {
		super("DEALER", "");
		deck = new Deck();
		deck.initializeStandardDeck();
	}
	
	public synchronized Card deal() {
		return deck.drawCard();
	}
	
	public void reset() {
		super.reset();
		deck.initializeStandardDeck();
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public static void main(String[] args) {
		Dealer dealer = new Dealer();
		System.out.println(dealer.deal().getFace());
		dealer.reset();
		System.out.println(dealer.deal().getFace());
	
	}
}
