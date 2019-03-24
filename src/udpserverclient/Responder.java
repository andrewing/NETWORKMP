package udpserverclient;

import java.io.IOException;
import java.net.*;

public class Responder implements Runnable {

	private DatagramSocket socket = null;
	private DatagramPacket packet = null;

	public Responder(DatagramSocket socket, DatagramPacket packet) {
		this.socket = socket;
		this.packet = packet;
	}

	public void run() {
		try {
			byte[] data = makeResponse();
			DatagramPacket response = new DatagramPacket(data, data.length,
					packet.getAddress(), packet.getPort());
			socket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] makeResponse() {
		
		return null;
	}
}