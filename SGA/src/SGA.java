//import java.util.Random;
//
//public class SGA {
//	private Problem prob;
//	Individual[] pop;
//	private int popSize;
//
//	public SGA(Problem p, int popSize) {
//		prob = p;
//		this.popSize = popSize;
//		pop = new Individual[popSize];
//
//	}
//
//	private void testPopFitness() {
//		for (Individual p : pop) {
//			p.calcFitness();
//		}
//	}
//
//	private void biasedRoulette() {
//		//testPopFitness();
//		int total = 0;
//		double[] percentage = new double[pop.length];
//		Random rand = new Random();
//		double randValue;
//
//		//finds total
//		for (int i = 0; i < pop.length; i++)
//			total += pop[i].calcFitness();
//
//		//percentage of each total
//		for (int i = 0; i < pop.length; i++) {
//			if (i == 0)
//				percentage[i] = (pop[i].calcFitness() / total);
//			else
//				percentage[i] = percentage[i-1] + (pop[i].calcFitness() / total);
//		}
//
//		//Linear Search to be replace later with binary search
//		boolean found = false;
//		int j;
//		Individual[] newPop = new Individual[pop.length];
//		for (int i = 0; i < pop.length; i++) {
//			randValue = rand.nextDouble();
//			found = false;
//			j = 0;
//			while (found = false) {
//				if(randValue < percentage[j])
//					newPop[i] = pop[j];
//				else
//					found = true;
//				j++;
//
//			}
//
//		}
//		pop = newPop;
//	}
//
//	public void MatingPool() {
//
//	}
//
//}
