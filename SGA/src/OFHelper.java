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
	 * @return
	 */
	public static byte[] boolArrayToByteArray(boolean[] in) {
		byte[] out = new byte[in.length+1];
		for(int i =0; i < in.length; i++) {
			out[i+1] = (byte) (in[i] ? 1 : -1);
		}
		return out;
	}
}
