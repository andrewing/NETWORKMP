package model;

import java.util.List;


public class Dealer extends Player {
	private Deck deck;
	
	public Dealer() {
		super("DEALER");
		deck = new Deck();
		deck.initializeStandardDeck();
	}
	
	public Card deal() {
		return deck.drawCard();
	}

}
