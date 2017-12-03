/**
 * basic test class. Problem is to find the largest 16 bit number squared. 0th
 * bit is the MSB
 * 
 * @author ryanw
 *
 */
public class Squared implements Problem {
	static int LENGTH = 5;
	public double fitness(boolean[] candidate) {
		double result = 0;
		for (int p = LENGTH - 1; p >= 0; p--) {
			if (candidate[(LENGTH - 1) - p]) {
				result += Math.pow(2, p);
			}
		}
		return Math.pow(result, 2);
	}

	public int getNumChromosomes() {
		return LENGTH;
	}

	public int getMaxOrMin() {
		return 1;
	}

}
