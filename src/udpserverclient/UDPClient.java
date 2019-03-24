package udpserverclient;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import model.BlackJackGame;
import model.Ender;
import model.Requestable;
import util.BitUtil;

public class UDPClient implements Runnable, Serializable{
	private transient DatagramSocket socket;
	private InetAddress ip;
	private BlackJackGame bjg;

	public UDPClient(BlackJackGame bjg, String ip) {
		this(ip);
		this.bjg = bjg;
	}

	public UDPClient(String ip) {
		try {
			this.ip = InetAddress.getByName(ip);
			socket = new DatagramSocket();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		byte[] data = new byte[4096];
		DatagramPacketModifier dpm = null;
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {

			socket.receive(packet);
			dpm = DatagramPacketModifier.fromDatagramPacket(packet);

		} catch (IOException e) {
			e.printStackTrace();
		}
		Object o = BitUtil.toObject(dpm.getData());
		Requestable r = (Requestable)o;
		r.request();


		System.out.println("CLIENT IS STOPPED");
	}



	public void sendData(byte[] data) {
		DatagramPacketModifier dpm = new DatagramPacketModifier(data);
		DatagramPacket packet = dpm.asDatagramPacket(ip, 9876);
		//		System.out.println(new String(data));
		//		System.out.println(packet.getLength());
		//		System.out.println(packet.getPort());
		//		System.out.println(packet.getAddress());
		//		System.out.println(new String(packet.getData()));
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	public static void main(String args[]) throws IOException, InterruptedException {
		
		
		//		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		//		
		//		DatagramSocket clientSocket = new DatagramSocket();
		//		
		//		InetAddress IPAddress = InetAddress.getByName("localhost");
		//		
		//		byte[] sendData = new byte[1024];
		//		byte[] receiveData = new byte[1024];
		//		
		//		String sentence = inFromUser.readLine();
		//		sendData = sentence.getBytes();
		//		
		//		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		//		
		//		clientSocket.send(sendPacket);
		//		
		//		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		//		
		//		clientSocket.receive(receivePacket);
		//		
		//		String modifiedSentence = new String(receivePacket.getData());
		//		
		//		System.out.println("FROM SERVER: " + modifiedSentence);
		//		clientSocket.close();
		




	}
}
