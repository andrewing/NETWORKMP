package udpserverclient;

import java.io.*;
import java.net.*;

public class UDPServer extends Thread {
	
	private DatagramSocket socket;
	
	public UDPServer() {
		try {
			init();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void init() throws SocketException {
		socket = new DatagramSocket(9876);
	}
	
	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
    		
    		try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
            new Thread(new Responder(socket, packet)).start();
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
