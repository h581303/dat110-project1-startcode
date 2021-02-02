package no.hvl.dat110.messaging;

public class Message {

    private byte[] payload;

<<<<<<< HEAD
	public Message(byte[] payload) {
		if(payload.length <= 128) {
			this.payload = payload;
			} // TODO: check for length within boundary
	}
=======
    /**
     * Creates a new Message object with the given payload as payload
     *
     * @param payload The information that is going to be sent, which must be less than 128 bytes
     * @throws Exception If the payload is over 127 bytes this get thrown
     */
    public Message(byte[] payload) throws Exception {
        if (payload.length > 127) {
            throw new Exception("Payload to big");
        }
>>>>>>> branch 'master' of https://github.com/h581303/dat110-project1-startcode

        this.payload = payload; // TODO: check for length within boundary
        // payload.length() <= 127
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

<<<<<<< HEAD
	public byte[] encapsulate() {
		
		//Making the encoded array with the fixed size
		
		byte[] encoded = new byte[128];
		
		// TODO
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format
		
		
		encoded[0] = (byte)payload.length;
		
		int i = 1;
		
		while(i <= payload.length) {
			encoded[i] = payload[i-1];
			
			i++;
		}

		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		
		byte[] buffer = new byte[received[0]];
		
		int i = 0;
		
		while (i < received[0]) {
			
			buffer[i] = received[i+1];
			i++;
		}
		
		payload = buffer; 
		
	}
	
	@Override
	public String toString() {
		
		String outstr = "";
		
		for(byte b : payload) {
			outstr = outstr + " " + b;
		}
		
		return outstr;
	}
=======
>>>>>>> branch 'master' of https://github.com/h581303/dat110-project1-startcode
}
