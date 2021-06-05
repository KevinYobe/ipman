package com.ipman.packet.capture;

import java.io.EOFException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.Packet;

public class ARPPacketCapture extends AbstractPacketCapture {
	
	public ARPPacketCapture() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArpPacket capturePacket() {
		Packet packet = null;
		try {
			packet = openHandle().getNextPacketEx();
		} catch (EOFException | PcapNativeException | TimeoutException | NotOpenException e) {
			e.printStackTrace();
		}
		
		if (packet.contains(ArpPacket.class)) {
			return packet.get(ArpPacket.class);
		}
		
		else {
			return null;
		}
		
	}

}
