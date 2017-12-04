import java.util.Arrays;

public class HimmelblauFunction implements Problem {

	@Override
	public double fitness(boolean[] candidate) {
		boolean[] xBool = Arrays.copyOf(candidate, 32);
		boolean[] yBool = Arrays.copyOfRange(candidate, 32, 64);
		float x = OFHelper.floatFromBoolArray(xBool);
		float y = OFHelper.floatFromBoolArray(yBool);
		return Math.pow(x * x + y + 11, 2) + Math.pow(x + y * y - 7, 2);
	}

	@Override
	public int getNumChromosomes() {
		return 64;
	}

	@Override
	public int getMaxOrMin() {
		return -1;
	}

}
