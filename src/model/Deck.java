package model;

import java.io.Serializable;
import java.util.*;


public class Deck implements Serializable{
	private List<Card> cards;
	public Deck() {
		cards = new Stack<Card>();
	}
	
	public void initializeStandardDeck() {
		String[] faces = {"2","3","4","5","6","7","8","9","10","11","12","13","A"};
		String[] suits = {"D","H","S","C"};
		for(String face: faces)
			for(String suit: suits)
				cards.add(new Card(face, suit));
		shuffleDeck();
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cards);
	}
	
	public synchronized Card drawCard() {
		return ((Stack<Card>)cards).pop();
	}
	
	
}

