package controller;


import javax.swing.*;

import event.BetEvent;
import event.EditProfileEvent;
import event.Event;
import event.HitEvent;
import event.JoinEvent;
import event.UpdateEvent;
import model.BlackJackGame;
import model.Card;
import model.Player;
import udpserverclient.TCPClient;
import view.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.awt.*;

public class BlackjackController {
	private BlackjackGUI blackjackView;
	public Player player;
	private PlayerProfile profile;
	private OpponentProfile oppProfile1, oppProfile2;
	private Avatars avatars;
	private TCPClient client;
	private int opp1HandCount = 0, opp2HandCount = 0;
	public BlackjackController(BlackjackGUI gameView, TCPClient client) {

		this.blackjackView = gameView;
		blackjackView.setVisible(true);
		this.client = client;
		client.start();

		this.profile = new PlayerProfile();
		this.oppProfile1 = new OpponentProfile();
		this.oppProfile2 = new OpponentProfile();
		this.avatars = new Avatars();

		this.blackjackView.FrameWindowListener(new WindowListenerForFrame());
		this.blackjackView.StartGameBtnListener(new ListenerForStartGameBtn());
		this.blackjackView.HitBtnListener(new ListenerForHitBtn());
		this.blackjackView.StandBtnListener(new ListenerForStandBtn());
		this.blackjackView.AddBetBtnListener(new ListenerForAddBetBtn());
		this.blackjackView.LowerBetBtnListener(new ListenerForLowerBetBtn());
		this.blackjackView.BetBtnListener(new ListenerForBetBtn());
		this.blackjackView.ButtonStartListener(new ListenerForButtonStart());

		this.blackjackView.PlayerAvatarListener(new ListenerForPlayerAvatar());
		this.blackjackView.Opponent1AvatarListener(new ListenerForOpponent1Avatar());
		this.blackjackView.Opponent2AvatarListener(new ListenerForOpponent2Avatar());

	}

	class ListenerForStartGameBtn implements ActionListener{

		@Override
		public synchronized void actionPerformed(ActionEvent e) {

			blackjackView.cl.show(blackjackView.mainPanel, "0");
			player = new Player(blackjackView.getName(), "/greed.png");
			Event join = new JoinEvent(player);
			client.send(join);
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			player = client.getBJG().findPlayer(player.getName());

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					BlackJackGame bjg = client.getBJG();
					initPlayer();
					if(client.getBJG().getPlayers().size() < 3)
						client.send(join);
					else {
						blackjackView.getBtnStart().setEnabled(true);
						blackjackView.setLblDealerStart();
					}
					blackjackView.setBorder(bjg.getPlayers().toArray());
					initOpponents();
				}
			}, 0, 2500);
		}

		private void initPlayer() {
			blackjackView.setBorder(client.getBJG().getPlayers().toArray());
			blackjackView.setPlayerPoints(""+player.getPoints());
			profile.setLblPlayerName(player.getName());
			profile.setPlayerPts(""+player.getPoints());
			profile.setPlayerWins(""+player.getWins());
			profile.setGamesPlayed(""+player.getGames());
		}

		private void initOpponents() {
			BlackJackGame bjg = client.getBJG();
			if(bjg.getPlayers().size() > 1) {
				Player opp1 = bjg.getPlayers().get(1);
				int bet1 = opp1.getBet();
				if(bet1 >= 0)
					blackjackView.setOpponent1Bet(""+bet1);
				else
					blackjackView.setOpponent1Bet("BUSTED");
				blackjackView.setOpponent1AvatarIcon(opp1.getImgPath());
				oppProfile1.setLblOppName(opp1.getName());
				oppProfile1.setPlayerPts(""+opp1.getPoints());
				oppProfile1.setPlayerWins(""+opp1.getWins());
				oppProfile1.setGamesPlayed(""+opp1.getGames());
				
			}


			if(bjg.getPlayers().size() > 2) {
				Player opp2 = bjg.getPlayers().get(2);
				int bet2 = opp2.getBet();
				if(bet2 >= 0)
					blackjackView.setOpponent2Bet(""+bet2);
				else
					blackjackView.setOpponent2Bet("BUSTED");
				blackjackView.setOpponent2AvatarIcon(opp2.getImgPath());
				oppProfile2.setLblOppName(opp2.getName());
				oppProfile2.setPlayerPts(""+opp2.getPoints());
				oppProfile2.setPlayerWins(""+opp2.getWins());
				oppProfile2.setGamesPlayed(""+opp2.getGames());

				blackjackView.getOpponent1Table().removeAll();
				blackjackView.getOpponent1Table().revalidate();
				blackjackView.getOpponent1Table().repaint();
				blackjackView.getOpponent2Table().removeAll();
				blackjackView.getOpponent2Table().revalidate();
				blackjackView.getOpponent2Table().repaint();
				
				for(int i = 0; i < bjg.getPlayers().get(1).getHandCount() ;i++) {
					blackjackView.setOpponent1Table("/COVER.png");
				}
				for(int i = 0; i < opp2.getHandCount(); i++) {
					blackjackView.setOpponent2Table("/COVER.png");
				}
				
				client.send(new UpdateEvent(bjg.getPlayers().get(1), bjg.getPlayers().get(2)));
				
			}
		}
	}



	class ListenerForButtonStart implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			client.send(new HitEvent(player));
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Player p = client.getBJG().findPlayer(player.getName());
			Card c = p.getHand().get(0);
			blackjackView.setPlayerTable("/"+c.getFace()+c.getSuit()+".png");
			
			
			client.send(new HitEvent(player));
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			p = client.getBJG().findPlayer(player.getName());
			c = p.getHand().get(0);
			blackjackView.setPlayerTable("/"+c.getFace()+c.getSuit()+".png");
			
			
			if(p.totalValue() > 21) {
				blackjackView.getHitButton().setEnabled(false);
				blackjackView.getStandButton().setEnabled(false);
				blackjackView.getBetButton().setEnabled(false);
				blackjackView.getBetLabel().setText("BUSTED");
				p.setBet(-1);
			}
			
			blackjackView.getBtnStart().setVisible(false);
		}

	}
	//============================================================================
	//hit & stand buttons

	class ListenerForHitBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			client.send(new HitEvent(player));
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Player p = client.getBJG().findPlayer(player.getName());
			Card c = p.getHand().get(0);
			blackjackView.setPlayerTable("/"+c.getFace()+c.getSuit()+".png");

			if(p.totalValue() > 21) {
				blackjackView.getHitButton().setEnabled(false);
				blackjackView.getStandButton().setEnabled(false);
				blackjackView.getBetButton().setEnabled(false);
				blackjackView.getBetLabel().setText("BUSTED");
				p.setBet(-1);
			}
		}
	}

	class ListenerForStandBtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			blackjackView.getHitButton().setEnabled(false);
			blackjackView.getStandButton().setEnabled(false);
		}

	}

	//================================================================================================	
	//bet buttons

	class ListenerForAddBetBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int val = Integer.parseInt(blackjackView.getBetTxtFieldInput());
			val = val + 10;
			blackjackView.setBetTxtField(val+ "");
		}

	}

	class ListenerForLowerBetBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int val = Integer.parseInt(blackjackView.getBetTxtFieldInput());

			val = val - 10;
			if(val >= 0) {
				blackjackView.setBetTxtField(val+ "");
			}
		}
	}

	class ListenerForBetBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int bet = Integer.parseInt(blackjackView.getBetTxtFieldInput());
			client.send(new BetEvent(bet, player));

			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			blackjackView.setPlayerPoints(""+player.getPoints());
			blackjackView.setPlayerBet(""+player.getBet());
		}
	}
	//===============================================================================================	
	//player profiles

	class ListenerForPlayerAvatar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			profile.setVisible(true);
			profile.editBtnListener(new ListenerForEditBtn());
			avatars.iconListeners(new ListenerForIcons());
			profile.setAvatar(player.getImgPath());
		}
	}

	class ListenerForOpponent1Avatar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			oppProfile1.setVisible(true);
		}
	}

	class ListenerForOpponent2Avatar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			oppProfile2.setVisible(true);
		}
	}

	//============================================================================================
	//edit

	class ListenerForEditBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			avatars.setVisible(true);
		}
	}

	class ListenerForIcons implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String img = "/greed.png";
			if(e.getActionCommand().equalsIgnoreCase("icon1")) {
				img = "/man.png";

			}else if(e.getActionCommand().equalsIgnoreCase("icon2")) {
				img = "/woman.png";
			}else if(e.getActionCommand().equalsIgnoreCase("icon3")) {
				img = "/man2.png";
			}else if(e.getActionCommand().equalsIgnoreCase("icon4")) {
				img = "/woman2.png";
			}

			profile.setAvatar(img);
			blackjackView.setTableAvatar(img);
			avatars.dispose();
			client.send(new EditProfileEvent(player, img));
		}	
	}

	class WindowListenerForFrame implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {}

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Close");
		}

		@Override
		public void windowClosed(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}
		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowDeactivated(WindowEvent e) {}
	}

}
