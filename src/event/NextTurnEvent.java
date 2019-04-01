package event;

import model.BlackJackGame;
import model.Player;

public class NextTurnEvent extends Event{
	Player player;
	public NextTurnEvent(Player player) {
		this.player = player;
	}
	
	@Override
	public void execute(BlackJackGame bjg) {
		Player p = bjg.findPlayer(player.getName());
		p.reset();
	}

	@Override
	public void set(BlackJackGame bjg) {
		
	}

	@Override
	public void statusPrint() {
		// TODO Auto-generated method stub
		
	}

}
