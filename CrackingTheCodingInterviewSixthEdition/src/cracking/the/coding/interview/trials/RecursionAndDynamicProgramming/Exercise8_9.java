package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

public class Exercise8_9 {

	public static boolean printEnabled = true;

	public static int dCount;

	public static void genParens(int noOfParens) {
		if (noOfParens >= 0) {
			genParens(0, noOfParens, noOfParens, new char[noOfParens * 2]);

		}
	}

	// The most optimal solution, there are NO duplicate combinations generated.
	// The first opening and last closing parens are always in their fixed
	// positions. We just need to rearrange the valid combinations of other
	// parens. Time complexity is 0(2^N)
	public static void genParens(int count, int leftParens, int rightParens, char[] cStr) {
		if (rightParens == 0) {
			dCount++;
			System.out.println(String.copyValueOf(cStr));
		} else {
			if (leftParens > 0) {
				cStr[count] = '(';
				genParens(count + 1, leftParens - 1, rightParens, cStr);
			}

			if (rightParens > leftParens) {
				cStr[count] = ')';
				genParens(count + 1, leftParens, rightParens - 1, cStr);
			}
		}
	}

	public static void main(String[] args) {
		int noOfParens = 4;
		double start = System.currentTimeMillis();
		genParens(noOfParens);
		double end = System.currentTimeMillis();

		System.out.println(
				"Total no of possible combinations " + dCount + " it took " + (end - start) / 100 + " seconds");

	}

}