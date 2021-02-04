package no.hvl.dat110.rpc;

/**
 * Defines a RPC server stub that stops a server
 */
public class RPCServerStopStub extends RPCStub {

    /**
     * Client-side (local) implementation of the built-in server stop RPC method
     */
    public void stop() {

        byte[] request = RPCUtils.marshallVoid(RPCCommon.RPIDSTOP);

        byte[] response = rpcClient.call(request);

        RPCUtils.unmarshallVoid(response);
    }
}
