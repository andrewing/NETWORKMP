
package view;

import java.util.*;

public class DrawedCard {
	private String[] face = {"2","3","4","5","6","7","8","9","10","11","12","13","A"};
	private String[] suits = {"D","H","S","C"};
	
	public String faceRandomizer() {
		
		Random r = new Random();
		int index = r.nextInt(face.length);
		
		return face[index];
	}
	
	public String suitsRandomizer() {
		Random r = new Random();
		int index = r.nextInt(suits.length);
		return suits[index];
	}
	
	public String getDrawedCard() {
		return "/" + this.faceRandomizer() + this.suitsRandomizer()+ ".png";
		
	}
	
	public String toString(){
		return this.faceRandomizer() + this.suitsRandomizer();
	}
	
}
