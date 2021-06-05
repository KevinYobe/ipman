package com.ipman.packet.capture;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import org.springframework.stereotype.Component;

import com.ipman.util.InterfaceData;


@Component
public abstract class AbstractPacketCapture implements PacketCapture {

	InterfaceData data = new InterfaceData(65536, PromiscuousMode.PROMISCUOUS, 1000);
    
	public AbstractPacketCapture() {
		// TODO Auto-generated constructor stub
	}
	public PcapNetworkInterface openNetworkInterface(){

		PcapNetworkInterface pcapInterface = null;

		try {
			InetAddress address = InetAddress.getByName("192.168.220.1");
			pcapInterface = Pcaps.getDevByAddress(address);
		} catch (UnknownHostException | PcapNativeException e) {

			e.printStackTrace();
		}
		return pcapInterface;

	}

	public PcapHandle openHandle() {
		PcapHandle handle = null;
		try {
			handle = openNetworkInterface().openLive(data.snapLen, data.mode, data.timeout);
		} catch (PcapNativeException e) {
			e.printStackTrace();
		}

		return handle;

	}
	
	@Override
	public abstract <T extends Packet> T capturePacket();
}
