package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.HashSet;

public class Exercise8_9 {

	public static boolean printEnabled = true;

	// First instinct brute force solution using HashSet to eliminate duplicates
	// (very slow :))
	public static void genParens(int remaining, int lenOfComb, String prefix, HashSet<String> hSet) {
		if (prefix.length() == lenOfComb) {
			if (hSet.add(prefix)) {
				if (!printEnabled) {
					System.out.println(prefix);
				}
			}
		} else {
			for (int i = 1; i <= remaining; i++) {
				genParens(remaining - i, lenOfComb, prefix + "()", hSet);
				genParens(remaining - i, lenOfComb, "(" + prefix + ")", hSet);
				genParens(remaining - i, lenOfComb, "()" + prefix, hSet);
			}
		}
	}

	// Optimized code:
	// For 1st Call: Only one possible unique combination possible that is ()
	// For 2nd Call: Only two possible unique combinations possible they are
	// ()() and (())
	// All other Calls: we can go ahead with the three regularly calls
	public static void genParens2(int remaining, int lenOfComb, String prefix, HashSet<String> hSet) {
		if (prefix.length() == lenOfComb) {
			if (hSet.add(prefix)) {
				if (!printEnabled) {
					System.out.println(prefix);
				}
			}
		} else {
			// First call
			if (remaining == lenOfComb / 2) {
				genParens(remaining - 1, lenOfComb, prefix + "()", hSet);
			}
			// Second call
			else if (remaining + 1 == lenOfComb / 2) {
				genParens(remaining - 1, lenOfComb, prefix + "()", hSet);
				genParens(remaining - 1, lenOfComb, "(" + prefix + ")", hSet);
			}
			// Subsequent calls
			else {
				genParens(remaining - 1, lenOfComb, prefix + "()", hSet);
				genParens(remaining - 1, lenOfComb, "(" + prefix + ")", hSet);
				genParens(remaining - 1, lenOfComb, "()" + prefix, hSet);
			}
		}
	}

	public static void main(String[] args) {
		HashSet<String> hSet = new HashSet<String>();
		int noOfParens = 10;
		genParens(noOfParens, noOfParens * 2, "", hSet);
		System.out.println("(Slow version) Total no of possible combinations " + hSet.size());
		HashSet<String> hSet2 = new HashSet<String>();
		genParens2(noOfParens, noOfParens * 2, "", hSet2);
		System.out.println("(A bit better version) Total no of possible combinations " + hSet.size());

		if (hSet.containsAll(hSet2)) {
			System.out.println("Test Passed: Same number of combinations in both solutions");
		} else {
			System.err.println("Test Failed: Different number of combinations in both solutions");

		}

	}

}
