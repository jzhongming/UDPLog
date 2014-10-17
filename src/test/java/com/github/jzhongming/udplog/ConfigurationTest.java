package com.github.jzhongming.udplog;

import java.io.IOException;

import org.junit.Test;

import com.github.jzhongming.udplog.config.Configuration;

public class ConfigurationTest {
	
	@Test
	public void tcpConfigTest() throws IOException {
		Configuration.TCPConfig.parase("D:\\gitspace\\UDPLog\\src\\main\\resources\\config.properties");
		System.out.println(Configuration.TCPConfig.isTcpNoDelay());
		System.out.println(Configuration.TCPConfig.isKeepAlive());
		System.out.println(Configuration.TCPConfig.getHost());
		System.out.println(Configuration.TCPConfig.getPort());
		System.out.println(Configuration.TCPConfig.getRecvBufferSize());
		System.out.println(Configuration.TCPConfig.getSendBufferSize());
		System.out.println(Configuration.TCPConfig.getWorkerCount());
	}
	@Test
	public void udpConfigTest() throws IOException {
		Configuration.UDPConfig.parase("D:\\gitspace\\UDPLog\\src\\main\\resources\\config.properties");
		System.out.println(Configuration.UDPConfig.isTcpNoDelay());
		System.out.println(Configuration.UDPConfig.isKeepAlive());
		System.out.println(Configuration.UDPConfig.getHost());
		System.out.println(Configuration.UDPConfig.getPort());
		System.out.println(Configuration.UDPConfig.getRecvBufferSize());
		System.out.println(Configuration.UDPConfig.getSendBufferSize());
		System.out.println(Configuration.UDPConfig.getWorkerCount());
	}
}
