package com.github.jzhongming.udplog.network.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.github.jzhongming.udplog.bootstrap.IServer;
import com.github.jzhongming.udplog.config.Configuration;

public class TcpServer implements IServer {

	private static final ServerBootstrap bootstrap = new ServerBootstrap();
	private final EventLoopGroup bossGroup;
	private final EventLoopGroup workGroup;
	private final Configuration config;

	public TcpServer(Configuration config) {
		this.config = config;
		bossGroup = new NioEventLoopGroup();
		workGroup = new NioEventLoopGroup(config.getWorkerCount());
	}

	@Override
	public void start() throws Exception {
		try {
			bootstrap.group(bossGroup, workGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, config.isKeepAlive());
			bootstrap.option(ChannelOption.TCP_NODELAY, config.isTcpNoDelay());
			bootstrap.option(ChannelOption.SO_SNDBUF, config.getSendBufferSize());
			bootstrap.option(ChannelOption.SO_RCVBUF, config.getRecvBufferSize());
			bootstrap.childHandler(new TcpInitializer(config));
			System.out.println("TcpServer listening is start! local:" + config.getHost() + " port:" + config.getPort());
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
		System.out.println("TcpServer stoped!");
	}
}