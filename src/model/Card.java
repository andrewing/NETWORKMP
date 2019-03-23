package model;

public class Card {
	private String suit;
	private String face;
	
	public Card(String face, String suit) {
		this.suit = suit;
		this.face = face;
	}
	
	public Card() {
		
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}
}
