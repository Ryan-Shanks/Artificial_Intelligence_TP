import java.util.Arrays;

public class RosenbrockValley implements Problem {

	private final float a = 1, b = 100;
	@Override
	public double fitness(boolean[] candidate) {
		boolean[] xBool = Arrays.copyOf(candidate, 32);
		boolean[] yBool = Arrays.copyOfRange(candidate, 32, 64);
		float x = OFHelper.floatFromBoolArray(xBool);
		float y = OFHelper.floatFromBoolArray(yBool);
		
		return Math.pow(a-x, 2) + b*Math.pow(y-x*x, 2);
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
