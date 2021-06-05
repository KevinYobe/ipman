package com.ipman.packet;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.packet.Packet;

public interface PacketServer  extends PacketListener{
	
	public void sendPacket(Packet packet) throws PcapNativeException, NotOpenException ;
	

}
