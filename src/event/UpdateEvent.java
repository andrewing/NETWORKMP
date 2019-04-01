package event;

import model.BlackJackGame;
import model.Player;

import java.util.*;
public class UpdateEvent extends Event{
	private List<Player> opponents;

	public UpdateEvent(Player...players) {
		opponents = new ArrayList<>();
		for(Player p: players)
			opponents.add(p);
	}

	public void execute(BlackJackGame bjg) {
		for(Player o: opponents) {
			boolean exists = false;
			for(Player p: bjg.getPlayers()) {
				if(o.getName().equals(p.getName())) {
					p.setAll(o);
					exists = true;
				}
			}
		}
	}

	public void set(BlackJackGame bjg) {
		for(Player p: bjg.getPlayers()) {
			boolean exists = false;
			for(Player o: opponents) {
				if(p.getName().equals(o.getName())) {
					o.setAll(p);
					exists = true;
				}
			}
		}
	}

	public void statusPrint() {

	}
}
