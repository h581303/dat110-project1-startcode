package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Utility methods for marshalling and marshalling of parameters and return values
 * in RPC request and RPC responses
 * Data bytearrays and return byte arrays is according to the
 * RPC message syntax [rpcid,parameter/return value]
 */
public class RPCUtils {

    /**
     * Marshall the given rpc id and string
     *
     * @param rpcId An rpc id
     * @param str   A string
     * @return Marshalled byte array
     */
    public static byte[] marshallString(byte rpcId, String str) {
        //Creating a byteArray from the string using the CharsetClass
        Charset charset = StandardCharsets.ISO_8859_1;

        byte[] stringByteArray = charset.encode(str).array();
        byte[] encoded = new byte[stringByteArray.length + 1];

        encoded[0] = rpcId;

        System.arraycopy(stringByteArray, 0, encoded, 1, stringByteArray.length);

        return encoded;
    }

    /**
     * Unmarshall the given byte array
     *
     * @param data Marshalled byte array
     * @return The string that was contained in the unmarshalled array
     */
    public static String unmarshallString(byte[] data) {
        byte[] byteArray = new byte[data.length - 1];

        if (byteArray.length >= 0) System.arraycopy(data, 1, byteArray, 0, byteArray.length);

        return new String(byteArray, StandardCharsets.ISO_8859_1);
    }

    /**
     * Marshall the given rpc id
     *
     * @param rpcId An rpc id
     * @return Marshalled byte array
     */
    public static byte[] marshallVoid(byte rpcId) {
        return new byte[]{rpcId};
    }

    /**
     * Unmarshall the given byte array
     *
     * @param data Marshalled byte array
     */
    public static void unmarshallVoid(byte[] data) {

    }

    /**
     * Marshall the given rpc id and boolean value
     *
     * @param rpcId An rpc id
     * @param b     Boolean value
     * @return Marshalled byte array
     */
    public static byte[] marshallBoolean(byte rpcId, boolean b) {
        return new byte[]{rpcId, (byte) (b ? 1 : 0)};
    }

    /**
     * Unmarshall the given byte array
     *
     * @param data Marshalled byte array
     * @return Boolean value
     */
    public static boolean unmarshallBoolean(byte[] data) {
        return (data[1] > 0);
    }

    /**
     * Marshall the given rpc id and integer value
     *
     * @param rpcId An rpc id
     * @param x     Integer value
     * @return Byte array
     */
    public static byte[] marshallInteger(byte rpcId, int x) {
        byte[] encoded = new byte[5];
        encoded[0] = rpcId;

        //Converting the integer into an array of bytes
        ByteBuffer b = ByteBuffer.allocate(4).putInt(x);
        byte[] intAsByteArray = b.array();

        //Puttin the array of bytes into the encoded array.
        System.arraycopy(intAsByteArray, 0, encoded, 1, encoded.length - 1);

        return encoded;
    }

    /**
     * Unmarshall the given byte array
     *
     * @param data Marshalled byte array
     * @return The integer value that was contained in the given byte array
     */
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
