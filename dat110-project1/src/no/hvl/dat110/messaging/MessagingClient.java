package no.hvl.dat110.messaging;

import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

public class MessagingClient {

	private String server;
	private int port;

	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}

	// connect to messaging server
	public Connection connect() {

		Socket clientSocket = null;
<<<<<<< HEAD
=======
		
		
		try {
			clientSocket = new Socket(server, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection connection = new Connection(clientSocket);
>>>>>>> branch 'master' of https://github.com/h581303/dat110-project1-startcode

		// TODO
		// create TCP socket for client and connection
		// create connection object
		
<<<<<<< HEAD
		try {
			clientSocket = new Socket(server, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
=======
		
>>>>>>> branch 'master' of https://github.com/h581303/dat110-project1-startcode

		Connection connection = new Connection(clientSocket);
		
		return connection;
	}
}
