package udpserverclient;

import java.io.*;
import java.net.*;

import model.BlackJackGame;

public class UDPClient extends Thread {
	
	private DatagramSocket socket;
	private InetAddress ip;
	private BlackJackGame bjg;
	private byte[] bytesFromServer;
	public UDPClient(BlackJackGame bjg, String ip) {
		try {
			this.ip = InetAddress.getByName(ip);
			this.bjg = bjg;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		byte[] data = new byte[4096];
		DatagramPacketModifier dpm;
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
			dpm = DatagramPacketModifier.fromDatagramPacket(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bytesFromServer = packet.getData();
	}
	
	public void sendData(byte[] data) {
		DatagramPacketModifier dpm = new DatagramPacketModifier(data);
		DatagramPacket packet = dpm.asDatagramPacket(ip, 9876);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] getBytesFromServer() {
		return bytesFromServer;
	}

	public void setBytesFromServer(byte[] bytesFromServer) {
		this.bytesFromServer = bytesFromServer;
	}

	
	public static void main(String args[]) throws IOException {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		DatagramSocket clientSocket = new DatagramSocket();
		
		InetAddress IPAddress = InetAddress.getByName("localhost");
		
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		
		clientSocket.send(sendPacket);
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		clientSocket.receive(receivePacket);
		
		String modifiedSentence = new String(receivePacket.getData());
		
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
		
	}
}
