import java.util.Random;

public class Main {

	final static int ELITISM_K = 5;
	final static int POP_SIZE = 200 + ELITISM_K;  // population size
	final static int MAX_ITER = 2000;             // max number of iterations
	final static double MUTATION_RATE = 0.05;     // probability of mutation
	final static double CROSSOVER_RATE = 0.7;     // probability of crossover

	private static Random m_rand = new Random();  // random-number generator
	private Individual[] m_population;
	private double totalFitness;


	public static void main(String[] args) {
		Population pop = new Population();
		Individual[] newPop = new Individual[POP_SIZE];
		Individual[] indiv = new Individual[2];

		// current population
		System.out.print("Total Fitness = " + pop.totalFitness);
		System.out.println(" ; Best Fitness = " +
				pop.findBestIndividual().getFitnessValue());

		// main loop
		int count;
		for (int iter = 0; iter < MAX_ITER; iter++) {
			count = 0;

			// Elitism
			for (int i=0; i<ELITISM_K; ++i) {
				newPop[count] = pop.findBestIndividual();
				count++;
			}

			// build new Population
			while (count < POP_SIZE) {
				// Selection
				indiv[0] = pop.rouletteWheelSelection();
				indiv[1] = pop.rouletteWheelSelection();

				// Crossover
				if ( m_rand.nextDouble() < CROSSOVER_RATE ) {
					indiv = pop.crossover(indiv[0], indiv[1]);
				}

				// Mutation
				if ( m_rand.nextDouble() < MUTATION_RATE ) {
					indiv[0].mutate();
				}
				if ( m_rand.nextDouble() < MUTATION_RATE ) {
					indiv[1].mutate();
				}

				// add to new population
				newPop[count] = indiv[0];
				newPop[count+1] = indiv[1];
				count += 2;
			}
			pop.setPopulation(newPop);

			// reevaluate current population
			pop.evaluate();
			System.out.print("Total Fitness = " + pop.totalFitness);
			System.out.println(" ; Best Fitness = " +
					pop.findBestIndividual().getFitnessValue());
		}

		// best indiv
		Individual bestIndiv = pop.findBestIndividual();
	}
}
