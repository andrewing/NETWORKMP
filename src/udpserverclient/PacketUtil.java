package udpserverclient;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import event.Event;
import util.BitUtil;

public class PacketUtil {
	public static final int FRAME = 50;
	public static int SYN = 0;
	public static byte[] combine(List<Packet> buffer) {
		byte data[] = new byte[buffer.size() * (FRAME + 10)];
		int counter = 0;
		for(Packet p: buffer) {
			for(int i = 0; i < p.getBytes().length; i++) {
				data[counter] = p.getBytes()[i];
				counter++;
			}
		}
		
		return data;
	}
	
	public static List<Packet> toPacket(byte[] bytes) {
		List<Packet> buffer = new ArrayList<>();
		byte[] chunks;
		System.out.println("*********************************PLACED INTO BUFFER*********************************");
		for(int i = 0; i < (int)Math.ceil((double)bytes.length/(double)FRAME); i++) {
			chunks = new byte[FRAME];
			for(int j = i*FRAME ; j != bytes.length && j < (i+1)*FRAME; j++) {
				chunks[j - i*FRAME] = bytes[j];
			}
			Packet p = new Packet(chunks, SYN * FRAME);
			SYN++;
			buffer.add(p);
			System.out.println("PACKET SYN #: " + p.getSyn());
		}
		if(SYN > Integer.MAX_VALUE - 1024*FRAME) {
			SYN = 0;
		}
		return buffer;
	}
	
	public static void fromPacket() {
		
	}
	
}
