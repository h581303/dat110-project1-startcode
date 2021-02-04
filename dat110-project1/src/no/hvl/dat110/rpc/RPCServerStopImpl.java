package no.hvl.dat110.rpc;

/**
 * Defines a service that stops a rpc server
 */
public class RPCServerStopImpl implements RPCImpl {
    /**
     * Server-side (remote) implementation of the built-in stop RPC method
     *
     * @param request A request to close the server
     * @return Byte array ready to be sent to the server
     */
    public byte[] invoke(byte[] request) {
        RPCUtils.unmarshallVoid(request);

        byte[] reply = RPCUtils.marshallVoid(RPCCommon.RPIDSTOP);

        stop();

        return reply;
    }

    /**
     * Prints that the server is executing the stop method
     */
    public void stop() {
        System.out.println("RPC server executing stop");
    }
}
