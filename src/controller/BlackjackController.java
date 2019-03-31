package controller;


import javax.swing.*;

import event.BetEvent;
import event.EditProfileEvent;
import event.Event;
import event.HitEvent;
import event.JoinEvent;
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
	private OpponentProfile oppProfile;
	private Avatars avatars;
	private TCPClient client;

	public BlackjackController(BlackjackGUI gameView, TCPClient client) {

		this.blackjackView = gameView;
		blackjackView.setVisible(true);
		this.client = client;
		new Thread(client, "client").start();

		this.profile = new PlayerProfile();
		this.oppProfile = new OpponentProfile();
		this.avatars = new Avatars();
		
		this.blackjackView.FrameWindowListener(new WindowListenerForFrame());
		this.blackjackView.StartGameBtnListener(new ListenerForStartGameBtn());
		this.blackjackView.HitBtnListener(new ListenerForHitBtn());
		this.blackjackView.StandBtnListener(new ListenerForStandBtn());
		this.blackjackView.AddBetBtnListener(new ListenerForAddBetBtn());
		this.blackjackView.LowerBetBtnListener(new ListenerForLowerBetBtn());
		this.blackjackView.BetBtnListener(new ListenerForBetBtn());

		this.blackjackView.PlayerAvatarListener(new ListenerForPlayerAvatar());
		this.blackjackView.Opponent1AvatarListener(new ListenerForOpponent1Avatar());
		this.blackjackView.Opponent2AvatarListener(new ListenerForOpponent2Avatar());
		
	}

	class ListenerForStartGameBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

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
			blackjackView.setBorder(client.getBJG().getPlayers().toArray());
			blackjackView.setPlayerPoints(""+player.getPoints());
			
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					if(client.getBJG().getPlayers().size() < 3)
						client.send(join);
					
					blackjackView.setBorder(client.getBJG().getPlayers().toArray());
				}
			}, 1000, 5000);
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
			blackjackView.setPlayerBet(""+bet);
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
			oppProfile.setVisible(true);
		}
	}

	class ListenerForOpponent2Avatar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			oppProfile.setVisible(true);
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
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}else if(e.getActionCommand().equalsIgnoreCase("icon2")) {
				img = "/woman.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}else if(e.getActionCommand().equalsIgnoreCase("icon3")) {
				img = "/man2.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}else if(e.getActionCommand().equalsIgnoreCase("icon4")) {
				img = "/woman2.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}else {
				img = "/greed.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}
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
