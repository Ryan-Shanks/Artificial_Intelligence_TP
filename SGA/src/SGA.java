import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class SGA {
	Individual[] pop;
	Problem prob;

	public SGA(Problem p, int popSize) {
		pop = new Individual[popSize];
		Individual.prob = p;
		for (int i = 0; i < pop.length; i++) {
			pop[i] = new Individual();
		}
		prob = p;
	}

	/**
	 * will return the sum of all the fitnesses in the population
	 */
	private double testPopFitness() {
		double total = 0;
		for (Individual p : pop) {
			p.calcFitness();
			total += p.fitness;
		}
		return total;
	}

	/**
	 * return the fitness of the least fit individual in the population
	 */
	private double getMinFitness() {
		double min = Double.MAX_VALUE;
		for (Individual i : pop) {
			if (i.fitness < min) {
				min = i.fitness;
			}
		}
		return min;
	}

	private double[] generatePercentagesForRouletteMAX() {
		double[] p = new double[pop.length];
		double total = testPopFitness();
		double minFitness = getMinFitness();
		total -= ((minFitness - 1) * pop.length);
		// percentage of each total
		for (int i = 0; i < pop.length; i++) {
			if (i == 0) {
				p[i] = ((pop[i].fitness - minFitness + 1) / total);
			} else if (i < pop.length - 1) { // if it is not the last one
				p[i] = p[i - 1] + ((pop[i].fitness - minFitness + 1) / total);
			} else {
				p[i] = 1.0; // make sure it is 1, there may otherwise be some error due to FP rounding
			}
		}
		return p;
	}

	private double getMaxFitness() {
		double max = -Double.MAX_VALUE;
		for (Individual i : pop) {
			if (i.fitness > max) {
				max = i.fitness;
			}
		}
		return max;
	}

	private double[] generatePercentagesForRouletteMIN() {
		double[] p = new double[pop.length];
		testPopFitness();
		double maxFitness = getMaxFitness();
		double[] fitnesses = new double[pop.length];
		double total = 0;
		for (int i = 0; i < pop.length; i++) {
			fitnesses[i] = -1 * (pop[i].fitness - maxFitness - 1);
			total += fitnesses[i];
		}

		for (int i = 0; i < pop.length; i++) {
			if (i == 0) {
				p[i] = fitnesses[i] / total;
			} else if (i < pop.length - 1) { // if it is not the last one
				p[i] = p[i - 1] +fitnesses[i] / total;
			} else {
				p[i] = 1.0; // make sure it is 1, there may otherwise be some error due to FP rounding
			}
		}
		return p;
	}

	private Individual[] getMatingPool() {

		double[] percentage;
		if (prob.getMaxOrMin() == 1) {
			percentage = generatePercentagesForRouletteMAX();
		} else { // min
			percentage = generatePercentagesForRouletteMIN();
		}

		// Linear Search to be replace later with binary search
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		double randValue;
		Individual[] matingPool = new Individual[pop.length];

		for (int i = 0; i < matingPool.length; i++) { // for each space in the mating pool
			randValue = rand.nextDouble();
			// linear search
			for (int j = 0; j < matingPool.length; j++) {
				if (randValue < percentage[j]) {
					matingPool[i] = pop[j]; // This does not appear to be working for some reason, still all null
					break;
				}
			}
		}
		return matingPool;
	}

	/**
	 * will pair up all members in the mating pool around the center of the array
	 * and mate to form new pop. Does not check to make sure individuals are mating
	 * with identical individuals, in which case their children would be the same.
	 * Perhaps it should, idk
	 * 
	 * @param pool
	 *            the mating pool
	 */
	private void mate(Individual[] pool) {
		Individual[] result = new Individual[2];
		for (int i = 0; i < pool.length / 2; i++) {
			result = pool[i].mate(pool[pool.length - i - 1]);
			pop[i] = result[0];
			pop[pool.length - i - 1] = result[1];
		}
	}

	/**
	 * Calls the mutate method of each individual in the population in order to
	 * increase genetic diversity and potentially find a better solution.
	 */
	private void mutateAll() {
		for (Individual i : pop) {
			i.mutate();
		}
	}

	/**
	 * run the algorithm. Stopping condition: generations.
	 *
	 * @param generations
	 *            number of iterations
	 */
	public void run(int generations) {
		int count = 0, generationFirstEncounteredBestFitness = -1;
		double maxFitnessSoFar = prob.getMaxOrMin() * -Double.MAX_VALUE;
		HashSet<Individual> fittestIndividuals = new HashSet<Individual>();

		while (count - generationFirstEncounteredBestFitness < generations) {
			Individual[] matingPool = getMatingPool(); // do the biased roulette and get the results
			double fittest = prob.getMaxOrMin() == 1 ? getMaxFitness() : getMinFitness();

			// if we have found an individual that is better than any we have ever found
			// before
			if ((prob.getMaxOrMin() == 1 && fittest > maxFitnessSoFar)
					|| (prob.getMaxOrMin() == -1 && fittest < maxFitnessSoFar)) {
				maxFitnessSoFar = fittest;
				generationFirstEncounteredBestFitness = count;
				fittestIndividuals.clear();
				for (Individual i : pop) { // there could be multiple solutions, add them all to the set
					if (i.fitness == fittest) {
						fittestIndividuals.add(i);
					}
				}
			} else if (fittest == maxFitnessSoFar) { // perhaps we have found a new solution, try and add it to the set
				for (Individual i : pop) { // there could be multiple solutions, add them all to the set
					if (i.fitness == fittest) {
						fittestIndividuals.add(i);
					}
				}
			} // else this generation does not contain the best we have seen, keep going

			System.out.println("Generation: " + count + " Best fitness: "
					+ (prob.getMaxOrMin() == 1 ? getMaxFitness() : getMinFitness()) + " Unique pop: " + getNumUnique());
			mate(matingPool); // mate members of the pool to produce a new pop, overwriting the old one
			mutateAll(); // apply mutations
			count++;
		}
		System.out.println("----------------------DONE----------------------");
		Iterator<Individual> i = fittestIndividuals.iterator();
		for (int solCount = 1; i.hasNext(); solCount++) {
			Individual solution = i.next();
			System.out.println("Solution " + solCount + ": " + solution.toString());
		}
	}

	// debugging
	private int getNumUnique() {
		HashSet<Individual> set = new HashSet<Individual>();
		for (Individual i : pop) {
			set.add(i);
		}
		return set.size();
	}
}