package com.github.jzhongming.udplog.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TcpClient {

	public static void main(String[] args) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new TcpClientInitializer());
			
			ChannelFuture f = b.connect("127.0.0.1", 8080).sync();
			f.channel().writeAndFlush("中华人民共和国");
			// Wait until the connection is closed.  
            f.channel().closeFuture().sync();  
		} finally {
			group.shutdownGracefully();
		}
	}

}
