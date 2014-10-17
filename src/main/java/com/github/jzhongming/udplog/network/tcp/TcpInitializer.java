package com.github.jzhongming.udplog.network.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import com.github.jzhongming.udplog.config.Configuration;
import com.github.jzhongming.udplog.network.adapter.DefaultMessageAdapter;

public class TcpInitializer extends ChannelInitializer<SocketChannel> {
	private final Configuration config;

	public TcpInitializer(Configuration config) {
		this.config = config;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
//		pipeline.addLast("LOGGING_HANDLER", new LoggingHandler(LogLevel.INFO));
		pipeline.addLast("decode", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("encode", new StringEncoder(CharsetUtil.UTF_8));
		pipeline.addLast("serverHandler", new TcpServerHandler(config.getWorkerCount(), new DefaultMessageAdapter()));
	}

}
