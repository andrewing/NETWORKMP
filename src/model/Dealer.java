package model;

import java.util.List;

public class Dealer extends Player {
	private Deck deck;

	private String noki;
	
	public Dealer() {
		deck = new Deck();
		super("DEALER");
		deck.initializeStandardDeck();
	}
	
	

}
