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
		Player p = bjg.findPlayer(player.getName());
		bjg.getPlayers().remove(p);
	}

	@Override
	public void set(BlackJackGame bjg) {
		
	}

	@Override
	public void statusPrint() {
		System.out.println("PLAYER " + player.getName() + " LEFT THE GAME");
		
	}

}
