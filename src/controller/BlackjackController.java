package Controller;


import javax.swing.*;

import model.BlackJackGame;
import view.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;

public class BlackjackController {
	
	private BlackjackGUI blackjackView;
	private PlayerProfile profile;
	private OpponentProfile oppProfile;
	private Avatars avatars;
	
	
	public BlackjackController(BlackjackGUI gameView) {
		
		this.blackjackView = gameView;
		blackjackView.setVisible(true);
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
			blackjackView.setBorder();
		}
	}

//============================================================================
	//hit & stand buttons
	
	class ListenerForHitBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
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
