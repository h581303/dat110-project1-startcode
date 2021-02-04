package no.hvl.dat110.rpc;

/**
 * Defines a generic RPC stub that client-side stubs must extend
 */
public abstract class RPCStub {

    protected RPCClient rpcClient;

    /**
     * Registers the given rpc client to this stub
     *
     * @param rpcClient Rpc client
     */
    public void register(RPCClient rpcClient) {
        this.rpcClient = rpcClient;
    }
}
