package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

import java.util.HashMap;

/**
 * Defines a RPC server
 *
 * @author Sebastian Misje Jonassen
 */
public class RPCServer {

    private final MessagingServer MSG_SERVER;
    private Connection connection;

    // hashmap to register RPC methods which are required to implement RPCImpl
    private final HashMap<Integer, RPCImpl> SERVICES;

    /**
     * Creates a new RPC server which is going to listen to the given socket
     *
     * @param port The port that the server is listening to
     */
    public RPCServer(int port) {

        this.MSG_SERVER = new MessagingServer(port);
        this.SERVICES = new HashMap<>();

        // the stop RPC methods is built into the server
        SERVICES.put((int) RPCCommon.RPIDSTOP, new RPCServerStopImpl());
    }

    /**
     * Starts the server and accepts an incoming connection
     * Then listens for request and executes the corresponding service
     */
    public void run() {

        System.out.println("RPC SERVER RUN - Services: " + SERVICES.size());

        connection = MSG_SERVER.accept();

        System.out.println("RPC SERVER ACCEPTED");

        boolean stop = false;

        while (!stop) {

            int rpcid;

            // TODO
            // - receive message containing RPC request
            // - find the identifier for the RPC methods to invoke
            // - lookup the method to be invoked
            // - invoke the method
            // - send back message containing RPC reply

            Message req = connection.receive();

            byte[] arr = req.getData();

            if (arr.length > 0) {
                rpcid = arr[0];

                if (rpcid == RPCCommon.RPIDSTOP) {
                    stop = true;
                    continue;
                }

                RPCImpl imp = SERVICES.get(rpcid);

                byte[] response = imp.invoke(arr);

                connection.send(new Message(response));
            }
        }
    }

    /**
     * Registers a new service
     *
     * @param rpcId The RPC id
     * @param impl  The service
     */
    public void register(int rpcId, RPCImpl impl) {
        SERVICES.put(rpcId, impl);
    }

    /**
     * Stops the server
     */
    public void stop() {
        connection.close();
        MSG_SERVER.stop();
    }
}
