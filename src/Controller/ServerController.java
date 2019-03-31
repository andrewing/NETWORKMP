package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import udpserverclient.TCPClient;
import udpserverclient.TCPServer;
import view.ServerGUI;

public class ServerController {
	private ServerGUI serverView;
	private TCPServer server;
	public ServerController(ServerGUI serverView, TCPServer server) {
		this.serverView = serverView;
		this.server = server;
		serverView.setBtnStartActionListener(new ListenerForBtnStart());
	}
	class ListenerForBtnStart implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
