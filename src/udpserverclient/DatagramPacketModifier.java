package udpserverclient;

import java.net.DatagramPacket;

public class DatagramPacketModifier {
	private byte flowcontrol = 0;
	private byte[] data;
	private static byte packetNumber = 0;
	
	public DatagramPacketModifier(byte flowcontrol, byte[] data) {
		this.flowcontrol =flowcontrol;
		this.data = data;
	}
	
	public DatagramPacketModifier(byte[] data) {
		this.data = data;
	}
	
	public DatagramPacket asDatagramPacket() {
		flowcontrol = packetNumber;
		byte[] dataflow = new byte[data.length + 1];
		
		data[0] = (byte) flowcontrol;
		for(int i = 0; i < data.length; i++) {
			dataflow[i+1] = data[i];
		}


		DatagramPacket datagramPacket = new DatagramPacket(dataflow, data.length);

		packetNumber++;
		return datagramPacket;
    }
	

    public static DatagramPacketModifier fromDatagramPacket(DatagramPacket datagramPacket) {
    	byte[] receivedData = datagramPacket.getData();
    	byte flowcontrol = receivedData[0];
		for(int i = 0; i < receivedData.length; i++) {
			receivedData[i] = receivedData[i+1];
		}
    	
    	DatagramPacketModifier dpm = new DatagramPacketModifier(flowcontrol, receivedData);
    	
    	return dpm;
    }
    
    public byte getFlowcontrol() {
		return flowcontrol;
	}

	public void setFlowcontrol(byte flowcontrol) {
		this.flowcontrol = flowcontrol;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public static byte getPacketNumber() {
		return packetNumber;
	}

	public static void setPacketNumber(byte packetNumber) {
		DatagramPacketModifier.packetNumber = packetNumber;
	}

}
