package com.teodonnell0.pong;

import com.teodonnell0.pong.enums.Protocol;

public class GameProtocol {

	private final Protocol protocol;
	private final Object[] data;
	
	public GameProtocol(Protocol protocol, Object...data) {
		this.protocol = protocol;
		this.data = data;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public Object[] getData() {
		return data;
	}
	
}
