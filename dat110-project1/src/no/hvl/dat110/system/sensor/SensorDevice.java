package no.hvl.dat110.system.sensor;

import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;

public class SensorDevice {

	public static void main(String[] args) {
		
		System.out.println("Sensor server starting ...");
		
		SensorImpl sensor = new SensorImpl();
		
		RPCServer sensorServer = new RPCServer(Common.SENSOR_PORT);
		
	    sensorServer.register(1,sensor);
		
		sensorServer.run();
		
		sensorServer.stop();
		
		System.out.println("Sensor server stopping ...");
		
	}
}
