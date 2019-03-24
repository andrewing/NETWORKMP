package udpserverclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;

import model.BlackJackGame;
import model.Ender;
import model.Requestable;
import util.BitUtil;

public class Responder implements Runnable{

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
			System.out.println(data == null);
			DatagramPacketModifier dpm = new DatagramPacketModifier(data);
			DatagramPacket response = dpm.asDatagramPacket(packet.getAddress(), packet.getPort());
			socket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] makeResponse() {
		DatagramPacketModifier dpm = DatagramPacketModifier.fromDatagramPacket(packet);
		String data = new String(dpm.getData());
		if(data.substring(0, "end".length()).equalsIgnoreCase("end")) {
			System.out.println("in");
			return BitUtil.toBytes(new Ender(this.bjg));
//			return "ended".getBytes();
		}
		return null;


	}


}
