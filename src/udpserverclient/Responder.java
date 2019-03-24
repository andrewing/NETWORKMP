package udpserverclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.*;

import model.BlackJackGame;
import model.Ender;

public class Responder implements Runnable {

	private DatagramSocket socket = null;
	private DatagramPacket packet = null;
	private BlackJackGame bjg;
	public Responder(DatagramSocket socket, DatagramPacket packet, BlackJackGame bjg) {
		this.socket = socket;
		this.packet = packet;
		this.bjg = bjg;
	}

	public void run() {
		try {
			byte[] data = makeResponse();
			DatagramPacketModifier dpm = new DatagramPacketModifier(data);
			DatagramPacket response = dpm.asDatagramPacket(packet.getAddress(), packet.getPort());
			socket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] makeResponse() {
		DatagramPacketModifier dpm = DatagramPacketModifier.fromDatagramPacket(packet);
		switch(new String(dpm.getData())) {
			case "end":
				return toBytes(new Ender(this.bjg));
			default:
		}
		return null;
	}
	
	private byte[] toBytes(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);   
			out.writeObject(obj);
			out.flush();
			bytes = bos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return bytes;
	}
}
