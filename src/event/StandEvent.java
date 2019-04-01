package event;

import model.BlackJackGame;
import model.Player;

public class StandEvent extends Event{
	private Player player;
	private boolean stand;
	public StandEvent(Player player, boolean stand) {
		this.player = player;
		this.stand = stand;
	}

	
	@Override
	public void execute(BlackJackGame bjg) {
		Player p = bjg.findPlayer(player.getName());
		p.setStand(stand);
	}

	@Override
	public void set(BlackJackGame bjg) {
		
	}

	@Override
	public void statusPrint() {
		System.out.println("PLAYER " + player.getName() + " STANDS");
	}
	
}
