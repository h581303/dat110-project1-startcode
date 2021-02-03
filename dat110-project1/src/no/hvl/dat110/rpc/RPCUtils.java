package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RPCUtils {

    // Utility methods for marshalling and marshalling of parameters and return values
    // in RPC request and RPC responses
    // data bytearrays and return byte arrays is according to the
    // RPC message syntax [rpcid,parameter/return value]

    public static byte[] marshallString(byte rpcid, String str) {
        //Creating a byteArray from the string using the CharsetClass
        Charset charset = StandardCharsets.ISO_8859_1;

        byte[] stringByteArray = charset.encode(str).array();
        byte[] encoded = new byte[stringByteArray.length + 1];

        encoded[0] = rpcid;

        System.arraycopy(stringByteArray, 0, encoded, 1, stringByteArray.length);

        return encoded;
    }

    public static String unmarshallString(byte[] data) {
        byte[] byteArray = new byte[data.length - 1];

        if (byteArray.length >= 0) System.arraycopy(data, 1, byteArray, 0, byteArray.length);

        return new String(byteArray, StandardCharsets.ISO_8859_1);
    }

    public static byte[] marshallVoid(byte rpcid) {

        return new byte[]{rpcid};

    }

    public static void unmarshallVoid(byte[] data) {

    }

    public static byte[] marshallBoolean(byte rpcid, boolean b) {
        return new byte[]{rpcid, (byte) (b ? 1 : 0)};
    }

    public static boolean unmarshallBoolean(byte[] data) {
        return (data[1] > 0);
    }

    public static byte[] marshallInteger(byte rpcid, int x) {
        byte[] encoded = new byte[5];
        encoded[0] = rpcid;

        //Converting the integer into an array of bytes
        ByteBuffer b = ByteBuffer.allocate(4).putInt(x);
        byte[] intAsByteArray = b.array();

        //Puttin the array of bytes into the encoded array.
        System.arraycopy(intAsByteArray, 0, encoded, 1, encoded.length - 1);

        return encoded;
    }

    public static int unmarshallInteger(byte[] data) {
        int decoded;

        //shifting the array one forward since we dont need the rpcid which sits at [0]
        byte[] byteArray = new byte[data.length - 1];
        if (byteArray.length >= 0) System.arraycopy(data, 1, byteArray, 0, byteArray.length);

        //Converting the array into Bytebuffer and further to an integer
        ByteBuffer b = ByteBuffer.wrap(byteArray);
        decoded = b.getInt();

        return decoded;

    }
}