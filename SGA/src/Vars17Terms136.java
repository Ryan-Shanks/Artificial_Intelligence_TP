import java.util.Arrays;

public class Vars17Terms136 implements Problem {
	@Override
	public double fitness(boolean[] candidate) {
		byte[] a = OFHelper.boolArrayToByteArray(candidate);
		return Math.abs(a[1] * a[2] + a[1] * a[3] + a[1] * a[4] + a[1] * a[5] + a[1] * a[6] + a[1] * a[7] + a[1] * a[8]
				+ a[1] * a[9] + a[1] * a[10] + a[2] * a[3] + a[2] * a[4] + a[2] * a[5] + a[2] * a[6] + a[2] * a[7]
				+ a[2] * a[8] + a[2] * a[9] + a[2] * a[10] + a[3] * a[4] + a[3] * a[5] + a[3] * a[6] + a[3] * a[7]
				+ a[3] * a[8] + a[3] * a[9] + a[3] * a[10] + a[4] * a[5] + a[4] * a[6] + a[4] * a[7] + a[4] * a[8]
				+ a[4] * a[9] + a[4] * a[10] + a[5] * a[6] + a[5] * a[7] + a[5] * a[8] + a[5] * a[9] + a[5] * a[10]
				+ a[6] * a[7] + a[6] * a[8] + a[6] * a[9] + a[6] * a[10] + a[7] * a[8] + a[7] * a[9] + a[7] * a[10]
				+ a[8] * a[9] + a[8] * a[10] + a[9] * a[10] + a[1] * a[11] + a[2] * a[11] + a[3] * a[11] + a[4] * a[11]
				+ a[5] * a[11] + a[6] * a[11] + a[7] * a[11] + a[8] * a[11] + a[9] * a[11] + a[10] * a[11]
				+ a[1] * a[12] + a[2] * a[12] + a[3] * a[12] + a[4] * a[12] + a[5] * a[12] + a[6] * a[12] + a[7] * a[12]
				+ a[8] * a[12] + a[9] * a[12] + a[10] * a[12] + a[11] * a[12] + a[1] * a[13] + a[2] * a[13]
				+ a[3] * a[13] + a[4] * a[13] + a[5] * a[13] + a[6] * a[13] + a[7] * a[13] + a[8] * a[13] + a[9] * a[13]
				+ a[10] * a[13] + a[11] * a[13] + a[12] * a[13] + a[1] * a[14] + a[2] * a[14] + a[3] * a[14]
				+ a[4] * a[14] + a[5] * a[14] + a[6] * a[14] + a[7] * a[14] + a[8] * a[14] + a[9] * a[14]
				+ a[10] * a[14] + a[11] * a[14] + a[12] * a[14] + a[13] * a[14] + a[1] * a[15] + a[2] * a[15]
				+ a[3] * a[15] + a[4] * a[15] + a[5] * a[15] + a[6] * a[15] + a[7] * a[15] + a[8] * a[15] + a[9] * a[15]
				+ a[10] * a[15] + a[11] * a[15] + a[12] * a[15] + a[13] * a[15] + a[14] * a[15] + a[1] * a[16]
				+ a[2] * a[16] + a[3] * a[16] + a[4] * a[16] + a[5] * a[16] + a[6] * a[16] + a[7] * a[16] + a[8] * a[16]
				+ a[9] * a[16] + a[10] * a[16] + a[11] * a[16] + a[12] * a[16] + a[13] * a[16] + a[14] * a[16]
				+ a[15] * a[16] + a[1] * a[17] + a[2] * a[17] + a[3] * a[17] + a[4] * a[17] + a[5] * a[17]
				+ a[6] * a[17] + a[7] * a[17] + a[8] * a[17] + a[9] * a[17] + a[10] * a[17] + a[11] * a[17]
				+ a[12] * a[17] + a[13] * a[17] + a[14] * a[17] + a[15] * a[17] + a[16] * a[17]);
	}

	@Override
	public int getNumChromosomes() {
		return 17;
	}

	@Override
	public int getMaxOrMin() {
		return 1;
	}
	@Override
	public String genesToString(boolean[] genes) {
		return Arrays.toString(genes).replace("true", "1").replace("false", "-1");
	}
}
