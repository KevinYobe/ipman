package com.ipman.packet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.ArpHardwareType;
import org.pcap4j.packet.namednumber.ArpOperation;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.util.ByteArrays;
import org.pcap4j.util.MacAddress;

public class IPPacketServer extends AbstractPacketServer{

	public IPPacketServer(PcapNetworkInterface nif) throws PcapNativeException {
		super(nif);
	}

	@Override
	public EthernetPacket buildPacket(String sourceIp, String destinationIP, MacAddress srcMacAdress) {
		return null;
	}

	@Override
	public void gotPacket(Packet packet) {
		// TODO Auto-generated method stub
		
	}

}
