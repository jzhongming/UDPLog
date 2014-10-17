package com.github.jzhongming.udplog.network.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.github.jzhongming.udplog.network.adapter.MessageAdapter;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
	private final ExecutorService pools;
	private final MessageAdapter<String> adapter;
	private int i;
	
	public UdpServerHandler(int worker, MessageAdapter<String> adapter) {
		this.adapter = adapter;
		pools = Executors.newFixedThreadPool(worker, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "udpWorkerThread-" + (++i));
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		final String message = msg.content().toString(CharsetUtil.UTF_8);
		pools.submit(new Runnable() {
			@Override
			public void run() {
				try {
					adapter.send(message);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		});
	}

}
