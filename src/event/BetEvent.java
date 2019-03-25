package event;

import model.BlackJackGame;
import model.Player;

public class BetEvent extends Event{
	private Player player;
	private int bet;
	public BetEvent(int bet, Player player) {
		this.bet = bet;
		this.player = player;
	}
	
	@Override
	public void execute(BlackJackGame bjg) {
		player = bjg.findPlayer(player.getId());
		player.bet(bet);
	}

	@Override
	public void set(BlackJackGame bjg) {
		
	}

	@Override
	public void statusPrint() {

		System.out.println("BET EVENT");
	}

	
}
