/**
 * I am not sure if this is a good idea, my thinking is that we can define an
 * interface for all methods that would be required for a given problem, and
 * then later create a class for each problem we want to solve and have the
 * class extend this interface
 * 
 * Only need to instantiate a given problem once, or maybe the whole thing can
 * be static somehow
 * 
 * I think if we can define this interface to include all relevant fields for a
 * given problem it will make the SGA driver functions easy
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

	/**
	 * return 1 if we are to maximize the return value of fitness for this problem,
	 * or -1 if we are to minimize
	 * 
	 */
	public int getMaxOrMin();
}
