
public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Problem p = (Problem) Class.forName(args[0]).newInstance();
		SGA s = new SGA(p, 100);
	}
}
