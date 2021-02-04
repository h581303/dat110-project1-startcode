package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class TestBooleanBooleanImpl implements RPCImpl {

    public byte[] invoke(byte[] request) {

        boolean b = RPCUtils.unmarshallBoolean(request);

        boolean resb = m(b);

		return RPCUtils.marshallBoolean(request[0], resb);
    }

    public boolean m(boolean b) {
        System.out.println("boolean m(" + b + ") executed");
        return (!b);
    }
}
