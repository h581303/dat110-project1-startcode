package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller {

    private static int N = 5;

    public static void main(String[] args) {

        Display display;
        Sensor sensor;

        RPCClient displayClient, sensorClient;

        System.out.println("Controller starting ...");

        RPCServerStopStub stopDisplay = new RPCServerStopStub();
        RPCServerStopStub stopSensor = new RPCServerStopStub();

        displayClient = new RPCClient(Common.DISPLAY_HOST, Common.DISPLAY_PORT);
        sensorClient = new RPCClient(Common.SENSOR_HOST, Common.SENSOR_PORT);

        // TODO
        // connect to sensor and display RPC servers
        // create local display and sensor objects
        // register display and sensor objects in the RPC layer

        displayClient.connect();
        sensorClient.connect();

        display = new Display();
        sensor = new Sensor();

        sensorClient.register(sensor);
        displayClient.register(display);

        // register stop methods in the RPC layer
        displayClient.register(stopDisplay);
        sensorClient.register(stopSensor);

        // TODO:
        // loop while reading from sensor and write to display via RPC

		for (int i = 0; i < 5; i++) {
			display.write(sensor.read() + "");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

        stopDisplay.stop();
        stopSensor.stop();

        displayClient.disconnect();
        sensorClient.disconnect();

        System.out.println("Controller stopping ...");

    }
}
