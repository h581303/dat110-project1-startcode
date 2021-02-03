package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

import java.util.HashMap;

public class RPCServer {

    private MessagingServer msgserver;
    private Connection connection;

    // hashmap to register RPC methods which are required to implement RPCImpl

    private HashMap<Integer, RPCImpl> services;

    public RPCServer(int port) {

        this.msgserver = new MessagingServer(port);
        this.services = new HashMap<>();

        // the stop RPC methods is built into the server
        services.put((int) RPCCommon.RPIDSTOP, new RPCServerStopImpl());
    }

    public void run() {

        System.out.println("RPC SERVER RUN - Services: " + services.size());

        connection = msgserver.accept();

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

            Message rec = connection.receive();

            byte[] arr = rec.getData();

            if(arr.length > 0){
				rpcid = arr[0];

				if (rpcid == RPCCommon.RPIDSTOP) {
					stop = true;
					continue;
				}

				RPCImpl imp = services.get(rpcid);

				byte[] response = imp.invoke(arr);

				try {
					connection.send(new Message(response));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
    }

    public void register(int rpcid, RPCImpl impl) {
        services.put(rpcid, impl);
    }

    public void stop() {
        connection.close();
        msgserver.stop();

    }
}