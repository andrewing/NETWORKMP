package udpserverclient;

import java.nio.ByteBuffer;
import java.util.*;
public class Packet implements Comparable{
	private byte[] bytes;
	private int syn;
	private int ack;
	
	public Packet(byte[] bytes) {
		byte[] data = new byte[bytes.length];
		byte[] bsyn = new byte[4];
		byte[] back = new byte[4];
		for(int i = 0; i < 4; i++) {
			bsyn[i] = bytes[i];
			back[i] = bytes[i+4];
		}
		
		for(int i = 8; i < bytes.length; i++) {
			data[i-8] = bytes[i];
		}
		syn = ByteBuffer.wrap(bsyn).getInt();
		ack = ByteBuffer.wrap(back).getInt();
		this.bytes = data;
	}
	
	public Packet(byte[] bytes, int syn) {
		this.bytes = bytes;	
		this.syn = syn;
		this.ack = -1;
	}
	
	public Packet(byte[] bytes, int syn, int ack) {
		this(bytes, syn);
		this.ack = ack;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public int getSyn() {
		return syn;
	}

	public void setSyn(int syn) {
		this.syn = syn;
	}

	public int getAck() {
		return ack;
	}

	public void setAck(int ack) {
		this.ack = ack;
	}
	
//	public void pack() {
//		byte[] bsyn = ByteBuffer.allocate(4).putInt(syn).array();
//		byte[] back = ByteBuffer.allocate(4).putInt(ack).array();
////		System.out.println("PACKET: " + ByteBuffer.wrap(bsyn).getInt());
////		System.out.println("PACKET: " + ByteBuffer.wrap(back).getInt());
//		byte[] packet = new byte[bytes.length + bsyn.length + back.length];
//		for(int i = 0; i < bsyn.length; i++) {
//			packet[i] = bsyn[i];
//			packet[i+4] = back[i];
//		}
//		
//		for(int i = 8; i < bytes.length; i++) {
//			packet[i-8] = bytes[i];
//		}
//		this.bytes = packet;
//	}

	@Override
	public int compareTo(Object o) {
		return this.syn - ((Packet)o).getSyn();
	}
	
}
