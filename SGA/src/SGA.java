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

	private double testPopFitness() {
		double total = 0;
		for (Individual p : pop) {
			p.calcFitness();
			total += p.fitness;
		}
		return total;
	}

	private Individual[] getMatingPool() {
		double total = testPopFitness();
		double[] percentage = new double[pop.length];

		// percentage of each total
		for (int i = 0; i < pop.length; i++) {
			if (i == 0) {
				percentage[i] = (pop[i].fitness / total);
			} else if (i < pop.length - 1) { // if it is not the last one
				percentage[i] = percentage[i - 1] + (pop[i].fitness / total);
			} else {
				percentage[i] = 1.0; // make sure it is 1, there may otherwise be some error due to FP rounding
			}
		}

		// Linear Search to be replace later with binary search
		boolean found = false;
		int j;
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		double randValue;
		Individual[] matingPool = new Individual[pop.length];

		for (int i = 0; i < matingPool.length; i++) { // for each space in the mating pool
			randValue = rand.nextDouble();
			found = false;
			// linear search
			j = 0;
			while (!found) {
				if (randValue < percentage[j]) {
					matingPool[i] = pop[j]; // This does not appear to be working for some reason, still all null
				}else {
					found = true;
				}
				j++;
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
	 * run the algorithm. Stopping condition TBD.
	 */
	public void run() {
		// TODO, obviously need to change to count generations or go until no more
		// improvements are found
		while (true) {
			Individual[] matingPool = getMatingPool(); // do the biased roulette and get the results
			mate(matingPool); // mate members of the pool to produce a new pop, overwriting the old one
			mutateAll(); // apply mutations
		}
	}
}
