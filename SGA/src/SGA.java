import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class SGA {
	Individual[] pop;

	public SGA(Problem p, int popSize) {
		pop = new Individual[popSize];
		Individual.prob = p;
		for (int i = 0; i < pop.length; i++) {
			pop[i] = new Individual();
		}
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
	private double getFitnessOfLeastFit() {
		double min = Double.MAX_VALUE;
		for (Individual i : pop) {
			if (i.fitness < min) {
				min = i.fitness;
			}
		}
		return min;
	}

	private Individual[] getMatingPool() {
		double total = testPopFitness();
		double[] percentage = new double[pop.length];
		double minFitness = getFitnessOfLeastFit();
		total -= ((minFitness - 1) * pop.length);
		// percentage of each total
		for (int i = 0; i < pop.length; i++) {
			if (i == 0) {
				percentage[i] = ((pop[i].fitness - minFitness + 1) / total);
			} else if (i < pop.length - 1) { // if it is not the last one
				percentage[i] = percentage[i - 1] + ((pop[i].fitness - minFitness + 1) / total);
			} else {
				percentage[i] = 1.0; // make sure it is 1, there may otherwise be some error due to FP rounding
			}
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
		int count = 0, generationFirstEncounteredMaxFitness = -1;
		double maxFitnessSoFar = Double.MIN_VALUE;
		HashSet<Individual> individualsWithMaxFitness = new HashSet<Individual>();

		while (count - generationFirstEncounteredMaxFitness < generations
				|| generationFirstEncounteredMaxFitness == -1) {
			Individual[] matingPool = getMatingPool(); // do the biased roulette and get the results
			double fittest = getFitnessOfMostFit();

			// if we have found an individual that is better than any we have ever found
			// before
			if (fittest > maxFitnessSoFar) {
				maxFitnessSoFar = fittest;
				generationFirstEncounteredMaxFitness = count;
				individualsWithMaxFitness.clear();
				for (Individual i : pop) { // there could be multiple solutions, add them all to the set
					if (i.fitness == fittest) {
						individualsWithMaxFitness.add(i);
					}
				}
			} else if (fittest == maxFitnessSoFar) { // perhaps we have found a new solution, try and add it to the set
				for (Individual i : pop) { // there could be multiple solutions, add them all to the set
					if (i.fitness == fittest) {
						individualsWithMaxFitness.add(i);
					}
				}
			} // else this generation does not contain the best we have seen, keep going

			System.out.println("Generation: " + count + " Max fitness: " + getFitnessOfMostFit() + " Unique pop: "
					+ getNumUnique());
			mate(matingPool); // mate members of the pool to produce a new pop, overwriting the old one
			mutateAll(); // apply mutations
			count++;
		}
		System.out.println("----------------------DONE----------------------");
		Iterator<Individual> i = individualsWithMaxFitness.iterator();
		for (int solCount = 1; i.hasNext(); solCount++) {
			Individual solution = i.next();
			System.out.println("Solution " + solCount + ": " + solution.toString());
		}
	}

	// debugging, but might be useful in checking the answer as well
	private double getFitnessOfMostFit() {
		double max = 0;
		for (Individual i : pop) {
			if (i.fitness > max) {
				max = i.fitness;
			}
		}
		return max;
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
