package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import model.Player;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.WindowListener;


public class BlackjackGUI extends JFrame {

	private ImageIcon profile = new ImageIcon(getClass().getResource("/greed.png"));
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/add.png"));
	private ImageIcon lowerIcon = new ImageIcon(getClass().getResource("/low.png"));
	private JPanel contentPane;
	public CardLayout cl;
	public JPanel mainPanel ,startGamePanel, inGamePanel;
	public JButton btnStartGame;
	private JPanel playerTable, opponent1Table, opponent2Table, dealerTable;
	private JButton hitBtn,standBtn,addBetBtn,lowerBetBtn,betBtn;;
	private JTextField nameTxtField;
	private JScrollPane scrollPane, scrollPane1, scrollPane2;
	private JFormattedTextField betTxtField;
	private JLabel lblPlayerPoints, lblDealer;
	private JLabel lblPlayerBet, lblOpponent1Bet, lblOpponent2Bet;
	private JLabel lblTotalPot, lblPotValue;
	private JLabel lblOpp1Stand, lblOpp2Stand;
	private JButton playerAvatar, opponent1Avatar, opponent2Avatar;
	private JButton btnStart;

	

	public BlackjackGUI() {

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch (Exception e) {}


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBounds(10, 11, 794, 396);
		contentPane.add(mainPanel);

		cl = new CardLayout();
		mainPanel.setLayout(cl);

		startGamePanel = new JPanel();
		mainPanel.add(startGamePanel);
		startGamePanel.setLayout(null);

		inGamePanel = new JPanel();
		inGamePanel.setBackground(new Color(46, 139, 87));
		mainPanel.add(inGamePanel, "0");
		inGamePanel.setLayout(null);

		btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
		btnStartGame.setBounds(310, 225, 123, 23);
		startGamePanel.add(btnStartGame);


		JLabel lblBlackjack = new JLabel("BLACKJACK");
		lblBlackjack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlackjack.setFont(new Font("Yu Gothic Light", Font.BOLD, 30));
		lblBlackjack.setBounds(270, 139, 190, 49);
		startGamePanel.add(lblBlackjack);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		lblName.setBounds(232, 201, 49, 14);
		startGamePanel.add(lblName);

		lblDealer = new JLabel("WAITING FOR MORE PLAYERS...");
		lblDealer.setForeground(new Color(255, 215, 0));
		lblDealer.setBounds(256, 32, 288, 27);
		lblDealer.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDealer.setHorizontalAlignment(SwingConstants.CENTER);
		inGamePanel.add(lblDealer);	

		nameTxtField = new JTextField();
		nameTxtField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTxtField.setBounds(280, 194, 203, 29);
		nameTxtField.setOpaque(true);
		nameTxtField.setBackground(Color.LIGHT_GRAY);
		startGamePanel.add(nameTxtField);
		nameTxtField.setColumns(10);

		//dealer table
		dealerTable = new JPanel();
		dealerTable.setBorder(BorderFactory.createRaisedBevelBorder());
		dealerTable.setBackground(new Color(46, 139, 87));
		dealerTable.setBounds(256, 70, 288, 74);
		dealerTable.setLayout(new BoxLayout(dealerTable, BoxLayout.X_AXIS));
		dealerTable.add(Box.createRigidArea(new Dimension(5,0)));
		dealerTable.setBackground(new Color(46, 139, 87));
		dealerTable.setBorder(BorderFactory.createRaisedBevelBorder());
		inGamePanel.add(dealerTable);

		//player tables
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(89, 155, 236, 74);
		scrollPane1.setBackground(new Color(46, 139, 87));
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		inGamePanel.add(scrollPane1);
		opponent1Table = new JPanel();
		opponent1Table.setLayout(new BoxLayout(opponent1Table, BoxLayout.X_AXIS));
		opponent1Table.add(Box.createRigidArea(new Dimension(5,0)));
		opponent1Table.setBackground(new Color(46, 139, 87));
		opponent1Table.setBorder(BorderFactory.createRaisedBevelBorder());
		scrollPane1.setViewportView(opponent1Table);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(520, 155, 236, 74);
		scrollPane2.setBackground(new Color(46, 139, 87));
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		inGamePanel.add(scrollPane2);
		opponent2Table = new JPanel();
		opponent2Table.setLayout(new BoxLayout(opponent2Table, BoxLayout.X_AXIS));
		opponent2Table.add(Box.createRigidArea(new Dimension(5,0)));
		opponent2Table.setBackground(new Color(46, 139, 87));
		opponent2Table.setBorder(BorderFactory.createRaisedBevelBorder());
		scrollPane2.setViewportView(opponent2Table);
		

		scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 240, 236, 74);
		scrollPane.setBackground(new Color(46, 139, 87));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		inGamePanel.add(scrollPane);
		playerTable = new JPanel();
		playerTable.setLayout(new BoxLayout(playerTable, BoxLayout.X_AXIS));
		playerTable.add(Box.createRigidArea(new Dimension(5,0)));
		playerTable.setBackground(new Color(46, 139, 87));
		playerTable.setBorder(BorderFactory.createRaisedBevelBorder());
		scrollPane.setViewportView(playerTable);

		//player buttons
		hitBtn = new JButton("Hit");
		hitBtn.setForeground(new Color(0, 0, 0));
		hitBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		hitBtn.setBounds(256, 325, 54, 27);
		hitBtn.setBackground(new Color(255, 215, 0));
		inGamePanel.add(hitBtn);

		standBtn = new JButton("Stand");
		standBtn.setForeground(new Color(0, 0, 0));
		standBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		standBtn.setBounds(318, 325, 84, 27);
		standBtn.setBackground(new Color(255, 215, 0));
		inGamePanel.add(standBtn);

		betBtn = new JButton("Bet");
		betBtn.setForeground(new Color(0, 0, 0));
		betBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		betBtn.setBounds(411, 325, 63, 27);
		betBtn.setBackground(new Color(255, 215, 0));
		inGamePanel.add(betBtn);

		betTxtField = new JFormattedTextField("50");
		betTxtField.setBounds(476, 325, 45, 27);
		betTxtField.setBackground(new Color(0, 153, 102));
		betTxtField.setForeground(Color.WHITE);
		betTxtField.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0)));
		inGamePanel.add(betTxtField);
		betTxtField.setColumns(10);

		addBetBtn = new JButton();
		addBetBtn.setFont(new Font("MS Gothic", Font.BOLD, 5));
		addBetBtn.setBounds(520, 325, 24, 14);
		addBetBtn.setBackground(new Color(255, 215, 0));
		addBetBtn.setIcon(addIcon);
		inGamePanel.add(addBetBtn);

		lowerBetBtn = new JButton();
		lowerBetBtn.setFont(new Font("MS Gothic", Font.BOLD, 5));
		lowerBetBtn.setBounds(520, 338, 24, 14);
		lowerBetBtn.setBackground(new Color(255, 215, 0));
		lowerBetBtn.setIcon(lowerIcon);
		inGamePanel.add(lowerBetBtn);

		//labels
		JLabel lblPoints = new JLabel("Points :");
		lblPoints.setForeground(new Color(255, 215, 0));
		lblPoints.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPoints.setBounds(281, 368, 48, 14);
		inGamePanel.add(lblPoints);

		JLabel lblBetAmt = new JLabel("Bet Amount :");
		lblBetAmt.setForeground(new Color(255, 215, 0));
		lblBetAmt.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblBetAmt.setBounds(398, 368, 85, 14);
		inGamePanel.add(lblBetAmt);

		JLabel label = new JLabel("Bet Amount :");
		label.setForeground(new Color(255, 215, 0));
		label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		label.setBounds(564, 229, 85, 14);
		inGamePanel.add(label);

		JLabel label_3 = new JLabel("Bet Amount :");
		label_3.setForeground(new Color(255, 215, 0));
		label_3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		label_3.setBounds(97, 229, 85, 14);
		inGamePanel.add(label_3);

		//labels to be modified
		lblPlayerPoints = new JLabel("0");
		lblPlayerPoints.setForeground(Color.WHITE);
		lblPlayerPoints.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPlayerPoints.setBounds(334, 368, 54, 14);
		inGamePanel.add(lblPlayerPoints);

		lblPlayerBet = new JLabel("0");
		lblPlayerBet.setForeground(Color.WHITE);
		lblPlayerBet.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPlayerBet.setBounds(486, 369, 54, 14);
		inGamePanel.add(lblPlayerBet);

		lblOpponent1Bet = new JLabel("0");
		lblOpponent1Bet.setForeground(Color.WHITE);
		lblOpponent1Bet.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblOpponent1Bet.setBounds(192, 230, 54, 14);
		inGamePanel.add(lblOpponent1Bet);

		lblOpponent2Bet = new JLabel("0");
		lblOpponent2Bet.setForeground(Color.WHITE);
		lblOpponent2Bet.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblOpponent2Bet.setBounds(659, 229, 54, 14);
		inGamePanel.add(lblOpponent2Bet);

		//players profile	
		opponent1Avatar = new JButton();
		opponent1Avatar.setBounds(26, 160, 64, 64);
		opponent1Avatar.setIcon(profile);
		opponent1Avatar.setOpaque(true);
		opponent1Avatar.setBackground(new Color(46,139,87));
		inGamePanel.add(opponent1Avatar);

		opponent2Avatar = new JButton();
		opponent2Avatar.setBounds(457, 160, 64, 64);
		opponent2Avatar.setIcon(profile);
		opponent2Avatar.setOpaque(true);
		opponent2Avatar.setBackground(new Color(46,139,87));
		inGamePanel.add(opponent2Avatar);

		playerAvatar = new JButton();
		playerAvatar.setIcon(profile);
		playerAvatar.setOpaque(true); 
		playerAvatar.setBackground(new Color(46, 139, 87));
		playerAvatar.setBounds(245, 245, 64, 64);
		inGamePanel.add(playerAvatar);
		
		btnStart = new JButton("Start");
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnStart.setBackground(new Color(255, 215, 0));
		btnStart.setBounds(356, 180, 84, 27);
		inGamePanel.add(btnStart);
		
		lblTotalPot = new JLabel("TOTAL POT");
		lblTotalPot.setForeground(Color.WHITE);
		lblTotalPot.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTotalPot.setBounds(360, 155, 76, 27);
		inGamePanel.add(lblTotalPot);
		
		lblPotValue = new JLabel("");
		lblPotValue.setForeground(Color.WHITE);
		lblPotValue.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPotValue.setBounds(390, 180, 76, 27);
		inGamePanel.add(lblPotValue);
		
		lblOpp1Stand = new JLabel("");
		lblOpp1Stand.setForeground(new Color(255, 215, 0));
		lblOpp1Stand.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblOpp1Stand.setBounds(89, 140, 85, 14);
		inGamePanel.add(lblOpp1Stand);
		
		lblOpp2Stand = new JLabel("");
		lblOpp2Stand.setForeground(new Color(255, 215, 0));
		lblOpp2Stand.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblOpp2Stand.setBounds(670, 140, 85, 14);
		inGamePanel.add(lblOpp2Stand);
	}

	public void setTableAvatar(String img) {
		ImageIcon avatar = new ImageIcon(getClass().getResource(img));
		this.playerAvatar.setIcon(avatar);
	}

	public String getName() {
		return nameTxtField.getText();
	}

	public void setBorder(Object...o) {
		java.util.List<TitledBorder> tb = new ArrayList<>();
		for(Object obj: o)
			tb.add(setBorder(obj));
		
		
		playerTable.setBorder(setBorderBlank());
		opponent1Table.setBorder(setBorderBlank());
		opponent2Table.setBorder(setBorderBlank());
		
		if(tb.size()>0)
		playerTable.setBorder(tb.get(0));
		if(tb.size() > 1)
		opponent1Table.setBorder(tb.get(1));
		if(tb.size() > 2)
		opponent2Table.setBorder(tb.get(2));

	}

	public TitledBorder setBorder(Object o) {
		Player player = (Player)o;
		System.out.println(player.getName());
		return new TitledBorder(BorderFactory.createLoweredBevelBorder(), player.getName(), TitledBorder.ABOVE_TOP,
				TitledBorder.CENTER, new Font("Segoe UI", Font.PLAIN, 13), Color.WHITE);
	}
	
	public TitledBorder setBorderBlank() {
		return new TitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.ABOVE_TOP,
				TitledBorder.CENTER, new Font("Segoe UI", Font.PLAIN, 13), Color.WHITE);
	}
	

	public void setPlayerTable(String drawedCard){
		JLabel card = new JLabel();
		playerTable.add(card);
		playerTable.add(Box.createRigidArea(new Dimension(1,0)));
		card.setIcon(new ImageIcon(getClass().getResource(drawedCard)));
	}
	
	public void setOpponent1Table(String drawedCard){
		JLabel card = new JLabel();
		opponent1Table.add(card);
		opponent1Table.add(Box.createRigidArea(new Dimension(1,0)));
		card.setIcon(new ImageIcon(getClass().getResource(drawedCard)));
	}
	
	public void setOpponent2Table(String drawedCard){
		JLabel card = new JLabel();
		opponent2Table.add(card);
		opponent2Table.add(Box.createRigidArea(new Dimension(1,0)));
		card.setIcon(new ImageIcon(getClass().getResource(drawedCard)));
	}
	//=========================================================================================	
	//getters and setters for text fields	
	public String getBetTxtFieldInput() {
		return betTxtField.getText();
	}

	public void setBetTxtField(String bet) {
		betTxtField.setText(bet);
	}
	//=========================================================================================
	//getters and setters for labels

	//opponent bets
	public String getOpponent1Bet() {
		return lblOpponent1Bet.getText();
	}

	public String getOpponent2Bet() {
		return lblOpponent2Bet.getText();
	}
	
	public void setOpponent1Bet(String bet) {
		lblOpponent1Bet.setText(bet);
	}
	public void setOpponent2Bet(String bet) {
		lblOpponent2Bet.setText(bet);
	}

	//player points and bet	
	public String getPlayerBet() {
		return this.lblPlayerBet.getText();
	}

	public void setPlayerBet(String bet) {
		lblPlayerBet.setText(bet);
	}

	public String getPlayerPoints() {
		return lblPlayerPoints.getText();
	}

	public void setPlayerPoints(String points) {
		lblPlayerPoints.setText(points);
	}

	//===============================================================================	
	//button listeners	
	public void StartGameBtnListener(ActionListener listenerForStartBtn) {
		btnStartGame.addActionListener(listenerForStartBtn);
	}

	public void HitBtnListener(ActionListener listenerForHitBtn) {
		hitBtn.addActionListener(listenerForHitBtn);
	}

	public void StandBtnListener(ActionListener listenerForStandBtn) {
		standBtn.addActionListener(listenerForStandBtn);
	}

	public void BetBtnListener(ActionListener listenerForBetBtn) {
		betBtn.addActionListener(listenerForBetBtn);
	}

	public void AddBetBtnListener(ActionListener listenerForAddBetBtn) {
		addBetBtn.addActionListener(listenerForAddBetBtn);
	}

	public void LowerBetBtnListener(ActionListener listenerForLowerBetBtn) {
		lowerBetBtn.addActionListener(listenerForLowerBetBtn);
	}

	public void PlayerAvatarListener(ActionListener listenerForPlayerAvatar) {
		playerAvatar.addActionListener(listenerForPlayerAvatar);
	}

	public void Opponent1AvatarListener(ActionListener listenerForOpponent1Avatar) {
		opponent1Avatar.addActionListener(listenerForOpponent1Avatar);
	}

	public void Opponent2AvatarListener(ActionListener listenerForOpponent2Avatar) {
		opponent2Avatar.addActionListener(listenerForOpponent2Avatar);
	}
	
	public void FrameWindowListener(WindowListener windowListenerForFrame) {
		this.addWindowListener(windowListenerForFrame);
	}
	
	public void ButtonStartListener(ActionListener listenerForButtonStart) {
		btnStart.addActionListener(listenerForButtonStart);
	}
	
	public JButton getHitButton() {
		return this.hitBtn;
	}
	
	public JButton getStandButton() {
		return this.standBtn;
	}
	
	public JLabel getBetLabel() {
		return this.lblPlayerBet;
	}
	
	public JButton getAddBetButton() {
		return this.addBetBtn;
	}
	
	public JButton getBetButton() {
		return this.betBtn;
	}
	
	public JButton getLowerBetButton() {
		return this.lowerBetBtn;
	}

	public JLabel getlblTotalPot() {
		return this.lblTotalPot;
	}
	
	public JLabel getlblPotValue() {
		return this.lblPotValue;
	}
	
	public JButton getOpponent1Avatar() {
		return opponent1Avatar;
	}

	public void setOpponent1AvatarIcon(String img) {
		ImageIcon avatar = new ImageIcon(getClass().getResource(img));
		opponent1Avatar.setIcon(avatar);
	}

	public JButton getOpponent2Avatar() {
		return opponent2Avatar;
	}

	public void setOpponent2AvatarIcon(String img) {
		ImageIcon avatar = new ImageIcon(getClass().getResource(img));
		opponent2Avatar.setIcon(avatar);
	}
	
	public JButton getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}
	
	public JLabel getLblDealer() {
		return lblDealer;
	}

	public void setLblDealerStart() {
		lblDealer.setText("DEALER");
	}
	
	public JPanel getOpponent1Table() {
		return opponent1Table;
	}

	public JPanel getOpponent2Table() {
		return opponent2Table;
	}
	
	public JButton getAddBetBtn() {
		return addBetBtn;
	}

	public JButton getLowerBetBtn() {
		return lowerBetBtn;
	}

	public JFormattedTextField getBetTxtField() {
		return betTxtField;
	}
	
	public JPanel getPlayerTable() {
		return playerTable;
	}

	public void setPlayerTable(JPanel playerTable) {
		this.playerTable = playerTable;
	}
	
	public JLabel getLblOpp1Stand() {
		return lblOpp1Stand;
	}

	public void setLblOpp1Stand(JLabel lblOpp1Stand) {
		this.lblOpp1Stand = lblOpp1Stand;
	}

	public JLabel getLblOpp2Stand() {
		return lblOpp2Stand;
	}

	public void setLblOpp2Stand(JLabel lblOpp2Stand) {
		this.lblOpp2Stand = lblOpp2Stand;
	}
}
