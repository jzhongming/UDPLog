//package com.github.jzhongming.udplog.network.udp;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.Channel;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.DatagramPacket;
//import io.netty.channel.socket.nio.NioDatagramChannel;
//import io.netty.handler.logging.LoggingHandler;
//import io.netty.util.CharsetUtil;
//
//import java.net.InetSocketAddress;
//
//public class UdpClient {
//	public static void main(String[] args) throws Exception {
//
//		EventLoopGroup group = new NioEventLoopGroup();
//		try {
//			Bootstrap b = new Bootstrap();
//			b.group(group).channel(NioDatagramChannel.class).handler(new LoggingHandler());
//
//			Channel ch = b.bind(0).sync().channel();
//			for(int i=0; i<1000000; i++) {
//				ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("QOTM:"+i, CharsetUtil.UTF_8), new InetSocketAddress("127.0.0.1", 1234))).sync();
////				ch.writeAndFlush("QQQ:" + i);
//			}
//			if (!ch.closeFuture().await(5000)) {
//				System.err.println("QOTM request timed out.");
//			}
//		} finally {
//			group.shutdownGracefully();
//		}
//	}
//}
package com.github.jzhongming.udplog.udp;

import java.nio.charset.Charset;

import com.github.jzhongming.udplog.commons.UdpClientSocket;

public class UdpClient {
	public static void main(String[] args) throws Exception {

//		EventLoopGroup group = new NioEventLoopGroup();
//		try {
//			Bootstrap b = new Bootstrap();
//			b.group(group).channel(NioDatagramChannel.class).handler(new UdpClientInitializer());
//			Channel ch = b.bind(0).sync().channel();
//			// 控制台输入
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//			for (;;) {
//				String line = in.readLine();
//				if (line == null) {
//					continue;
//				}
//				System.out.println("myline:" + line);
//				ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(line, CharsetUtil.UTF_8), new InetSocketAddress("127.0.0.1", 1234)));
//			}
//			for(int i=0; i<1000000; i++) {
//				ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("udp log:" + i, CharsetUtil.UTF_8), new InetSocketAddress("127.0.0.1", 1234)));
//			}
//			ch.write("").sync();
//		} finally {
//			group.shutdownGracefully();
//		}
		String c = "中华人民共和国！";
		UdpClientSocket helper = new UdpClientSocket();
		while(true)
		helper.send("127.0.0.1", 1234, c.getBytes(Charset.forName("UTF-8")));
	}
}
