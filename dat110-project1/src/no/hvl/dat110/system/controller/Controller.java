package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		System.out.println("Controller starting ...");
		
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		RPCClient displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		RPCClient sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		
		displayclient.connect();
		sensorclient.connect();
		
		Display display = new Display();
		Sensor sensor = new Sensor();
		
		displayclient.register(display);
		sensorclient.register(sensor);
		
		// register stop methods in the RPC layer
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);
		
		for (int i = 0; i<N;i++) {
			display.write(sensor.read() + "");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}