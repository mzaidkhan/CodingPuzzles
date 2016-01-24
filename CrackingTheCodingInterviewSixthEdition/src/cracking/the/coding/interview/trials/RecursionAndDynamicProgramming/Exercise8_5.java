package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

public class Exercise8_5 {

	// Brute force solution.
	// Time complexity O(B), where B is the value of value
	public static int recMultiply(int a, int b) {
		if (b == 0 || a == 0) {
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
		}
		return a + recMultiply(a, --b);
	}

	// Divide and conquer method. Time complexity 0(Log of smaller number)
	public static int multiply(int a, int b) {
		int captureSign = 0;
		if (b == 0 || a == 0) {
			return 0;
		} else if (b < 0 && a < 0) {
			a = -a;
			b = -b;
		} else if (b < a) {
			int c = a;
			a = b;
			b = c;
		}

		if (a < 0) {
			captureSign = a;
			a = -a;
		}

		int result = multiplyRec(a, b);
		if (captureSign < 0) {
			result = -result;
		}
		return result;
	}

	private static int multiplyRec(int a, int b) {
		if (a == 1) {
			return b;
		}
		int half = a >> 1;

		int res = multiplyRec(half, b);

		if (a % 2 == 0) {
			return res + res;
		} else {
			return res + res + b;
		}
	}

	public static void main(String[] args) {
		int a = 12, b = -11;
		System.out.println("Brute force solution " + a + " x " + b + " = " + recMultiply(a, b));
		System.out.println("Divide and conquer solution " + a + " x " + b + " = " + multiply(a, b));

	}

}
