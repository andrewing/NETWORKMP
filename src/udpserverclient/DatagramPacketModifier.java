package udpserverclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.*;
public class DatagramPacketModifier implements Comparable{
	private int flowcontrol;
	private byte[] data;
	private static int packetNumber = 0;

	public DatagramPacketModifier(int flowcontrol, byte[] data) {
		this.flowcontrol =flowcontrol;
		this.data = data;
	}

	public DatagramPacketModifier(byte[] data) {
		this.data = data;
		this.flowcontrol = -1;
	}

	public synchronized DatagramPacket asDatagramPacket() {
		if(flowcontrol == -1) {
			flowcontrol = packetNumber;
			packetNumber++;
		}
		
		byte[] dataflow = new byte[data.length + 4];
		byte[] bytefc = new byte[4];
		bytefc = ByteBuffer.allocate(4).putInt(flowcontrol).array();
		for(int i = 0; i < bytefc.length; i++) {
			dataflow[i] = bytefc[i];
		}
		
		for(int i = 0; i < data.length; i++) {
			dataflow[i+4] = data[i];
		}
	
		DatagramPacket datagramPacket = new DatagramPacket(dataflow, dataflow.length);
		return datagramPacket;
	}


	public synchronized static DatagramPacketModifier fromDatagramPacket(DatagramPacket datagramPacket) {
		byte[] dpmReceivedData = datagramPacket.getData();
		
		int flowcontrol = extractFlowControl(dpmReceivedData);
		byte[] data = extractData(dpmReceivedData);
		
		DatagramPacketModifier dpm = new DatagramPacketModifier(flowcontrol, data);

		return dpm;
	}
	
	private synchronized static int extractFlowControl(byte[] bytes) {
		byte[] fc = new byte[4];
		for(int i = 0; i < 4; i++) {
			fc[i] = bytes[i];
		}
		return ByteBuffer.wrap(fc).getInt();
	}
	
	private synchronized static byte[] extractData(byte[] bytes) {
		byte[] data = new byte[bytes.length-4];
		for(int i = 4; i < bytes.length; i++) {
			data[i-4] = bytes[i];
		}
		return data;
	}
	
	@Override
	public  int compareTo(Object o) {
		byte flowcompare = ((DatagramPacketModifier)o).getData()[0];
		return this.getData()[0] - flowcompare;
	}
	
	public synchronized int getFlowcontrol() {
		return flowcontrol;
	}

	public synchronized void setFlowcontrol(int flowcontrol) {
		this.flowcontrol = flowcontrol;
	}

	public synchronized byte[] getData() {
		return data;
	}

	public synchronized void setData(byte[] data) {
		this.data = data;
	}

	public synchronized static int getPacketNumber() {
		return packetNumber;
	}

	public synchronized static void setPacketNumber(int packetNumber) {
		DatagramPacketModifier.packetNumber = packetNumber;
	}

	/*test*/
	public static void main(String[] args) throws IOException {
	
		DatagramSocket dataSock = new DatagramSocket(9876);
	
		byte[] data = {1,2,3,4,5};
		byte[] data1 = {6,7,8,9,10};
		byte[] data2 = {11,12,13,14,15};
		byte[] data3 = {16,17,18,19,20};
		List<DatagramPacketModifier> list = new ArrayList<>();
		
		DatagramPacketModifier dpm = new DatagramPacketModifier(data);
		DatagramPacket dp = dpm.asDatagramPacket();
		dataSock.receive(dp);
		
		
		System.out.println();
		DatagramPacketModifier dpm1 = new DatagramPacketModifier(data1);
		DatagramPacket dp1 = dpm1.asDatagramPacket();
		System.out.println(dpm1.getFlowcontrol());

		
		System.out.println();
		DatagramPacketModifier dpm2 = new DatagramPacketModifier(data2);
		DatagramPacket dp2 = dpm2.asDatagramPacket();
		System.out.println(dpm2.getFlowcontrol());

		
		DatagramPacketModifier dpm3 = new DatagramPacketModifier(data3);
		DatagramPacket dp3 = dpm3.asDatagramPacket();
		
		list.add(dpm2);
		list.add(dpm);
		list.add(dpm3);
		list.add(dpm1);

		Collections.sort(list);
		
		for(DatagramPacketModifier qwe : list) {
			System.out.println("FLOW CONTROL: " + qwe.getFlowcontrol());
			System.out.println("DATA: ");
			for(byte a: qwe.getData())
				System.out.println(a);
		}
		
		
	}

	
	

}
