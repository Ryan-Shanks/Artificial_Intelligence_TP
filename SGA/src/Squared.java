/**
 * basic test class. Problem is to find the largest 16 bit number squared. 0th
 * bit is the MSB
 * 
 * @author ryanw
 *
 */
public class Squared implements Problem {
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

}
