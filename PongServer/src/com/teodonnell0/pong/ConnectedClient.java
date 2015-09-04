package com.teodonnell0.pong;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ConnectedClient implements Runnable {

	private static int ids = 0;

	private final int clientId;

	private final Server server;
	
	private final Socket socket;
	private final ObjectOutputStream objectOutputStream;
	private final ObjectInputStream  objectInputStream;

	private final Logger log = Logger.getLogger(ConnectedClient.class);

	private Player player;
	
	public ConnectedClient(Server server, Socket socket) throws IOException {
		this.clientId = ids++;
		this.server = server;
		this.socket = socket;

		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();

		this.objectInputStream = (ObjectInputStream) inputStream;
		this.objectOutputStream = (ObjectOutputStream) outputStream;
	}

	public boolean sendProtocol(GameProtocol gameProtocol) {
		try {
			objectOutputStream.writeObject(gameProtocol);
			objectOutputStream.flush();
		} catch (IOException e) {
			log.fatal("Could not send protocol, " + gameProtocol.getProtocol().toString() + " to client-"+clientId);
			try {
				objectInputStream.close();
				objectOutputStream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return false;
		}
		return true;
	}

	public int getClientId() {
		return clientId;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
