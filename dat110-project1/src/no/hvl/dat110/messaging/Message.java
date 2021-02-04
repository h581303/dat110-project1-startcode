package no.hvl.dat110.messaging;

/**
 * Defines a message
 *
 * @author Sebastian Misje Jonassen
 */
public class Message {

    private byte[] payload;

    /**
     * Creates a new Message object with the given payload as payload
     *
     * @param payload The information that is going to be sent, which must be less than 127 bytes
     */
    public Message(byte[] payload) {
        if (payload.length < 128) this.payload = payload;

        else System.out.println("Payload too big");
    }

    public Message() {
        super();
    }

    /**
     * Converts the payload into a 128 byte long array
     *
     * @return byte array ready to be sent
     */
    public byte[] encapsulate() {

        byte[] encoded = new byte[128];

        encoded[0] = (byte) payload.length;

        System.arraycopy(payload, 0, encoded, 1, payload.length);

        return encoded;
    }

    /**
     * Converts the received byte array into a byte array without the padding and first byte
     *
     * @param received 128 byte long array containing information
     */
    public void decapsulate(byte[] received) {

        byte[] buffer = new byte[received[0]];

        if (buffer.length >= 0) System.arraycopy(received, 1, buffer, 0, buffer.length);

        payload = buffer;
    }

    /**
     * Returns the data contained in this message
     *
     * @return The payload
     */
    public byte[] getData() {
        return this.payload;
    }

}
