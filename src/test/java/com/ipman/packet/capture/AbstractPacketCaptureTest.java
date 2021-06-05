package com.ipman.packet.capture;

import static org.junit.jupiter.api.Assertions.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.IpV4Packet;
import org.springframework.beans.factory.annotation.Autowired;

import com.ipman.util.InterfaceData;

class AbstractPacketCaptureTest {
	
	static InetAddress addr;
	static PcapNetworkInterface nif;
	static AbstractPacketCapture capture; 
	int snapLen = 65536;
	PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
	int timeout = 10;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		addr = InetAddress.getByName("192.168.220.1");
		nif = Pcaps.getDevByAddress(addr);
		capture = new IPPacketCapture();
	
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testOpenNetworkInterface() throws PcapNativeException {
		
		System.out.print(addr);
		assertEquals(nif, capture.openNetworkInterface(), "Interface Opened");
		
	}

	@Test
	void testOpenHandle() {
		InterfaceData data = new InterfaceData(65536, PromiscuousMode.PROMISCUOUS, 1000);
		

		try {
			nif = Pcaps.getDevByAddress(addr);
			PcapHandle handle = nif.openLive(data.snapLen, data.mode, data.timeout);
			assertEquals(handle, capture.openHandle());
		} catch (PcapNativeException e) {
		
			e.printStackTrace();
		}
		
	}

	@Test
	void testCapturePacket() {
		
		assertEquals(IpV4Packet.class, capture.capturePacket().getClass());
	}

}
