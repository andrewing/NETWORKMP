package event;

import model.BlackJackGame;
import model.Player;

public class EditProfileEvent extends Event{
	private Player player;
	private String img;
	public EditProfileEvent(Player player, String img) {
		this.player = player;
		this.img = img;
	}
	
	
	@Override
	public void execute(BlackJackGame bjg) {
		Player p = bjg.findPlayer(player.getName());
		p.setImgPath(img);
	}

	@Override
	public void set(BlackJackGame bjg) {
		
	}

	@Override
	public void statusPrint() {
		System.out.println(player.getName() + " CHANGED AVATAR");
		
		
	}
	
}
