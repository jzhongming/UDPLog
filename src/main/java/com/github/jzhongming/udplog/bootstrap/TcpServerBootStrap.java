package com.github.jzhongming.udplog.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jzhongming.udplog.config.Configuration;
import com.github.jzhongming.udplog.network.tcp.TcpServer;

public final class TcpServerBootStrap {

	private static final Logger logger = LoggerFactory.getLogger(TcpServerBootStrap.class);
	private static IServer server;

	public static void main(String[] args) throws Exception {
		logger.info("start tcp Server");
		Configuration type = Configuration.TCPConfig.parase("D:\\gitspace\\UDPLog\\src\\main\\resources\\config.properties");
		registerShtudownHook();
		server = new TcpServer(type);
		server.start();
	}

	private static void registerShtudownHook() {
		System.out.println("registerShtudownHook start");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				// 关闭服务
				if(null != server)
					server.stop();
				try {
					System.out.println("run the finalize!!");
					super.finalize();
				} catch (Throwable e) {
					logger.error("super.finalize() error when stop server", e);
				}
			}
		});
	}
}
