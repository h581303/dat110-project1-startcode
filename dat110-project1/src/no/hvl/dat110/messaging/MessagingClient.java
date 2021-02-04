package no.hvl.dat110.messaging;

import java.io.IOException;

import java.net.Socket;

/**
 * Defines a Messaging client
 *
 * @author Sebastian Misje Jonassen
 */
public class MessagingClient {

	private final String SERVER;
	private final int PORT;

	/**
	 * Creates a new Messaging client with the given server address and port
	 *
	 * @param server Server address
	 * @param port The port the server is listening on
	 */
	public MessagingClient(String server, int port) {
		SERVER = server;
		PORT = port;
	}

	/**
	 * Tries to connect to the messaging server
	 * by creating a TCP socket with the server address and port
	 * then creating a new connection with the created socket
	 *
	 * @return A connection to the server
	 */
	public Connection connect() {
		Socket clientSocket = null;
		
		try {
			clientSocket = new Socket(SERVER, PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert clientSocket != null;
		return new Connection(clientSocket);
	}
}
