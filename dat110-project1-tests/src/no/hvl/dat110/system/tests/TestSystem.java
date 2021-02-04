package no.hvl.dat110.system.tests;

import no.hvl.dat110.system.controller.Controller;
import no.hvl.dat110.system.display.DisplayDevice;
import no.hvl.dat110.system.sensor.SensorDevice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSystem {

    @Test
    void test() {

        System.out.println("System starting ...");

        Runnable display = () -> DisplayDevice.main(null);
        Runnable sensor = () -> SensorDevice.main(null);
        Runnable controller = () -> Controller.main(null);

        Thread displayThread = new Thread(display);
        Thread sensorThread = new Thread(sensor);
        Thread controllerThread = new Thread(controller);

        displayThread.start();
        sensorThread.start();
        controllerThread.start();

        try {

            displayThread.join();
            sensorThread.join();
            controllerThread.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // we check only termination here
        assertTrue(true);

        System.out.println("System stopping ...");
    }

}
