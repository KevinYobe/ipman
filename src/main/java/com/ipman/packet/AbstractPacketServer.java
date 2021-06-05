package com.ipman.packet;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.MacAddress;

import com.ipman.util.InterfaceData;

public abstract class AbstractPacketServer implements PacketServer {
	
	private PcapNetworkInterface nif;
	private PcapHandle handle;
	
	InterfaceData data = new InterfaceData(65536, PromiscuousMode.PROMISCUOUS, 10);
	
	public AbstractPacketServer(PcapNetworkInterface nif) throws PcapNativeException {
		this.nif = nif;
		this.handle = this.nif.openLive(data.snapLen, data.mode, data.timeout);
	}
	
	public abstract EthernetPacket buildPacket(String sourceIp, String destinationIP, MacAddress srcMacAdress);

	@Override
	public void sendPacket(Packet packet) throws PcapNativeException, NotOpenException {
		handle.sendPacket(packet);
	}

	@Override
	public abstract void gotPacket(Packet packet);

}
