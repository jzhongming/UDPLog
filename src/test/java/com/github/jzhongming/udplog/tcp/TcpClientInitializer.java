package com.github.jzhongming.udplog.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class TcpClientInitializer extends ChannelInitializer<SocketChannel> {
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		System.out.println("init Channel");
		ChannelPipeline pipe = ch.pipeline();
//		pipe.addLast(new LoggingHandler(LogLevel.DEBUG));
		pipe.addLast("decode", new StringDecoder(CharsetUtil.UTF_8));
		pipe.addLast("encode", new StringEncoder(CharsetUtil.UTF_8));
		pipe.addLast(new TcpClientHandler());
	}

}
