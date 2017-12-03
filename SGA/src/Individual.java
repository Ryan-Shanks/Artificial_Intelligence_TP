import java.util.concurrent.ThreadLocalRandom;

public class Individual {
	public boolean[] genes;
	public static Problem prob = null;
	public double fitness;

	private float chanceOfMutation; // possibility of variation within the population
	private static final float DEFAULT_CHANCE_OF_MUTATION = 0.05f;

	/**
	 * create a candidate with specific genes
	 * 
	 * @param genes
	 * @param p
	 */
	public Individual(boolean[] genes, float chanceOfMutation) {
		if (prob == null) {
			throw new java.lang.NullPointerException(
					"You must provide a reference to the problem to the candidate class");
		}
		this.genes = genes;
		this.chanceOfMutation = chanceOfMutation;
	}

	/**
	 * create a new candidate with random genes and default chance of mutation
	 */
	public Individual() {
		if (prob == null) {
			throw new java.lang.NullPointerException(
					"You must provide a reference to the problem to the candidate class");
		}
		genes = new boolean[prob.getNumChromosomes()];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = ThreadLocalRandom.current().nextBoolean();
		}
		chanceOfMutation = DEFAULT_CHANCE_OF_MUTATION;
	}

	/**
	 * create a new candidate with random genes and given chance of mutation
	 */
	public Individual(float chanceOfMutation) {
		if (prob == null) {
			throw new java.lang.NullPointerException(
					"You must provide a reference to the problem to the candidate class");
		}
		genes = new boolean[prob.getNumChromosomes()];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = ThreadLocalRandom.current().nextBoolean();
		}
		this.chanceOfMutation = chanceOfMutation;
	}

	/**
	 * will calculate the fitness of this individual and store it in the 
	 * fitness
	 * variable
	 */
	public void calcFitness() {
		fitness = prob.fitness(genes);
	}

	/**
	 * given this candidate and a partner, randomly cross the 2 gene vectors
	 * 
	 * @param other
	 * @return a single child candidate
	 */
	public Individual[] mate(Individual other) {
		if (other.genes.length != genes.length) { // should never happen
			return null;
		} else {
			// randomly cross the 2 vectors
			int split = ThreadLocalRandom.current().nextInt(1, genes.length - 1);
			System.out.println("Split: " + split);
			Individual[] ret = {
					// candidate with the beginning of this and the end of the other
					new Individual(cross(genes, other.genes, split), chanceOfMutation),
					// candidate with the beginning of other and the end of this
					new Individual(cross(other.genes, genes, split), chanceOfMutation) };
			return ret;
		}
	}

	private static boolean[] cross(boolean[] begin, boolean[] end, int crossPos) {
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

	public void mutate() {
		if (chanceOfMutation > 0) { // dont bother if mutation is disabled
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			for (int i = 0; i < genes.length; i++) { // for each gene
				if (rand.nextFloat() < chanceOfMutation) { // generate a random between 0 and 1 and flip if its less
					genes[i] = !genes[i];
				}
			}
		}
	}
	
	public String print() {
		String value = "[";
		for (int i  = 0; i < genes.length; i++) {
			if (genes[i] == true)
				value += 1 + ", ";
			else
				value += 0 + ", ";
		}
		value += "]";
		return value;
	}
	
}
