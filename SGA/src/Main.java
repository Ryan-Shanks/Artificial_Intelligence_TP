public class Main {
	private final static int NUM_GENERATIONS = 1000000; // if we have not found a better candidate in this many generations, done
	public static void main(String[] args) throws Exception {
		Problem p = (Problem) Class.forName(args[0]).newInstance();
		SGA s = new SGA(p, 1000);
		s.run(NUM_GENERATIONS, false);
	}
}
