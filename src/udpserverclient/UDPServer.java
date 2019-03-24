package udpserverclient;

import java.io.*;
import java.net.*;

import model.BlackJackGame;

public class UDPServer extends Thread {

	private DatagramSocket socket;
	private BlackJackGame bjg;
	public UDPServer(BlackJackGame bjg) {
		this.bjg = bjg;
		init();
	}

	public void init() {
		try {
			socket = new DatagramSocket(9876);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			byte[] data = new byte[4096];
			DatagramPacket packet = new DatagramPacket(data, data.length);

			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			new Thread(new Responder(socket, packet, this.bjg)).start();
		}
	}
	
	

	public static void main(String args[]) throws IOException {
		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] receiveData = new byte[1024];
		byte[] sendData  = new byte[1024];
		while(true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			InetAddress IPAddress= receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence= sentence.toUpperCase();
			sendData= capitalizedSentence.getBytes();
			DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket); 
		}
	}
}
