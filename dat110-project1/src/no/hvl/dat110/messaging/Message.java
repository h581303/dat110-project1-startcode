package no.hvl.dat110.messaging;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		if(payload.length <= 128) {
			this.payload = payload;
			} // TODO: check for length within boundary
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

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
}
