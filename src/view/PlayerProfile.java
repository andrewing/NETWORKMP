package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JSeparator;
import java.awt.Color;

public class PlayerProfile extends JFrame {

	private JPanel contentPane;
	private JLabel playerPts, playerWins, gamesPlayed, avatar, lblPlayerName;
	private JButton editBtn;
	private ImageIcon avatarImg;
	private ImageIcon greed = new ImageIcon(getClass().getResource("/greed.png"));

	public PlayerProfile() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		setDefaultCloseOperation(1);
		setBounds(100, 100, 260, 217);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setForeground(new Color(255, 215, 0));
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblName.setBounds(84, 35, 45, 14);
		contentPane.add(lblName);
		
		lblPlayerName = new JLabel("<name>");
		lblPlayerName.setForeground(new Color(255, 255, 255));
		lblPlayerName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPlayerName.setBounds(136, 35, 109, 14);
		contentPane.add(lblPlayerName);
		
		JLabel lblPoints = new JLabel("Points :");
		lblPoints.setForeground(new Color(255, 215, 0));
		lblPoints.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPoints.setBounds(10, 97, 52, 14);
		contentPane.add(lblPoints);
		
		JLabel lblWins = new JLabel("Wins :");
		lblWins.setForeground(new Color(255, 215, 0));
		lblWins.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblWins.setBounds(10, 122, 45, 14);
		contentPane.add(lblWins);
		
		JLabel lblGamesPlayed = new JLabel("Games Played :");
		lblGamesPlayed.setForeground(new Color(255, 215, 0));
		lblGamesPlayed.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblGamesPlayed.setBounds(10, 147, 99, 14);
		contentPane.add(lblGamesPlayed);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(74, 30, 171, 8);
		contentPane.add(separator);
		
		JLabel label = new JLabel("PLAYER STAT");
		label.setForeground(new Color(255, 215, 0));
		label.setBackground(new Color(255, 215, 0));
		label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		label.setBounds(84, 11, 116, 14);
		contentPane.add(label);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 86, 245, 8);
		contentPane.add(separator_1);
		
		//labels and button needed
		editBtn = new JButton("Edit avatar");
		editBtn.setForeground(new Color(255, 215, 0));
		editBtn.setBackground(new Color(128, 0, 0));
		editBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		editBtn.setBounds(84, 52, 101, 23);
		editBtn.setOpaque(true);
		editBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0)));
		contentPane.add(editBtn);
		
		avatar = new JLabel();
		avatar.setHorizontalAlignment(SwingConstants.CENTER);
		avatar.setBounds(10, 11, 64, 64);
		avatar.setIcon(greed);
		contentPane.add(avatar);
		
		playerPts = new JLabel("New label");
		playerPts.setForeground(new Color(255, 255, 255));
		playerPts.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		playerPts.setBounds(61, 97, 125, 14);
		contentPane.add(playerPts);
		
		playerWins = new JLabel("New label");
		playerWins.setForeground(new Color(255, 255, 255));
		playerWins.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		playerWins.setBounds(58, 123, 125, 14);
		contentPane.add(playerWins);
		
		gamesPlayed = new JLabel("New label");
		gamesPlayed.setForeground(new Color(255, 255, 255));
		gamesPlayed.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		gamesPlayed.setBounds(111, 148, 125, 14);
		contentPane.add(gamesPlayed);
		
	}
//=======================================================================
	//getters and setters
	
	public void setAvatar(String img) {
		avatarImg = new ImageIcon(getClass().getResource(img));
		avatar.setIcon(avatarImg);
	}
	
	public String getPlayerPts() {
		return playerPts.getText();
	}

	public void setPlayerPts(String pts) {
		playerPts.setText(pts);
	}

	public String getPlayerWins() {
		return playerWins.getText();
	}

	public void setPlayerWins(String wins) {
		playerWins.setText(wins);;
	}

	public String getGamesPlayed() {
		return gamesPlayed.getText();
	}

	public void setGamesPlayed(String plays) {
		gamesPlayed.setText(plays);
	}
	
	
	public void editBtnListener(ActionListener listenerForEditBtn) {
		editBtn.addActionListener(listenerForEditBtn);
	}
	
	public JLabel getLblPlayerName() {
		return lblPlayerName;
	}

	public void setLblPlayerName(String name) {
		lblPlayerName.setText(name);
	}

}
