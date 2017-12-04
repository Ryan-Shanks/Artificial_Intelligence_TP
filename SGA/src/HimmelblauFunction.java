import java.util.Arrays;
/**
 * representation for the Himmelblau function. First 32 bits of the bool vector are x, next 32 are y
 * @author ryanw
 *
 */
public class HimmelblauFunction implements Problem {

	@Override
	public double fitness(boolean[] candidate) {
		boolean[] xBool = Arrays.copyOf(candidate, 32);
		boolean[] yBool = Arrays.copyOfRange(candidate, 32, 64);
		double x = OFHelper.floatFromBoolArray(xBool);
		double y = OFHelper.floatFromBoolArray(yBool);
		return Math.pow(x * x + y - 11, 2) + Math.pow(x + y * y - 7, 2);
	}

	@Override
	public int getNumChromosomes() {
		return 64; // takes 2 floats, each is 32 bits
	}

	@Override
	public int getMaxOrMin() {
		return -1;
	}

	@Override
	public String genesToString(boolean[] genes) {
		boolean[] xBool = Arrays.copyOf(genes, 32);
		boolean[] yBool = Arrays.copyOfRange(genes, 32, 64);
		float x = OFHelper.floatFromBoolArray(xBool);
		float y = OFHelper.floatFromBoolArray(yBool);
		return "x = " + x + " y = " + y;
	}
	
}
