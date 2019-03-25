package Controller;


import java.util.ArrayList;

import model.Dealer;
import model.Player;

public class GameLogic {
	
	private Dealer dealer = new Dealer();
	private ArrayList<Player> playersInGame = new ArrayList<Player>();
	private int totalPot;
	
	public GameLogic(Dealer dealer, ArrayList<Player>playersInGame) {
		this.dealer = dealer;
		this.playersInGame = playersInGame;
	}
	
	public void dealCards() {
		for(int i = 0; i<playersInGame.size(); i++) {
			//dealer.deal()  // Not sure where cardsOnHand will come from sa Player
			playersInGame.get(i).setHand(cardsOnHand);	//cardsOnHand is a List object
			totalPot += playersInGame.get(i).bet(10);
		}	
	}

	public void startGame() {
		int i = 0; //Player Number (e.g. Player Number 0)
		
		for(i=0;i<playersInGame.size();i++)
		{
			Player Andrew = playersInGame.get(i);
			//Player Turn - Hit or Stand
			do {
				if(/*Andrew presses Hit Button*/) {
					Andrew.hit(dealer.deal());
				}
				else {
					Andrew.stand();
				}
			}while(playersInGame.get(i).totalValue() <= 21 && playersInGame.get(i).totalValue() > 0 /*|| While the player hasn't pressed Stand*/);	
		}
	}	
	
	public Player getWinner() {
		int i;
		int maxIndex = 0;
		int max = playersInGame.get(0).totalValue();
		
		for(i = 0; i < playersInGame.size(); i++) {
			if(playersInGame.get(i).totalValue() > max) {
				max = playersInGame.get(i).totalValue();
				maxIndex = i;
			}
		}
		
		playersInGame.get(maxIndex).addPoints(totalPot);
		return playersInGame.get(maxIndex);
	}
}
