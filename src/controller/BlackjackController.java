package controller;


import javax.swing.*;

import view.BlackjackGUI;
import view.DrawedCard;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;

public class BlackjackController {
	
	private BlackjackGUI blackjackView;
	
	
	public BlackjackController(BlackjackGUI gameView) {
		
		this.blackjackView = gameView;
		blackjackView.setVisible(true);
		
		this.blackjackView.StartGameBtnListener(new ListenerForStartGameBtn());
		this.blackjackView.HitBtnListener(new ListenerForHitBtn());
		this.blackjackView.StandBtnListener(new ListenerForStandBtn());
	}
	
	class ListenerForStartGameBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			blackjackView.cl.show(blackjackView.mainPanel, "0");
			blackjackView.setBorder();
		}
	}
	
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
}
