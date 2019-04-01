package udpserverclient;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

import event.BetEvent;
import event.EditProfileEvent;
import event.Event;
import event.HitEvent;
import event.JoinEvent;
import event.LeaveEvent;
import model.BlackJackGame;
import model.Player;
import util.BitUtil;
import java.util.*;
public class TCPClient implements Runnable{
	private List<Packet> buffer;
	private Socket socket;
	private String ip;
	private int port; 
	private BlackJackGame bjg;
	private Thread tclient;
	private volatile boolean exit = false;
	private volatile boolean sent = false;

	public TCPClient(String ip, int port,BlackJackGame bjg) {
		this.bjg = bjg;
		this.ip = ip;
		this.port = port;
		try {	
			this.socket = new Socket(InetAddress.getByName(ip), port);
			handshake();
			buffer = new ArrayList<>();
		} catch (IOException e) {
		}
	}
	public void run() {

		while(!exit) {
			try {
				DataInputStream inFromServer = new DataInputStream(socket.getInputStream());
				DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
				while(!sent) {
					
				}
				sent = false;
				int numPackets = inFromServer.readInt();
//				System.out.println("*********************************PLACED INTO BUFFER*********************************");
				while(numPackets > 0) { 
					byte[] chunks = new byte[PacketUtil.FRAME];

					int syn = inFromServer.readInt();
					int ack = inFromServer.readInt();
					inFromServer.read(chunks, 0, chunks.length);
					Packet p = new Packet(chunks, syn, syn+1);
					buffer.add(p);
//					System.out.println("PACKET SYN: " + p.getSyn());
					outToServer.writeInt(p.getAck());
					numPackets += inFromServer.readInt();
					numPackets--;
				}	
				Collections.sort(buffer);
				byte[] data = PacketUtil.combine(buffer);
				Object o = BitUtil.toObject(data);
				Event event = (Event) o;
				event.statusPrint();
				event.execute(bjg);
				buffer.clear();
//				System.out.println("*******************************DATA PROCESSED SUCCESSFULLY*******************************");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void stop() {
		exit = true;
		System.out.println("SUCCESSFULLY DISCONNECTED");
	}

	public synchronized void send(Event event) {
		try {
			DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
			DataInputStream inFromServer = new DataInputStream(socket.getInputStream());
			byte[] bytes = BitUtil.toBytes(event);
//			System.out.println(bytes.length);
			buffer = PacketUtil.toPacket(bytes);
			int numberOfPackets = buffer.size();
			outToServer.writeInt(numberOfPackets);
//			System.out.println("*********************************SENDING TO SERVER*********************************");
			for(int i = 0; i < buffer.size(); i++) {
				Packet p = buffer.get(i);
				outToServer.writeInt(p.getSyn());
				outToServer.writeInt(p.getAck());
				outToServer.write(p.getBytes(), 0, p.getBytes().length);
//				System.out.println("PACKET SYN # " + p.getSyn());
				outToServer.flush();
				int ack = inFromServer.readInt();
//				System.out.print("SERVER ACK # " + ack);
				if(ack != p.getSyn() + 1) {
//					System.out.println(" MISMATCH ERROR! RE-SENDING PACKET SYN # " + p.getSyn());
					i--;
					outToServer.writeInt(1);
				}else {
					System.out.println();
					outToServer.writeInt(0);
				}
			}
	
			outToServer.flush();
			buffer.clear();
			sent = true;
			TimeUnit.MILLISECONDS.sleep(200);
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
/*		try {
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
*/	}

	public synchronized void handshake() throws IOException {
		boolean connected = false;
		DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		while(!connected) {
			int synClient = new Random().nextInt(127);
			outToServer.write(synClient);
			System.out.println("SENDING TO SERVER: SYN # " + synClient);
			int ackServer = inFromServer.read();
			System.out.print("RECEIVED FROM SERVER: ACK # "+ ackServer);
			if(ackServer == synClient + 1) {
				connected = true;
				int synServer = inFromServer.read();
				System.out.println(" / SYN # " + synServer);
				int ackClient = synServer + 1;
				System.out.println("SENDING TO SERVER: ACK # " + ackClient);
				outToServer.write(ackClient);
			}
		}
		outToServer.flush();
	}
	
	public synchronized void fin() {
		boolean connected = false;
		try {
			DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outToServer.writeInt(-1);
			while(!connected) {
				int synClient = new Random().nextInt(127);
				outToServer.write(synClient);
				System.out.println("SENDING TO SERVER: FIN # " + synClient);
				int ackServer = inFromServer.read();
				System.out.print("RECEIVED FROM SERVER: ACK # "+ ackServer);
				if(ackServer == synClient + 1) {
					connected = true;
					int synServer = inFromServer.read();
					System.out.println(" / FIN # " + synServer);
					int ackClient = synServer + 1;
					System.out.println("SENDING TO SERVER: ACK # " + ackClient);
					outToServer.write(ackClient);
				}
			}
			outToServer.flush();
			stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void start() {
		tclient = new Thread(this, "client");
		tclient.start();
	}

	public synchronized BlackJackGame getBJG() {
		return bjg;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}


	public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
		BlackJackGame bjg = new BlackJackGame();
		Player player = new Player("Andrew", "img");

		TCPClient client = new TCPClient("localhost", 5000, bjg);
		new Thread(client, "Client").start();
		Event join = new JoinEvent(player);
		client.send(join);

	}




}
