package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingClient;

/**
 * Defines a RPC client
 *
 * @author Sebastian Misje Jonassen
 */
public class RPCClient {

    private final MessagingClient MSG_CLIENT;
    private Connection connection;

    /**
     * Creates a new RPC client
     *
     * @param server
     * @param port
     */
    public RPCClient(String server, int port) {
        MSG_CLIENT = new MessagingClient(server, port);
    }

    public void register(RPCStub remote) {
        remote.register(this);
    }

    public void connect() {
        connection = MSG_CLIENT.connect();
    }

    public void disconnect() {

        connection.close();

    }

    public byte[] call(byte[] rpcrequest) {

        connection.send(new Message(rpcrequest));

        Message msg = connection.receive();

        return msg.getData();
    }

}
