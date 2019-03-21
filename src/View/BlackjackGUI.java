package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;


public class BlackjackGUI extends JFrame {

	private JPanel contentPane;
	public CardLayout cl;
	public JPanel mainPanel;
	public JPanel startGamePanel, inGamePanel;
	public JButton btnStartGame;

	private JPanel opponent1Table, opponent2Table, dealerTable;
	private JPanel playerTable;
	private JButton hitBtn;
	private JButton standBtn;
	private JTextField nameTxtField;
	private JScrollPane scrollPane;

	public BlackjackGUI() {
		
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		
		JLabel lblDealer = new JLabel("DEALER");
		lblDealer.setForeground(Color.WHITE);
		lblDealer.setBounds(351, 32, 105, 27);
		lblDealer.setFont(new Font("Tahoma", Font.BOLD, 15));
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
		dealerTable.setLayout((new FlowLayout(FlowLayout.CENTER)));
		inGamePanel.add(dealerTable);
		
		//player tables
		opponent1Table = new JPanel();
		opponent1Table.setForeground(Color.WHITE);
		opponent1Table.setBorder(BorderFactory.createRaisedBevelBorder());
		opponent1Table.setBackground(new Color(46, 139, 87));
		opponent1Table.setBounds(37, 155, 288, 74);
		opponent1Table.setLayout(new FlowLayout(FlowLayout.CENTER));
		inGamePanel.add(opponent1Table);
		
		opponent2Table = new JPanel();
		opponent2Table.setBackground(new Color(46, 139, 87));
		opponent2Table.setBorder(BorderFactory.createRaisedBevelBorder());
		opponent2Table.setBounds(476, 155, 288, 74);
		opponent2Table.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		inGamePanel.add(opponent2Table);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 240, 288, 74);
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
		
		//player button
		hitBtn = new JButton("Hit");
		hitBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		hitBtn.setBounds(298, 325, 74, 27);
		//hitBtn.setEnabled(false);
		inGamePanel.add(hitBtn);
		
		standBtn = new JButton("Stand");
		standBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		standBtn.setBounds(412, 325, 74, 27);
		standBtn.setEnabled(false);
		inGamePanel.add(standBtn);
		
	}
	
	public String getName() {
		return nameTxtField.getText();
	}
	
	public void setBorder() {
		
		TitledBorder titleBorder = new TitledBorder(BorderFactory.createLoweredBevelBorder(), getName(), TitledBorder.ABOVE_TOP,
									TitledBorder.CENTER, new Font("Segoe UI", Font.PLAIN, 13), Color.WHITE);
		
		playerTable.setBorder(titleBorder);
		
	}
	
	public void setPlayerTable(String drawedCard){
		
       		JLabel card = new JLabel();
       		playerTable.add(card);
       		playerTable.add(Box.createRigidArea(new Dimension(1,0)));
			card.setIcon(new ImageIcon(getClass().getResource(drawedCard)));
			System.out.println(drawedCard);
	}
	
	public void StartGameBtnListener(ActionListener listenerForStartBtn) {
		btnStartGame.addActionListener(listenerForStartBtn);
	}
	
	public void HitBtnListener(ActionListener listenerForHitBtn) {
		hitBtn.addActionListener(listenerForHitBtn);
	}
	
	public void StandBtnListener(ActionListener listenerForStandBtn) {
		standBtn.addActionListener(listenerForStandBtn);
	}
}
