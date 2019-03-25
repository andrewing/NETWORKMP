package event;

import model.BlackJackGame;
import model.Player;

public class LeaveEvent extends Event{
	private Player player;
	
	public LeaveEvent(Player player) {
		this.player = player;
	}
	
	@Override
	public void execute(BlackJackGame bjg) {
		player = bjg.findPlayer(player.getId());
		bjg.getPlayers().remove(player);
	}

	@Override
	public void set(BlackJackGame bjg) {
		
	}

	@Override
	public void statusPrint() {
		System.out.println("LEAVE EVENT");
	}

}
