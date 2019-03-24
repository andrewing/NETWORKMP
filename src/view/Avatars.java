package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Avatars extends JFrame {
	
	
	private ImageIcon man = new ImageIcon(getClass().getResource("/man.png"));
	private ImageIcon woman = new ImageIcon(getClass().getResource("/woman.png"));
	private ImageIcon man2 = new ImageIcon(getClass().getResource("/man2.png"));
	private ImageIcon woman2 = new ImageIcon(getClass().getResource("/woman2.png"));
	private JPanel contentPane;
	private JButton icon1, icon2, icon3, icon4;
	private JLabel lblChooseYourAvatar;

	public Avatars() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		setDefaultCloseOperation(1);
		setBounds(100, 100, 325, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(128, 0, 0));
		
		lblChooseYourAvatar = new JLabel("Choose your avatar :");
		lblChooseYourAvatar.setBackground(new Color(255, 215, 0));
		lblChooseYourAvatar.setForeground(new Color(255, 215, 0));
		lblChooseYourAvatar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblChooseYourAvatar.setBounds(10, 20, 138, 14);
		contentPane.add(lblChooseYourAvatar);
		
		icon1 = new JButton();
		icon1.setActionCommand("icon1");
		icon1.setBounds(10, 45, 64, 64);
		icon1.setIcon(man);
		icon1.setBorder(null);
		icon1.setOpaque(true);
		icon1.setBackground(new Color(128, 0, 0));
		contentPane.add(icon1);
		
		icon2 = new JButton();
		icon2.setActionCommand("icon2");
		icon2.setBounds(84, 45, 64, 64);
		icon2.setIcon(woman);
		icon2.setBorder(null);
		icon2.setOpaque(true);
		icon2.setBackground(new Color(128, 0, 0));
		contentPane.add(icon2);
		
		icon3 = new JButton();
		icon3.setActionCommand("icon3");
		icon3.setBounds(158, 45, 64, 64);
		icon3.setIcon(man2);
		icon3.setBorder(null);
		icon3.setOpaque(true);
		icon3.setBackground(new Color(128, 0, 0));
		contentPane.add(icon3);
		
		icon4 = new JButton();
		icon4.setActionCommand("icon4");
		icon4.setBounds(237, 45, 64, 64);
		icon4.setIcon(woman2);
		icon4.setBorder(null);
		icon4.setOpaque(true);
		icon4.setBackground(new Color(128, 0, 0));
		contentPane.add(icon4);
	}
	
	public void iconListeners(ActionListener listenerForIcons) {
		
		icon1.addActionListener(listenerForIcons);
		icon2.addActionListener(listenerForIcons);
		icon3.addActionListener(listenerForIcons);
		icon4.addActionListener(listenerForIcons);
	}
	
}
