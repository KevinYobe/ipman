package com.ipman.packet.capture;



import org.pcap4j.core.NotOpenException;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Component;

@Component
public class IPPacketCapture extends AbstractPacketCapture {
	public IPPacketCapture() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IpV4Packet capturePacket() {
		Packet packet = null;
		try {
			packet = openHandle().getNextPacket();
		} catch (NotOpenException e) {
			System.out.println("Timed out:" + e);
		}
		
		if (packet.contains(IpV4Packet.class)) {
			return packet.get(IpV4Packet.class);
		}
		
		else {
			return null;
		}
	}

}
