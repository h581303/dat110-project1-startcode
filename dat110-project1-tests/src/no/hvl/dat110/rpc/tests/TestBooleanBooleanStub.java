package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestBooleanBooleanStub extends RPCStub {

	private final byte RPC_ID = 4;
	
	public boolean m(boolean b) {
		
		byte[] request = RPCUtils.marshallBoolean(RPC_ID,b);
		
		byte[] reply = rpcClient.call(request);

		return RPCUtils.unmarshallBoolean(reply);
	}
	
}
