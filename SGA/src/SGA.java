import java.util.concurrent.ThreadLocalRandom;

public class SGA {
	Individual[] pop;

	public SGA(Problem p, int popSize) {
		System.out.println("========================= Initial Set ==========================");
		pop = new Individual[popSize];
		Individual.prob = p;
		for (int i = 0; i < pop.length; i++) {
			pop[i] = new Individual();
			System.out.println(i + ": " +  pop[i].print());
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
		System.out.println("Total: " + total);
		double[] percentage = new double[pop.length];

		String p = "[";
		// percentage of each total
		for (int i = 0; i < pop.length; i++) {
			if (i == 0) {
				percentage[i] = (pop[i].fitness / total);
			} else if (i < pop.length - 1) { // if it is not the last one
				percentage[i] = percentage[i - 1] + (pop[i].fitness / total);
			} else {
				percentage[i] = 1.0; // make sure it is 1, there may otherwise be some error due to FP rounding
			}
			p += percentage[i]*100 +"%" + ", ";
			System.out.println("Fitness of " + i + ": " + pop[i].fitness);
		}
		p+= "]";
		System.out.println("Percentage Array: " + p);
		
		
		// Linear Search to be replace later with binary search
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		double randValue;
		Individual[] matingPool = new Individual[pop.length];

		for (int i = 0; i < matingPool.length; i++) { // for each space in the mating pool
			randValue = rand.nextDouble();
			// linear search
			for(int j =0; j < matingPool.length; j++){
				if (randValue < percentage[j]) {
					matingPool[i] = pop[j]; // This does not appear to be working for some reason, still all null
					System.out.println("Random Chance: " + randValue);
					System.out.println("Mating Pool " + i + ": " + matingPool[i].print());
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
			
			System.out.println("Mate " + i + "&" + (pool.length - i - 1) + ": " + pop[i].print());
			System.out.println("Mate " + (pool.length - i - 1) + "&" + i + ": " + pop[(pool.length - i - 1)].print());
		}
	}

	/**
	 * Calls the mutate method of each individual in the population in order to
	 * increase genetic diversity and potentially find a better solution.
	 */
	private void mutateAll() {
		for (Individual i : pop) {
			i.mutate();
			System.out.println(i.print());
		}
	}

	/**
	 * run the algorithm. Stopping condition: generations.
	 *
	 *  @param generations number of iterations
	 */
	public void run(int generations) {
		// TODO, obviously need to change to count generations or go until no more
		// improvements are found
		
		int count = 0;
		while (count < generations) {
			System.out.println("--------------------------------------   "+ count + "   ----------------------------------------------");
			Individual[] matingPool = getMatingPool(); // do the biased roulette and get the results
			System.out.println("Generation: " + count + " Max fitness: " + getMaxFitness());
			mate(matingPool); // mate members of the pool to produce a new pop, overwriting the old one
			mutateAll(); // apply mutations
			count ++;
		}
	}	
	
	private double getMaxFitness() {
		double max = 0;
		for (Individual i : pop) {
			if (i.fitness > max) {
				max = i.fitness;
			}
		}
		return max;
	}
}
