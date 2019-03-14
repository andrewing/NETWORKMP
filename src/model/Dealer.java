package model;

import java.util.List;

public class Dealer extends Player {
	private Deck deck;
	
	public Dealer() {
		deck = new Deck();
		super("DEALER");
		deck.initializeStandardDeck();
	}
	
	

}
