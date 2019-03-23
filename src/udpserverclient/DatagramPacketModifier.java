package udpserverclient;

import java.net.DatagramPacket;

public class DatagramPacketModifier {
	private byte flowcontrol = 0;
	private static byte packetNumber = 0;
	
	public DatagramPacketModifier(byte flowcontrol) {
		this.flowcontrol =flowcontrol;
	}
	
	public DatagramPacket asDatagramPacket(byte[] data) {
		flowcontrol = packetNumber;
		byte[] dataflow = new byte[data.length + 1];
		
		data[0] = (byte) flowcontrol;
		for(int i = 0; i < data.length; i++) {
			dataflow[i+1] = data[i];
		}


		DatagramPacket datagramPacket = new DatagramPacket(dataflow, data.length);
		byte[] helloworld = datagramPacket.getData();
		for(byte bb: helloworld) {
			System.out.println(Byte.toString(bb));
		}
		packetNumber++;
		return datagramPacket;
    }
	

    public static DatagramPacketModifier fromDatagramPacket(DatagramPacket datagramPacket) {
        // Parse the control bytes out of the given DatagramPacket 
        // and construct a JayPacket
    	byte[] receivedData = datagramPacket.getData();
    	DatagramPacketModifier dpm = new DatagramPacketModifier(receivedData[0]);
    	
    	return null;
    }
}
