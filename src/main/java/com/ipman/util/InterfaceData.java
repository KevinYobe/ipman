package com.ipman.util;

import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InterfaceData {
	
	@Value("ipman.capture.interface")
	public String ipAddress = "192.168.220.1";
	
	public int snapLen;
	public PromiscuousMode mode;
	public int timeout;

	public InterfaceData(int snapLen, PromiscuousMode mode, int timeout) {
		this.snapLen = snapLen;
		this.mode = mode;
		this.timeout = timeout;
	}
}