package problem;

import problem.Problem;

import java.util.Arrays;

/**
 * basic test class. problem.Problem is to find the largest 16 bit number squared. 0th
 * bit is the MSB
 * 
 * @author ryanw
 *
 */
public class Max16BitInteger implements Problem {
	public double fitness(boolean[] candidate) {
		double result = 0;
		for (int p = 15; p >= 0; p--) {
			if (candidate[15 - p]) {
				result += Math.pow(2, p);
			}
		}
		//return Math.pow(result,2);
		return result;
	}

	public int getNumChromosomes() {
		return 16;
	}

	public int getMaxOrMin() {
		return 1;
	}
	@Override
	public String genesToString(boolean[] genes) {
		return Arrays.toString(genes).replace("true", "1").replace("false", "-1");
	}
}
