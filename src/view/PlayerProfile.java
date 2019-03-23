package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;

public class PlayerProfile extends JFrame {

	private JPanel contentPane;
	private JLabel playerPts, playerWins, gamesPlayed;
	private JButton editBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerProfile frame = new PlayerProfile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayerProfile() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImage = new JLabel("img");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(10, 11, 64, 64);
		contentPane.add(lblImage);
		
		editBtn = new JButton("Edit image");
		editBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		editBtn.setBounds(84, 52, 99, 23);
		contentPane.add(editBtn);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblName.setBounds(84, 35, 45, 14);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(136, 36, 125, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPoints = new JLabel("Points :");
		lblPoints.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPoints.setBounds(10, 97, 52, 14);
		contentPane.add(lblPoints);
		
		JLabel lblWins = new JLabel("Wins :");
		lblWins.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblWins.setBounds(10, 122, 45, 14);
		contentPane.add(lblWins);
		
		JLabel lblGamesPlayed = new JLabel("Games Played :");
		lblGamesPlayed.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblGamesPlayed.setBounds(10, 147, 99, 14);
		contentPane.add(lblGamesPlayed);
		
		playerPts = new JLabel("New label");
		playerPts.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		playerPts.setBounds(61, 97, 125, 14);
		contentPane.add(playerPts);
		
		playerWins = new JLabel("New label");
		playerWins.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		playerWins.setBounds(58, 123, 125, 14);
		contentPane.add(playerWins);
		
		gamesPlayed = new JLabel("New label");
		gamesPlayed.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		gamesPlayed.setBounds(111, 148, 125, 14);
		contentPane.add(gamesPlayed);
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
		gamesPlayed.setText(plays);;
	}
	
	public void EditBtnListener(ActionListener listenerForEditBtn) {
		editBtn.addActionListener(listenerForEditBtn);
	}

}
