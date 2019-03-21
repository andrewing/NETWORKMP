package model;

import java.util.*;


public class Deck {
	private List<Card> cards;
	public Deck() {
		cards = new Stack<Card>();
	}
	
	public void initializeStandardDeck() {
		String[] faces = {"2","3","4","5","6","7","8","9","10","11","12","13","A"};
		String[] suits = {"D","H","S","C"};
		for(int i = 0; i < faces.length; i++) {
			for(int j = 0; j < suits.length; j++) {
				cards.add(new Card(faces[i], suits[j]));
			}
		}
		shuffleDeck();
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cards);
	}
	
	public Card drawCard() {
		return ((Stack<Card>)cards).pop();
	}
}

