package udpserverclient;

import java.io.*;
import java.net.*;

public class UDPClient extends Thread {
	
	private DatagramSocket socket;
	private InetAddress ip;
	
	public UDPClient(String ip) {
		try {
			this.ip = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		byte[] data = new byte[1024];
		DatagramPacketModifier dpm = new DatagramPacketModifier(data);
		DatagramPacket packet = dpm.asDatagramPacket();
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, 9876);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
