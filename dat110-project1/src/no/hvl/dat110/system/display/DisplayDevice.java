package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;

/**
 * Simulates a display server
 */
public class DisplayDevice {

    public static void main(String[] args) {

        System.out.println("Display server starting ...");

        DisplayImpl display = new DisplayImpl();

        RPCServer displayServer = new RPCServer(Common.DISPLAY_PORT);

        displayServer.register(1, display);

        displayServer.run();

        displayServer.stop();

        System.out.println("Display server stopping ...");

    }
}
