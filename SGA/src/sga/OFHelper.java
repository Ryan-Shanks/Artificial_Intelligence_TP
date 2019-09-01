package sga;
/**
 * Helper methods that are commonly used by the objective functions (aka fitness
 * functions)
 * 
 * @author ryanw
 *
 */
public class OFHelper {
	/**
	 * convert a 0 indexed boolean array to a 1 indexed byte array, where each byte
	 * is 1 if the boolean was true, or -1 if it was false. This will be useful for
	 * many of the given objective functions.
	 * 
	 * @return byte array
	 */
	public static byte[] boolArrayToByteArray(boolean[] in) {
		byte[] out = new byte[in.length+1];
		for(int i =0; i < in.length; i++) {
			out[i+1] = (byte) (in[i] ? 1 : -1);
		}
		return out;
	}
	/**
	 * takes a boolean vector of length 32 converts it to a float
	 * @return float
	 */
	public static float floatFromBoolArray(boolean[] input) {
		if (input.length != 32) {
			throw new IllegalArgumentException("FloatFromBoolArray recieved an array that was not 32 bits long!");
		}
		int val = 0;
		for (int i =0; i < 32; i++) {
			if(input[i]) {
				val =val |1;
			}
			if (i < 31) { // dont shift the last one
				val = val << 1;
			}
		}
		//so now val is 4 bytes and contains the 32 boolean bits.
		// now we need to somehow represent it as a float without casting it.
		return Float.intBitsToFloat(val);
	}
}
