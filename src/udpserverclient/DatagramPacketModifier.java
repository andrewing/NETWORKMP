package udpserverclient;

import java.net.DatagramPacket;
import java.util.*;
public class DatagramPacketModifier implements Comparable{
	private byte flowcontrol;
	private byte[] data;
	private static byte packetNumber = 0;

	public DatagramPacketModifier(byte flowcontrol, byte[] data) {
		this.flowcontrol =flowcontrol;
		this.data = data;
	}

	public DatagramPacketModifier(byte[] data) {
		this.data = data;
		this.flowcontrol = -1;
	}

	public DatagramPacket asDatagramPacket() {
		if(flowcontrol == -1) {
			flowcontrol = packetNumber;
			packetNumber++;
		}
		byte[] dataflow = new byte[data.length + 1];
		
		dataflow[0] = (byte) flowcontrol;
		for(int i = 0; i < data.length; i++) {
			dataflow[i+1] = data[i];
		}
	
		DatagramPacket datagramPacket = new DatagramPacket(dataflow, data.length);
		return datagramPacket;
	}


	public static DatagramPacketModifier fromDatagramPacket(DatagramPacket datagramPacket) {
		byte[] dpmReceivedData = datagramPacket.getData();
		byte flowcontrol = dpmReceivedData[0];
		byte[] receivedData = new byte[dpmReceivedData.length-1];
		for(int i = 1; i < dpmReceivedData.length; i++) {
			receivedData[i-1] = dpmReceivedData[i];
		}

		DatagramPacketModifier dpm = new DatagramPacketModifier(flowcontrol, receivedData);

		return dpm;
	}

	@Override
	public int compareTo(Object o) {
		byte flowcompare = ((DatagramPacketModifier)o).getData()[0];
		return this.getData()[0] - flowcompare;
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

	/*test*/
	public static void main(String[] args) {
		byte[] data = {1,2,3,4,5};
		byte[] data1 = {6,7,8,9,10};
		byte[] data2 = {11,12,13,14,15};
		byte[] data3 = {16,17,18,19,20};
		List<DatagramPacketModifier> list = new ArrayList<>();
		DatagramPacketModifier dpm = new DatagramPacketModifier(data);
		DatagramPacket dp = dpm.asDatagramPacket();

		
		DatagramPacketModifier dpm1 = new DatagramPacketModifier(data1);
		DatagramPacket dp1 = dpm1.asDatagramPacket();
		
		DatagramPacketModifier dpm2 = new DatagramPacketModifier(data2);
		DatagramPacket dp2 = dpm1.asDatagramPacket();
		
		DatagramPacketModifier dpm3 = new DatagramPacketModifier(data3);
		DatagramPacket dp3 = dpm1.asDatagramPacket();
		
		list.add(dpm2);
		list.add(dpm);
		list.add(dpm3);
		list.add(dpm1);
		
		Collections.sort(list);
		
		for(DatagramPacketModifier qwe : list)
			for(byte a: qwe.getData())
				System.out.println(a);
		
		
		
	}

	
	

}
