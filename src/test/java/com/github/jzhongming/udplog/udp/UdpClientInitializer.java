package com.github.jzhongming.udplog.udp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UdpClientInitializer extends ChannelInitializer<NioDatagramChannel> {
	
	@Override
	protected void initChannel(NioDatagramChannel ch) throws Exception {
		System.out.println("init Channel");
		ChannelPipeline pipe = ch.pipeline();
//		pipe.addLast(new LoggingHandler(LogLevel.DEBUG));
		pipe.addLast(new UdpClientHandler());
	}

}
