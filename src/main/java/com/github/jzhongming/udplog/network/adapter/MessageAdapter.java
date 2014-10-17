package com.github.jzhongming.udplog.network.adapter;

public interface MessageAdapter<T> {
	public void send(T msg);
}
