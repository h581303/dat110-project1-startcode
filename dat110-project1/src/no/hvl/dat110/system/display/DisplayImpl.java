package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

/**
 * Defines a display service
 */
public class DisplayImpl implements RPCImpl {

    /**
     * Prints the given message to the console
     *
     * @param message A message
     */
    public void write(String message) {
        System.out.println("DISPLAY:" + message);
    }

    /**
     * Unmarshalled the given byte array and prints the value to the console
     *
     * @param request Contains the message that is wanted to be printed
     * @return Response
     */
    public byte[] invoke(byte[] request) {
        String msg = RPCUtils.unmarshallString(request);

        write(msg);

        return RPCUtils.marshallVoid(request[0]);
    }
}
