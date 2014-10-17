package com.github.jzhongming.udplog.network.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jzhongming.udplog.network.udp.UdpServerHandler;

public class DefaultMessageAdapter implements MessageAdapter<String> {
	private final Logger logger = LoggerFactory.getLogger(UdpServerHandler.class);
	
	@Override
	public void send(String msg) {
		logger.info(msg);
	}

}
