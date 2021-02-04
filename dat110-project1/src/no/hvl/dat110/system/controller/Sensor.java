package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class Sensor extends RPCStub {

    private final byte RPC_ID = 1;

    public int read() {

        // TODO
        // implement marshalling, call and unmarshalling for read RPC method

        byte[] response = rpcclient.call(RPCUtils.marshallVoid(RPC_ID));

        return RPCUtils.unmarshallInteger(response);
    }

}
