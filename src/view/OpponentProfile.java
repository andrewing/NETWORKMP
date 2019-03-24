package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class OpponentProfile extends JFrame {

	private JPanel contentPane;
	private JLabel playerPts, playerWins, gamesPlayed;
	private ImageIcon man = new ImageIcon(getClass().getResource("/man.png"));

	public OpponentProfile() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		setDefaultCloseOperation(1);
		setBounds(100, 100, 222, 178);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(man);
		lblImage.setBounds(10, 12, 64, 64);
		contentPane.add(lblImage);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setForeground(new Color(255, 215, 0));
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblName.setBounds(84, 36, 45, 14);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("John Andrew Ing");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(84, 48, 125, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblPoints = new JLabel("Points :");
		lblPoints.setForeground(new Color(255, 215, 0));
		lblPoints.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPoints.setBounds(10, 81, 52, 14);
		contentPane.add(lblPoints);
		
		JLabel lblWins = new JLabel("Wins :");
		lblWins.setForeground(new Color(255, 215, 0));
		lblWins.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblWins.setBounds(10, 98, 45, 14);
		contentPane.add(lblWins);
		
		JLabel lblGamesPlayed = new JLabel("Games Played :");
		lblGamesPlayed.setForeground(new Color(255, 215, 0));
		lblGamesPlayed.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblGamesPlayed.setBounds(10, 116, 99, 14);
		contentPane.add(lblGamesPlayed);
		
		playerPts = new JLabel("New label");
		playerPts.setForeground(new Color(255, 255, 255));
		playerPts.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		playerPts.setBounds(73, 81, 125, 14);
		contentPane.add(playerPts);
		
		playerWins = new JLabel("New label");
		playerWins.setForeground(new Color(255, 255, 255));
		playerWins.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		playerWins.setBounds(54, 98, 125, 14);
		contentPane.add(playerWins);
		
		gamesPlayed = new JLabel("New label");
		gamesPlayed.setForeground(new Color(255, 255, 255));
		gamesPlayed.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		gamesPlayed.setBounds(113, 116, 95, 14);
		contentPane.add(gamesPlayed);
		
		JLabel lblPlayerStat = new JLabel("PLAYER STAT");
		lblPlayerStat.setForeground(new Color(255, 215, 0));
		lblPlayerStat.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPlayerStat.setBounds(84, 11, 114, 14);
		contentPane.add(lblPlayerStat);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(73, 29, 135, 8);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 76, 209, 8);
		contentPane.add(separator_1);
	}

	//getters and setters
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
}
