package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary
		// payload.length() <= 127
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[128];
		
		encoded[0] = (byte)payload.length;
		
		int i = 1;
		
		while(i <= payload.length) {
			encoded[i] = payload[i-1];
			i++;
		}

		return encoded;
		
	}

	public void decapsulate(byte[] received) {
		
		byte[] buffer = new byte[received[0]];
		
		int i = 0;
		
		while(i < buffer.length) {
			buffer[i] = received[i+1];
			i++;
		}
		
		
		
		payload = buffer;
		
	}
}
