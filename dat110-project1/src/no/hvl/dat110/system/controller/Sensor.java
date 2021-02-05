package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class Sensor extends RPCStub {

    private final byte RPC_ID = 1;

    /**
     * Sends a rpc call to the sensor client to send the temperature
     * back
     *
     * @return The temperature received from the sensor
     */
    public int read() {
        byte[] response = rpcclient.call(RPCUtils.marshallVoid(RPC_ID));

        return RPCUtils.unmarshallInteger(response);
    }

}
