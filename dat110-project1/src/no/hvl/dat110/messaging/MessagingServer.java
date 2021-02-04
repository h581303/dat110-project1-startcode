package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Defines a messaging server
 *
 * @author Sebastian Misje Jonassen
 */
public class MessagingServer {

	private ServerSocket welcomeSocket;

	/**
	 * Creates a new Messaging server object which is going to listen on the given port
	 *
	 * @param port The port which the server will listen to
	 */
	public MessagingServer(int port) {
		try {
			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {
			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Accept an incoming connection from a messaging client
	 *
	 * @return A connection to the client
	 */
	public Connection accept() {
		Socket connectionSocket;
		Connection connection = null;

		try {
			connectionSocket = welcomeSocket.accept();
			
			connection = new Connection(connectionSocket);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}

		return connection;
	}

	/**
	 * Stops the server
	 */
	public void stop() {

		if (welcomeSocket != null) {
			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
