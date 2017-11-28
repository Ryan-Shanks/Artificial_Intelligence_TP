
public class SGA {
	private Problem prob;
	Individual[] pop;
	private int popSize;
	public SGA(Problem p, int popSize) {
		prob = p;
		this.popSize = popSize;
		pop = new Individual[popSize];
		
	}
	private void testPopFitness() {
		for (Individual p : pop) {
			p.calcFitness();
		}
	}
	private void biasedRoulette() {
		testPopFitness();
		//TODO complete the biased roulette.s
	}
}
