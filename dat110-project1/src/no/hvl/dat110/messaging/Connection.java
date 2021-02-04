package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Defines a connection which can send and receive messages
 */
public class Connection {

    private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
    private DataInputStream inStream; // for reading bytes from the underlying TCP connection
    private Socket socket; // socket for the underlying TCP connection

    /**
     * Creates a new connection with the given socket
     *
     * @param socket A socket
     */
    public Connection(Socket socket) {

        try {
            this.socket = socket;

            outStream = new DataOutputStream(socket.getOutputStream());

            inStream = new DataInputStream(socket.getInputStream());

        } catch (IOException ex) {

            System.out.println("Connection: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Sends a Message object to the underlying stream
     *
     * @param message A message
     */
    public void send(Message message) {
        try {
            outStream.write(message.encapsulate());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Receives a message from the underlying stream
     *
     * @return The received message
     */
    public Message receive() {
        byte[] recvbuf = new byte[128];

        try {
            inStream.read(recvbuf, 0, 128);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Message message = new Message();

        message.decapsulate(recvbuf);

        return message;

    }

    /**
     * Closes the connection by closing streams and the underlying socket
     */
    public void close() {

        try {
            outStream.close();
            inStream.close();

            socket.close();
        } catch (IOException ex) {

            System.out.println("Connection: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}