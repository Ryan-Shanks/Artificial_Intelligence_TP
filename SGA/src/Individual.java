import java.util.concurrent.ThreadLocalRandom;

public class Individual {
	public boolean[] genes;
	public static Problem prob = null;
	public double fitness;
	
	/**
	 * create a candidate with specific genes
	 * @param genes
	 * @param p
	 */
	public Individual(boolean[] genes) {
		if (prob == null) {
			throw new java.lang.NullPointerException("You must provide a reference to the problem to the candidate class");
		}
		this.genes = genes;
	}
	/**
	 * create a new candidate with random genes
	 */
	public Individual() {
		if (prob == null) {
			throw new java.lang.NullPointerException("You must provide a reference to the problem to the candidate class");
		}
		genes = new boolean[prob.getNumChromosomes()];
		for (int i =0; i < genes.length;i++) {
			genes[i] = ThreadLocalRandom.current().nextBoolean();
		}
	}
	/**
	 * will calculate the fitness of this individual and store it in the fitness variable
	 */
	public void calcFitness(){
		fitness = prob.fitness(genes);
	}
	/**
	 * given this candidate and a partner, randomly cross the 2 gene vectors
	 * @param other
	 * @return a single child candidate
	 */
	public Individual[] mate(Individual other) {
		if (other.genes.length != genes.length) { // should never happen
			return null;
		}else {
			//randomly cross the 2 vectors
			int split = ThreadLocalRandom.current().nextInt(1, genes.length - 1);
			Individual[] ret= {
				new Individual(cross(genes, other.genes, split)), // candidate with the beginning of this and the end of the other
				new Individual(cross(other.genes, genes, split)) // candidate with the beginning of other and the end of this
			};
			return ret;
		}
	}
	public static boolean[] cross(boolean[] begin, boolean[]end, int crossPos) {
		boolean[] ret = new boolean[begin.length];
		int i = 0;
		// copy the array into a new one
		while (i <= crossPos) {
			ret[i] = begin[i];
			i++;
		}
		while (i < end.length) {
			ret[i] = end[i];
			i++;
		}
		return ret;
	}
}
