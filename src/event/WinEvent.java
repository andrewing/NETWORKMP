package event;

import model.BlackJackGame;
import model.Player;

public class WinEvent extends Event{
	private Player player;
	private int winnings;
	public WinEvent(Player player, int winnings) {
		this.player = player;
		this.winnings = winnings;
	}
	@Override
	public void execute(BlackJackGame bjg) {
		Player p = bjg.findPlayer(player.getName());
		p.win(winnings);
	}

	@Override
	public void set(BlackJackGame bjg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statusPrint() {
		
	}
	
	
	
	
}
