import problem.Problem;

public class Main {
	private final static int NUM_GENERATIONS = 1000000; // if we have not found a better candidate in this many generations, done
	public static void main(String[] args) throws Exception {
		Problem p = (Problem) Class.forName(args[0]).getConstructor().newInstance();
		SGA s = new SGA(p, 100);
		s.run(NUM_GENERATIONS, false);
	}
}
