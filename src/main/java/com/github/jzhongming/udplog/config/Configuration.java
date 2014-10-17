package com.github.jzhongming.udplog.config;

import java.io.IOException;

import com.github.jzhongming.udplog.commons.PropertiesUtil;

public enum Configuration {
	TCPConfig {
		@Override
		public Configuration parase(String confPath) throws IOException {
			PropertiesUtil u = new PropertiesUtil(confPath);
			tcpNoDelay = u.getBoolean("tcp.tcpnodelay");
			keepAlive = u.getBoolean("tcp.keepalive");
			host = u.getString("tcp.host");
			port = u.getInt("tcp.port");
			recvBufferSize = u.getInt("tcp.recvBufferSize");
			sendBufferSize = u.getInt("tcp.sendBufferSize");
			workCount = u.getInt("tcp.workCount");
			return this;
		}
	},
	UDPConfig {
		@Override
		public Configuration parase(String confPath) throws IOException {
			PropertiesUtil u = new PropertiesUtil(confPath);
			brodCast = u.getBoolean("udp.brodCast");
			host = u.getString("udp.host");
			port = u.getInt("udp.port");
			recvBufferSize = u.getInt("udp.recvBufferSize");
			sendBufferSize = u.getInt("udp.sendBufferSize");
			workCount = u.getInt("udp.workCount");
			return this;
		}
	};

	private static boolean tcpNoDelay;
	private static boolean keepAlive;
	private static boolean brodCast;
	private static String host;
	private static int port;
	private static int recvBufferSize;
	private static int sendBufferSize;
	private static int workCount;

	public abstract Configuration parase(final String confPath) throws IOException;

	public boolean isTcpNoDelay() {
		if( this == UDPConfig) {
			throw new IllegalStateException("udp is not suport tcpNoDelay");
		}
		return tcpNoDelay;
	}

	public boolean isKeepAlive() {
		if( this == UDPConfig) {
			throw new IllegalStateException("udp is not suport keepAlive");
		}
		return keepAlive;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public int getWorkerCount() {
		return workCount;
	}

	public int getRecvBufferSize() {
		return recvBufferSize;
	}

	public int getSendBufferSize() {
		return sendBufferSize;
	}
	
	public boolean isBroadCast() {
		if(this == TCPConfig) {
			throw new IllegalStateException("tcp is not suport BroadCast");
		}
		return brodCast;
	}

}
