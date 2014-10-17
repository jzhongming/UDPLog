package com.github.jzhongming.udplog.network.udp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;

import com.github.jzhongming.udplog.config.Configuration;
import com.github.jzhongming.udplog.network.adapter.DefaultMessageAdapter;

public class UdpInitializer extends ChannelInitializer<NioDatagramChannel> {

	private final Configuration config;

	public UdpInitializer(Configuration config) {
		this.config = config;
	}

	@Override
	protected void initChannel(NioDatagramChannel ch) throws Exception {
		System.out.println("init Channel");
		ChannelPipeline pipe = ch.pipeline();
//		pipe.addLast(new LoggingHandler(LogLevel.DEBUG));
		pipe.addLast(new UdpServerHandler(config.getWorkerCount(), new DefaultMessageAdapter()));
	}

}
