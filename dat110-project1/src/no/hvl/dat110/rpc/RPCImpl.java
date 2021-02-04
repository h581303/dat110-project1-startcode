package no.hvl.dat110.rpc;

/**
 * Interface for RPC implementation classes
 */
public interface RPCImpl {
    /**
     * Method for doing that which the classes that implements this method wants
     *
     * @param request Contains what the class is requested to do
     * @return A response
     */
    byte[] invoke(byte[] request);
}
