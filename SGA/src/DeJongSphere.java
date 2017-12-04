
public class DeJongSphere implements Problem{
	int n = 2;
	@Override
	public double fitness(boolean[] candidate) {
		float fitness = 0, floatValue;
		boolean[] portion = new boolean[32];
		for (int i = 0; i < candidate.length/32; i++) {
			System.arraycopy(candidate, i *32, portion, 0, 32);
			floatValue = OFHelper.floatFromBoolArray(portion);
			fitness += Math.pow(floatValue, 2);
		}
		return fitness;
	}

	@Override
	public int getNumChromosomes() {
		return n * 32;
	}

	@Override
	public int getMaxOrMin() {
		return -1;
	}
}
