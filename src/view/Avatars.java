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
import java.awt.event.ActionListener;

public class Avatars extends JFrame {
	
	
	private ImageIcon man = new ImageIcon(getClass().getResource("/man.png"));
	private ImageIcon woman = new ImageIcon(getClass().getResource("/woman.png"));
	private ImageIcon pig = new ImageIcon(getClass().getResource("/pig.png"));
	private ImageIcon panda = new ImageIcon(getClass().getResource("/panda.png"));
	private JPanel contentPane;
	private JButton icon1, icon2, icon3, icon4;
	private JLabel lblChooseYourAvatar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Avatars frame = new Avatars();
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
	public Avatars() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(46, 139, 87));
		
		lblChooseYourAvatar = new JLabel("Choose your avatar :");
		lblChooseYourAvatar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblChooseYourAvatar.setBounds(10, 20, 138, 14);
		contentPane.add(lblChooseYourAvatar);
		
		icon1 = new JButton();
		icon1.setBounds(10, 45, 64, 64);
		icon1.setIcon(man);
		icon1.setBorder(null);
		icon1.setOpaque(true);
		icon1.setBackground(new Color(46, 139, 87));
		contentPane.add(icon1);
		
		icon2 = new JButton();
		icon2.setBounds(84, 45, 64, 64);
		icon2.setIcon(woman);
		icon2.setBorder(null);
		icon2.setOpaque(true);
		icon2.setBackground(new Color(46, 139, 87));
		contentPane.add(icon2);
		
		icon3 = new JButton();
		icon3.setBounds(158, 45, 64, 64);
		icon3.setIcon(pig);
		icon3.setBorder(null);
		icon3.setOpaque(true);
		icon3.setBackground(new Color(46, 139, 87));
		contentPane.add(icon3);
		
		icon4 = new JButton();
		icon4.setBounds(237, 45, 64, 64);
		icon4.setIcon(panda);
		icon4.setBorder(null);
		icon4.setOpaque(true);
		icon4.setBackground(new Color(46, 139, 87));
		contentPane.add(icon4);
	}
	
	public void icon1BtnListener (ActionListener listenerForIcon1Btn) {
		icon1.addActionListener(listenerForIcon1Btn);
	}
	public void icon2BtnListener (ActionListener listenerForIcon2Btn) {
		icon1.addActionListener(listenerForIcon2Btn);
	}
	public void icon3BtnListener (ActionListener listenerForIcon3Btn) {
		icon1.addActionListener(listenerForIcon3Btn);
	}
	public void icon4BtnListener (ActionListener listenerForIcon4Btn) {
		icon1.addActionListener(listenerForIcon4Btn);
	}

}
