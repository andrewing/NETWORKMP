package event;
import java.util.*;

import model.BlackJackGame;
import model.Player;
public class JoinEvent extends Event{
	private List<Player> players;

	public JoinEvent() {
		players = new ArrayList<>();
	}

	public JoinEvent(Player player) {
		this();
		players.add(player);
	}
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}


	@Override
	public void execute(BlackJackGame bjg) {
		for(Player a: players) {
			if(!bjg.getPlayers().contains(a)) {
				bjg.getPlayers().add(a);
			}
		}
	}

	@Override
	public void set(BlackJackGame bjg) {
		for(Player p: bjg.getPlayers())
			players.add(p);

	}

	@Override
	public void statusPrint() {
		System.out.println("JOIN EVENT");
	}
}
