/**
 * I am not sure if this is a good idea, my thinking is that we can define an
 * interface for all methods that would be required for a given problem, and
 * then later create a class for each problem we want to solve and have the
 * class extend this interface
 * 
 * @author ryanw
 *
 */
public interface Problem {
	public double fitness(boolean[] candidate);

	/**
	 * return a positive int that is the length of the boolean vector representing
	 * an individual for this problem
	 * 
	 * @return a constant value
	 */
	public int getNumChromosomes();
}
