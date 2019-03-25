package event;

import model.BlackJackGame;
import model.Player;

public class EditProfileEvent extends Event{
	private Player player;
	public EditProfileEvent(Player player) {
		this.player = player;
	}
	
	
	@Override
	public void execute(BlackJackGame bjg) {
		Player old = bjg.findPlayer(player.getId());
		old.setAll(player);
	}

	@Override
	public void set(BlackJackGame bjg) {
	
	}

	@Override
	public void statusPrint() {
		// TODO Auto-generated method stub
		
	}
	
}
