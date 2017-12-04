public class Vars10Terms45 implements Problem {

	@Override
	public double fitness(boolean[] candidate) {
		byte[] a = OFHelper.boolArrayToByteArray(candidate);
		return Math.abs(a[1] * a[2] + a[1] * a[3] + a[1] * a[4] + a[1] * a[5] + a[1] * a[6] + a[1] * a[7] + a[1] * a[8]
				+ a[1] * a[9] + a[1] * a[10] + a[2] * a[3] + a[2] * a[4] + a[2] * a[5] + a[2] * a[6] + a[2] * a[7]
				+ a[2] * a[8] + a[2] * a[9] + a[2] * a[10] + a[3] * a[4] + a[3] * a[5] + a[3] * a[6] + a[3] * a[7]
				+ a[3] * a[8] + a[3] * a[9] + a[3] * a[10] + a[4] * a[5] + a[4] * a[6] + a[4] * a[7] + a[4] * a[8]
				+ a[4] * a[9] + a[4] * a[10] + a[5] * a[6] + a[5] * a[7] + a[5] * a[8] + a[5] * a[9] + a[5] * a[10]
				+ a[6] * a[7] + a[6] * a[8] + a[6] * a[9] + a[6] * a[10] + a[7] * a[8] + a[7] * a[9] + a[7] * a[10]
				+ a[8] * a[9] + a[8] * a[10] + a[9] * a[10]);
	}

	@Override
	public int getNumChromosomes() {
		return 10;
	}

	@Override
	public int getMaxOrMin() {
		return -1;
	}
}
