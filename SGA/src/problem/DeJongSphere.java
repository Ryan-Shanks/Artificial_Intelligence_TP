package problem;

import problem.Problem;
import sga.OFHelper;

public class DeJongSphere implements Problem {
	private int n = 2;

	@Override
	public double fitness(boolean[] candidate) {
		double fitness = 0, dValue;
		boolean[] portion = new boolean[32];
		for (int i = 0; i < n; i++) {
			System.arraycopy(candidate, i * 32, portion, 0, 32);
			dValue = OFHelper.floatFromBoolArray(portion);
			fitness += Math.pow(dValue, 2);
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

	@Override
	public String genesToString(boolean[] genes) {
		StringBuilder r = new StringBuilder();
		float floatValue;
		boolean[] portion = new boolean[32];
		for (int i = 0; i < genes.length / 32; i++) {
			System.arraycopy(genes, i * 32, portion, 0, 32);
			floatValue = OFHelper.floatFromBoolArray(portion);
			r.append("x").append(i).append(": ").append(floatValue).append(" ");
		}
		return r.toString();
	}

}
