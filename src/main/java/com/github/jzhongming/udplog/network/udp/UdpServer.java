package com.github.jzhongming.udplog.network.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import com.github.jzhongming.udplog.bootstrap.IServer;
import com.github.jzhongming.udplog.config.Configuration;

public class UdpServer implements IServer {

	private static final Bootstrap bootstrap = new Bootstrap();
	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(4);
	private final Configuration config;

	public UdpServer(Configuration config) {
		this.config = config;
	}

	@Override
	public void start() throws Exception {
		try {
			bootstrap.group(bossGroup);
			bootstrap.channel(NioDatagramChannel.class);
			bootstrap.option(ChannelOption.SO_BROADCAST, config.isBroadCast());
			bootstrap.option(ChannelOption.SO_SNDBUF, config.getSendBufferSize());
			bootstrap.option(ChannelOption.SO_RCVBUF, config.getRecvBufferSize());
			bootstrap.handler(new UdpInitializer(config));
			System.out.println("UdpServer listening is start! local:" + config.getHost() + " port:" + config.getPort());
			bootstrap.bind(config.getHost(), config.getPort()).sync().channel().closeFuture().sync();// 此处会阻塞
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
		}
	}

	@Override
	public void stop() {
		bossGroup.shutdownGracefully();
		System.out.println("UdpServer stoped!");
	}
}