package view;


import javax.swing.*;
import java.awt.event.*;

public class BlackjackController {
	
	private BlackjackGUI blackjackView;
	
	
	public BlackjackController(BlackjackGUI gameView) {
		
		this.blackjackView = gameView;
		blackjackView.setVisible(true);
		
		this.blackjackView.StartGameBtnListener(new ListenerForStartGameBtn());
		this.blackjackView.HitBtnListener(new ListenerForHitBtn());
		this.blackjackView.HitBtnListener(new ListenerForStandBtn());
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
			JLabel playerTbl = blackjackView.getPlayerTable();
			DrawedCard draw = new DrawedCard();
			
			JLabel card = new JLabel();
			card.setIcon(new ImageIcon(getClass().getResource(draw.getDrawedCard())));
			playerTbl.add(card);
			
		}
	}
		
	class ListenerForStandBtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
}
