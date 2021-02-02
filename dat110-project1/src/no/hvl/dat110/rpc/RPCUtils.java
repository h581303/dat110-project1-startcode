package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		// TODO: marshall RPC identifier and string into byte array

		
		//Creating a byteArray from the string using the CharsetClass
		
		Charset charset = StandardCharsets.ISO_8859_1;
		byte[] stringByteArray = charset.encode(str).array();
		byte[] encoded = new byte[stringByteArray.length+1];

		encoded[0] = rpcid;

		for(int i = 1; i<=stringByteArray.length; i++) {
			encoded[i] = stringByteArray[i-1];
		}
		
		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		// TODO: unmarshall String contained in data into decoded
		

						
		byte[] byteArray = new byte[data.length-1];
		
		for(int i = 1; i<=byteArray.length; i++) {
			byteArray[i-1] = data[i];
		}
		
		String decoded = new String(byteArray);
		
		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded;

		// TODO: marshall RPC identifier in case of void type

		if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		// TODO: marshall RPC identifier and string into byte array
		
		byte[] encoded = new byte[5];
		encoded[0] = rpcid;
		
		//Converting the integer into an array of bytes
		ByteBuffer b = ByteBuffer.allocate(4).putInt(x);
		byte[] intAsByteArray = b.array();
				
		
		//Puttin the array of bytes into the encoded array.
		for(int i = 1; i<encoded.length;i++) {
			encoded[i] = intAsByteArray[i-1];
		}



		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		// TODO: unmarshall integer contained in data
		
		
		int decoded;
		
		//shifting the array one forward since we dont need the rpcid which sits at [0]
		byte[] byteArray = new byte[data.length-1];
		for(int i = 0; i < byteArray.length; i++) {
			byteArray[i] = data[i+1];
		}
		
		//Converting the array into Bytebuffer and further to an integer
		ByteBuffer b = ByteBuffer.wrap(byteArray);
		decoded = b.getInt();
		
		return decoded;

	}
}
