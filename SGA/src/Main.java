public class Main {
	private final static int NUM_GENERATIONS = 10000;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Problem p = (Problem) Class.forName(args[0]).newInstance();
		SGA s = new SGA(p, 10);
		s.run(NUM_GENERATIONS);
	}
}
