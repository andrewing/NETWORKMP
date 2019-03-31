package event;

import model.BlackJackGame;
import model.Card;
import model.Player;

public class HitEvent extends Event{
	private Card card;
	private Player player;

	public HitEvent(Player player) {
		this.player = player;
	}
	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public void execute(BlackJackGame bjg) {
		Player p = bjg.findPlayer(this.player.getName());
		p.hit(card);
	}

	

	@Override
	public void set(BlackJackGame bjg) {
		this.card = bjg.getDealer().deal();
	}

	@Override
	public void statusPrint() {
		System.out.println("PLAYER: " + player.getName() + " GOT " + card.getFace() + card.getSuit());
	}
}
