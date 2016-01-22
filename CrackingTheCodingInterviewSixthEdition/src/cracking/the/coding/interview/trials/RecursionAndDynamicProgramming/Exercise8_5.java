package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

public class Exercise8_5 {

	// Brute force solution.
	// Time complexity O(B), where B is the value of value
	public static int recMultiply(int a, int b) {
		if (b == 0) {
			return 0;
		} else if (b < 0 && a < 0) {
			a = -a;
			b = -b;
		} else if (b < 0) {
			int c = a;
			a = b;
			b = c;
		}
		return recMulti(a, b);
	}

	private static int recMulti(int a, int b) {
		if (b < 0) {
			return 0;
		} else if (b == 0) {
			return a;
		}
		return a + recMultiply(a, --b);
	}

	public static void main(String[] args) {
		int a = 12, b = -3;
		System.out.println("Brute force solution " + a + " x " + b + " = " + recMultiply(a, b));
	}

}
