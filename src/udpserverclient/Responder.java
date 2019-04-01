package udpserverclient;

import java.io.*;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;

import event.EditProfileEvent;
import event.Event;
import event.HitEvent;
import event.JoinEvent;
import model.BlackJackGame;
import util.BitUtil;

import java.util.*;
public class Responder implements Runnable {
	private List<Packet> buffer;
	private Socket clientSocket;
	private int id;
	private BlackJackGame bjg;
	private volatile boolean exit = false;

	public Responder(Socket clientSocket, int id, BlackJackGame bjg) {
		this.clientSocket = clientSocket;
		this.id = id;
		this.bjg = bjg;
		buffer = new ArrayList<>();
	}

	@Override
	public void run() {

		try {
			DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream inFromClient = new DataInputStream(clientSocket.getInputStream());

			while(!exit) {
				int numPackets = inFromClient.readInt();
				if(numPackets == -1) {
					fin();
					stop();
				}else {
					receive(numPackets);
					Collections.sort(buffer);
					byte[] data = PacketUtil.combine(buffer);
					Object o = BitUtil.toObject(data);
					Event event = (Event) o;


					event.set(bjg);
					event.execute(bjg);
					event.statusPrint();

					byte[] bevents = BitUtil.toBytes(event);
					buffer.clear();
					buffer = PacketUtil.toPacket(bevents);
					outToClient.writeInt(buffer.size());
//					System.out.println("*********************************SENDING TO CLIENT*********************************");

					for(int i = 0; i < buffer.size(); i++) {
						Packet p = buffer.get(i);
						outToClient.writeInt(p.getSyn());
						outToClient.writeInt(p.getAck());
						outToClient.write(p.getBytes(), 0, p.getBytes().length);
//						System.out.println("PACKET SYN # " + p.getSyn());
						outToClient.flush();

						int ack = inFromClient.readInt();
//						System.out.print("CLIENT ACK # " + ack);
						if(ack != p.getSyn() + 1) {
//							System.out.println(" MISMATCH ERROR! RE-SENDING PACKET SYN # " + p.getSyn());
							i--;
							outToClient.writeInt(1);
						}else {
//							System.out.println();
							outToClient.writeInt(0);
						}
					}
					buffer.clear();
					outToClient.flush();
				}
			}


		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void receive(int numPackets) throws IOException {
		DataInputStream inFromClient = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());

//		System.out.println("*********************************PLACED INTO BUFFER*********************************");
		while(numPackets > 0) { 
			byte[] chunks = new byte[PacketUtil.FRAME];

			int syn = inFromClient.readInt();
			int ack = inFromClient.readInt();
			inFromClient.read(chunks, 0, chunks.length);
			Packet p = new Packet(chunks, syn, syn+1);
			buffer.add(p);
//			System.out.println("RECEIVED: PACKET SYN # " + p.getSyn());
			outToClient.writeInt(p.getAck());
//			System.out.println("SENDING: PACKET ACK # " + p.getAck());
			numPackets += inFromClient.readInt();
			numPackets--;
		}	

		outToClient.flush();
	}

	public void fin() throws IOException {
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
		boolean connected = false;
		while(!connected) {
			int synFromClient = inFromClient.read();
			System.out.println("RECEIVED FROM CLIENT # [" + id + "]: FIN # " + synFromClient);
			int synServer = new Random().nextInt(127);
			int ackToClient = synFromClient + 1;
			outToClient.write(ackToClient);
			outToClient.write(synServer);
			System.out.println("SENDING TO CLIENT # [" + id + "]: FIN # " + synServer + " / ACK # " + ackToClient);
			int ackFromClient = inFromClient.read();
			System.out.println("RECEIVED FROM CLIENT # [" + id + "]: ACK # " + ackFromClient);
			if(ackFromClient == synServer + 1){
				connected = true;
			}
		}
		outToClient.flush();
	}

	public void stop() {
		exit = true;
		System.out.println("SERVER DISCONNECTED FROM CLIENT # [" + id + "]");
	}

	private void printTime() {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		Format format = new SimpleDateFormat("HH:mm");
		System.out.println("Request processed: @ " + format.format(date));

	}

}
