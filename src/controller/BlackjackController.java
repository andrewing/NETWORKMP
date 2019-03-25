package controller;


import javax.swing.*;

import event.Event;
import event.HitEvent;
import event.JoinEvent;
import model.BlackJackGame;
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
			
			System.out.println(client.getBJG().getPlayers().size());
			blackjackView.setBorder(client.getBJG().getPlayers().toArray());
			System.out.println("JOIN");
		}
	}

	//============================================================================
	//hit & stand buttons

	class ListenerForHitBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			client.send(new JoinEvent(player));
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			blackjackView.setBorder(client.getBJG().getPlayers().toArray());
			DrawedCard draw = new DrawedCard();
			blackjackView.setPlayerTable(draw.getDrawedCard());

		}
	}

	class ListenerForStandBtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

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
			if(e.getActionCommand().equalsIgnoreCase("icon1")) {
				String img = "/man.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}else if(e.getActionCommand().equalsIgnoreCase("icon2")) {
				String img = "/woman.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}else if(e.getActionCommand().equalsIgnoreCase("icon3")) {
				String img = "/man2.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}else if(e.getActionCommand().equalsIgnoreCase("icon4")) {
				String img = "/woman2.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}else {
				String img = "/greed.png";
				profile.setAvatar(img);
				blackjackView.setTableAvatar(img);
				avatars.dispose();
			}
		}	
	}




}
