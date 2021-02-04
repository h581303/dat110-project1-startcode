package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

/**
 * Defines the local display class
 */
public class Display extends RPCStub {

    private final byte RPC_ID = 1;

    /**
     * Sends a message to the display client
     *
     * @param message The message that is to be displayed
     */
    public void write(String message) {
        byte[] request = RPCUtils.marshallString(RPC_ID, message);

		byte[] response = rpcClient.call(request);

		RPCUtils.unmarshallVoid(response);
    }
}
