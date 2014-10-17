package com.github.jzhongming.udplog.network.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.github.jzhongming.udplog.network.adapter.MessageAdapter;

public class TcpServerHandler extends SimpleChannelInboundHandler<String> {
	private final ExecutorService pools;
	private final MessageAdapter<String> adapter;
	private int i;

	public TcpServerHandler(int worker, MessageAdapter<String> adapter) {
		this.adapter = adapter;
		pools = Executors.newFixedThreadPool(worker, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "tcpWorkerThread-" + (++i));
			}
		});
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("from：" + ctx.channel().remoteAddress());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		final String message = msg;
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
//		ctx.writeAndFlush(msg);
//		ctx.fireChannelRead(msg);
	}

}
