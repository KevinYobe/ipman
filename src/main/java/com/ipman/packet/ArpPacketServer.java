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

public class ArpPacketServer extends AbstractPacketServer{

	public ArpPacketServer(PcapNetworkInterface nif) throws PcapNativeException {
		super(nif);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EthernetPacket buildPacket(String sourceIp, String destinationIP, MacAddress srcMacAdress) {
		
		ArpPacket.Builder arpBuilder = new ArpPacket.Builder();
		try {
	        arpBuilder
	            .hardwareType(ArpHardwareType.ETHERNET)
	            .protocolType(EtherType.IPV4)
	            .hardwareAddrLength((byte) MacAddress.SIZE_IN_BYTES)
	            .protocolAddrLength((byte) ByteArrays.INET4_ADDRESS_SIZE_IN_BYTES)
	            .operation(ArpOperation.REQUEST)
	            .srcHardwareAddr(srcMacAdress)
	            .srcProtocolAddr(InetAddress.getByName(sourceIp))
	            .dstHardwareAddr(MacAddress.ETHER_BROADCAST_ADDRESS)
	            .dstProtocolAddr(InetAddress.getByName(destinationIP));
	      } catch (UnknownHostException e) {
	        throw new IllegalArgumentException(e);
	      }
		
		EthernetPacket.Builder etherBuilder = new EthernetPacket.Builder();
	      etherBuilder
	          .dstAddr(MacAddress.ETHER_BROADCAST_ADDRESS)
	          .srcAddr(srcMacAdress)
	          .type(EtherType.ARP)
	          .payloadBuilder(arpBuilder)
	          .paddingAtBuild(true);
	      
		return etherBuilder.build();
	}

	@Override
	public void gotPacket(Packet packet) {
			
	}

}
