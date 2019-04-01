package event;

import java.util.ArrayList;
import java.util.List;

import model.BlackJackGame;
import model.Player;

public class ResetEvent extends Event{
	private List<Player> players;
	
	public ResetEvent(List<Player> players) {
		this.players = new ArrayList<>();
		this.players.addAll(players);
	}
	
	@Override
	public void execute(BlackJackGame bjg) {
		for(Player p: players) {
			Player pbjg = bjg.findPlayer(p.getName());
			pbjg.reset();
		}
	}

	@Override
	public void set(BlackJackGame bjg) {
		
	}

	@Override
	public void statusPrint() {
		System.out.println("NEW ROUND");
	}
	
}
