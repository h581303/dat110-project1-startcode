package no.hvl.dat110.system.sensor;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

/**
 * Defines a sensor service
 */
public class SensorImpl implements RPCImpl {

	static final int RANGE = 20;

	/**
	 * Reads the temperature (Picks a random number in the range [-20,20]
	 *
	 * @return The temperature
	 */
	public int read() {

		long seconds = System.currentTimeMillis();

		double temp = RANGE * Math.sin(seconds / 1000.0);

		return (int) Math.ceil(temp);
	}

	/**
	 * Unmarshalled the given byte array and return a marshalled byte array containing the temperature
	 *
	 * @param request Contains the request
	 * @return Marshalled byte array containing the temperature
	 */
	public byte[] invoke(byte[] request) {
				
		RPCUtils.unmarshallVoid(request); 
		
		int temp = read();
		
		byte rpcid = request[0];

		return RPCUtils.marshallInteger(rpcid,temp);
	}
}
