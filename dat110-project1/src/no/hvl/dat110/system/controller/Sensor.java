package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Sensor extends RPCStub {

	private byte RPCID = 1;
	
	public int read() {
		
		int temp;
		
		// TODO
		// implement marshalling, call and unmarshalling for read RPC method
		
		byte[] request = RPCUtils.marshallInteger(RPCID, temp);
		
		byte[] response = rpcclient.call(request);
		
		RPCUtils.unmarshallInteger(response);
		
		return temp;
	}
	
}
