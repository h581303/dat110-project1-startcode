package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

/**
 * Defines a controller that talks to sensors and displays
 */
public class Controller {

    private static final int N = 5;

    public static void main(String[] args) {
        System.out.println("Controller starting ...");

        RPCServerStopStub stopDisplay = new RPCServerStopStub();
        RPCServerStopStub stopSensor = new RPCServerStopStub();

        RPCClient displayClient = new RPCClient(Common.DISPLAY_HOST, Common.DISPLAY_PORT);
        RPCClient sensorClient = new RPCClient(Common.SENSOR_HOST, Common.SENSOR_PORT);

        displayClient.connect();
        sensorClient.connect();

        Display display = new Display();
        Sensor sensor = new Sensor();

        sensorClient.register(sensor);
        displayClient.register(display);

        // register stop methods in the RPC layer
        displayClient.register(stopDisplay);
        sensorClient.register(stopSensor);

        for (int i = 0; i < N; i++) {
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
