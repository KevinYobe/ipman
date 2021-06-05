package com.ipman.packet.capture;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Component;

@Component
public interface  PacketCapture {
	 
	PcapNetworkInterface openNetworkInterface();
	PcapHandle openHandle();

	<T extends Packet> T capturePacket();

}
