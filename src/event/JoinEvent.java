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
		for(Player p: players) {
			boolean found = false;
			for(Player pbjg: bjg.getPlayers()) {
				if(pbjg.getName().equals(p.getName())) {
					found = true;
				}
			}
			if(!found){
				bjg.getPlayers().add(p);
			}
		}
	}

	@Override
	public void set(BlackJackGame bjg) {
		for(Player p: bjg.getPlayers()) {
			boolean found = false;
			for(Player pe: players) {
				if(p.getName().equals(pe.getName())) {
					found = true;
				}
			}
			if(!found) {
				players.add(p);
			}
		}
	}

	@Override
	public void statusPrint() {
		for(Player p: players)
			System.out.println(p.getName() + " JOINED");
	}
}
