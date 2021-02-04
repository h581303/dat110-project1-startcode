package no.hvl.dat110.system.sensor;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class SensorImpl implements RPCImpl {

	static final int RANGE = 20;

	public int read() {

		long seconds = System.currentTimeMillis();

		double temp = RANGE * Math.sin(seconds / 1000.0);

		return (int) Math.ceil(temp);
	}
	
	public byte[] invoke(byte[] request) {
				
		RPCUtils.unmarshallVoid(request); 
		
		int temp = read();
		
		byte rpcid = request[0];

		return RPCUtils.marshallInteger(rpcid,temp);
	}
}
