package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestIntIntStub extends RPCStub {

    private final byte RPC_ID = 3;

    public int m(int x) {

        byte[] request = RPCUtils.marshallInteger(RPC_ID, x);

        byte[] reply = rpcClient.call(request);

		return RPCUtils.unmarshallInteger(reply);
    }
}
