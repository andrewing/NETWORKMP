package controller;


import javax.swing.*;

import event.BetEvent;
import event.EditProfileEvent;
import event.Event;
import event.HitEvent;
import event.JoinEvent;
import event.LeaveEvent;
import event.ResetEvent;
import event.StandEvent;
import event.UpdateEvent;
import event.WinEvent;
import model.BlackJackGame;
import model.Card;
import model.Player;
import udpserverclient.TCPClient;
import view.*;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class BlackjackController {
	private BlackjackGUI blackjackView;
	public Player player;
	private PlayerProfile profile;
	private OpponentProfile oppProfile1, oppProfile2;
	private Avatars avatars;
	private TCPClient client;
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

		blackjackView.getHitButton().setVisible(false);
		blackjackView.getStandButton().setVisible(false);
		blackjackView.getBtnStart().setVisible(false);
		blackjackView.getlblTotalPot().setVisible(false);
		blackjackView.getlblPotValue().setVisible(false);

		blackjackView.getBetButton().setVisible(false);
		blackjackView.getAddBetBtn().setVisible(false);
		blackjackView.getLowerBetBtn().setVisible(false);
		blackjackView.getBetTxtField().setVisible(false);
	}

	class ListenerForStartGameBtn implements ActionListener{

		@Override
		public synchronized void actionPerformed(ActionEvent e) {

			blackjackView.cl.show(blackjackView.mainPanel, "0");
			player = new Player(blackjackView.getName(), "/greed.png");
			Event join = new JoinEvent(player);
			client.send(join);
			player = client.getBJG().findPlayer(blackjackView.getName());

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					BlackJackGame bjg = client.getBJG();
					initPlayer();
					if(client.getBJG().getPlayers().size() < 3) {
						client.send(join);
					}else {
						blackjackView.getBetButton().setVisible(true);
						blackjackView.getAddBetBtn().setVisible(true);
						blackjackView.getLowerBetBtn().setVisible(true);
						blackjackView.getBetTxtField().setVisible(true);

						blackjackView.setLblDealerStart();
						initGame();
						initOpponents();
						checkWinner();
					}
					blackjackView.setBorder(bjg.getPlayers().toArray());
				}
			}, 0, 1000);

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
				oppProfile1.setAvatar(opp1.getImgPath());
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
				oppProfile2.setAvatar(opp2.getImgPath());
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
				int cmpCount = blackjackView.getOpponent1Table().getComponentCount();
				for(int i = 0; i < bjg.getPlayers().get(1).getHandCount() - cmpCount;i++) {
					blackjackView.setOpponent1Table("/COVER.png");

				}
				cmpCount = blackjackView.getOpponent2Table().getComponentCount();
				for(int i = 0; i < opp2.getHandCount() - cmpCount; i++) {
					blackjackView.setOpponent2Table("/COVER.png");
				}

				client.send(new UpdateEvent(bjg.getPlayers().get(0), bjg.getPlayers().get(1), bjg.getPlayers().get(2)));
			}
		}

		private void initGame() {
			blackjackView.getlblPotValue().setText("" + client.getBJG().getPot());
		}

		private void checkWinner() {
			boolean allStand = true;
			for(int i = 0; i < client.getBJG().getPlayers().size(); i++) {
				Player p = client.getBJG().getPlayers().get(i);
				if(!p.isStand()) {
					allStand = false;
				}else {
					if(i == 1)
						blackjackView.getLblOpp1Stand().setText("STAND");
					if(i == 2)
						blackjackView.getLblOpp2Stand().setText("STAND");
				}
			}

			if(allStand) {
				blackjackView.getOpponent1Table().removeAll();
				blackjackView.getOpponent1Table().revalidate();
				blackjackView.getOpponent1Table().repaint();
				blackjackView.getOpponent2Table().removeAll();
				blackjackView.getOpponent2Table().revalidate();
				blackjackView.getOpponent2Table().repaint();

				Player opp = client.getBJG().getPlayers().get(1);
				for(int i = 0; i < opp.getHandCount(); i++) {
					blackjackView.setOpponent1Table("/"+opp.getHand().get(i).getFace()+opp.getHand().get(i).getSuit()+".png");
				}
				opp = client.getBJG().getPlayers().get(2);
				for(int i = 0; i < opp.getHandCount(); i++) {
					blackjackView.setOpponent2Table("/"+opp.getHand().get(i).getFace()+opp.getHand().get(i).getSuit()+".png");
				}
				try {
					TimeUnit.MILLISECONDS.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int highest = -999;
				Player winner = null;
				for(Player p: client.getBJG().getPlayers()) {
					if(highest < p.totalValue()) {
						highest = p.totalValue();
						winner = p;
					}
				}

				if(winner.equals(player)) {
					JOptionPane.showConfirmDialog(null, "YOU WON " + client.getBJG().getPot(),
			                   "", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
					client.send(new WinEvent(player, client.getBJG().getPot()));
					client.send(new ResetEvent(client.getBJG().getPlayers()));
				}


				reset();

			}
		}

		private void reset() {
			blackjackView.getPlayerTable().removeAll();
			blackjackView.getPlayerTable().revalidate();
			blackjackView.getPlayerTable().revalidate();
			blackjackView.getOpponent1Table().removeAll();
			blackjackView.getOpponent1Table().revalidate();
			blackjackView.getOpponent1Table().repaint();
			blackjackView.getOpponent2Table().removeAll();
			blackjackView.getOpponent2Table().revalidate();
			blackjackView.getOpponent2Table().repaint();

			blackjackView.getHitButton().setVisible(false);
			blackjackView.getStandButton().setVisible(false);
			blackjackView.getBtnStart().setVisible(false);
			blackjackView.getlblTotalPot().setVisible(false);
			blackjackView.getlblPotValue().setVisible(false);

			if(player.getPoints() > 0) {
				blackjackView.getBetButton().setEnabled(true);
				blackjackView.getAddBetButton().setEnabled(true);
				blackjackView.getLowerBetButton().setEnabled(true);
				blackjackView.getBetTxtField().setEnabled(true);
			}else {
				client.send(new StandEvent(player, true));
			}
			
			if(player.getPoints() > 0 
					&& client.getBJG().getPlayers().get(1).getPoints() == 0 
					&& client.getBJG().getPlayers().get(2).getPoints() == 0 
					/*&& client.getBJG().getPlayers().get(1).isStand()
					&& client.getBJG().getPlayers().get(2).isStand()*/) {
				JOptionPane.showConfirmDialog(null, "YOU WIN!",
		                   "", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
			
			
			blackjackView.getLblOpp1Stand().setText("PLAYING...");
			blackjackView.getLblOpp2Stand().setText("PLAYING...");
		}
	}



	class ListenerForButtonStart implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			client.send(new HitEvent(player));
			Player p = client.getBJG().findPlayer(player.getName());
			Card c = p.getHand().get(0);
			blackjackView.setPlayerTable("/"+c.getFace()+c.getSuit()+".png");


			client.send(new HitEvent(player));
			p = client.getBJG().findPlayer(player.getName());
			c = p.getHand().get(0);
			blackjackView.setPlayerTable("/"+c.getFace()+c.getSuit()+".png");

			blackjackView.getBtnStart().setVisible(false);
			blackjackView.getHitButton().setVisible(true);
			blackjackView.getStandButton().setVisible(true);
			blackjackView.getlblTotalPot().setVisible(true);
			blackjackView.getlblPotValue().setVisible(true);

			if(p.totalValue() == -1) {
				blackjackView.getHitButton().setVisible(false);
				blackjackView.getStandButton().setVisible(false);
				blackjackView.getBetLabel().setText("BUSTED");
				p.stand();
				client.send(new StandEvent(p,true));
			}

		}

	}
	//============================================================================
	//hit & stand buttons

	class ListenerForHitBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			client.send(new HitEvent(player));
			Player p = client.getBJG().findPlayer(player.getName());
			Card c = p.getHand().get(0);
			blackjackView.setPlayerTable("/"+c.getFace()+c.getSuit()+".png");

			if(p.totalValue() == -1) {
				blackjackView.getHitButton().setVisible(false);
				blackjackView.getStandButton().setVisible(false);
				blackjackView.getAddBetBtn().setVisible(false);
				blackjackView.getLowerBetBtn().setVisible(false);
				blackjackView.getBetLabel().setText("BUSTED");
				p.stand();
				client.send(new StandEvent(p,true));
			}
		}
	}

	class ListenerForStandBtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			blackjackView.getHitButton().setVisible(false);
			blackjackView.getStandButton().setVisible(false);
			player.stand();
			client.send(new StandEvent(player, true));
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
			if(player.getPoints() > 0) {
				int bet = Integer.parseInt(blackjackView.getBetTxtFieldInput());
				client.send(new BetEvent(bet, player));

				blackjackView.setPlayerPoints(""+player.getPoints());
				blackjackView.setPlayerBet(""+player.getBet());

				if(player.getBet() != 0) {
					blackjackView.getBtnStart().setVisible(true);
					blackjackView.getAddBetButton().setVisible(false);
					blackjackView.getLowerBetButton().setVisible(false);
					blackjackView.getBetTxtField().setVisible(false);

					blackjackView.getBetButton().setEnabled(false);
					blackjackView.getAddBetButton().setEnabled(false);
					blackjackView.getLowerBetButton().setEnabled(false);
					blackjackView.getBetTxtField().setEnabled(false);
				}
			}
		}
	}
	//===============================================================================================	
	//player profiles

	class ListenerForPlayerAvatar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Point p = MouseInfo.getPointerInfo().getLocation();
			profile.setVisible(true);
			profile.setLocation(p);
			profile.editBtnListener(new ListenerForEditBtn());
			avatars.iconListeners(new ListenerForIcons());
			profile.setAvatar(player.getImgPath());
		}
	}


	class ListenerForOpponent1Avatar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Point p = MouseInfo.getPointerInfo().getLocation();
			oppProfile1.setLocation(p);
			oppProfile1.setVisible(true);
		}
	}

	class ListenerForOpponent2Avatar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Point p = MouseInfo.getPointerInfo().getLocation();
			oppProfile2.setLocation(p);
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
			profile.dispose();
			client.send(new EditProfileEvent(player, img));
		}	
	}

	class WindowListenerForFrame implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {}

		@Override
		public void windowClosing(WindowEvent e) {
			client.send(new LeaveEvent(player));
			client.fin();
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
