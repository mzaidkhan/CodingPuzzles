package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.HashSet;

public class Exercise8_9 {

	public static boolean printEnabled = true;

	public static int dCount1;
	public static int dCount2;

	// First instinct brute force solution using HashSet to eliminate duplicates
	// (very slow :))
	public static void genParens(int remaining, int lenOfComb, String prefix, HashSet<String> hSet) {
		if (prefix.length() == lenOfComb) {
			if (hSet.add(prefix)) {
				if (!printEnabled) {
					System.out.println(prefix);
				}
			} else {
				dCount1++;
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
			} else {
				dCount2++;
			}
		} else {
			// First call
			if (remaining == lenOfComb / 2) {
				genParens2(remaining - 1, lenOfComb, prefix + "()", hSet);
			}
			// Second call
			else if (remaining + 1 == lenOfComb / 2) {
				genParens2(remaining - 1, lenOfComb, prefix + "()", hSet);
				genParens2(remaining - 1, lenOfComb, "(" + prefix + ")", hSet);
			}
			// Subsequent calls
			else {
				genParens2(remaining - 1, lenOfComb, prefix + "()", hSet);
				genParens2(remaining - 1, lenOfComb, "(" + prefix + ")", hSet);
				genParens2(remaining - 1, lenOfComb, "()" + prefix, hSet);
			}
		}
	}

	public static void main(String[] args) {
		HashSet<String> hSet = new HashSet<String>();
		int noOfParens = 14;

		double start = System.currentTimeMillis();
		genParens(noOfParens, noOfParens * 2, "", hSet);
		double end = System.currentTimeMillis();

		System.out.println("(Slow version) Total no of possible combinations " + hSet.size() + " it took "
				+ (end - start) / 100 + " seconds");
		System.out.println("(Slow version) Total no of duplicate combinations " + dCount1);

		HashSet<String> hSet2 = new HashSet<String>();
		start = System.currentTimeMillis();
		genParens2(noOfParens, noOfParens * 2, "", hSet2);
		end = System.currentTimeMillis();

		System.out.println("(A bit better version) Total no of possible combinations " + hSet2.size() + " it took "
				+ (end - start) / 100 + " seconds");
		System.out.println("(A bit better version) Total no of duplicate combinations " + dCount2);

		if (hSet.containsAll(hSet2)) {
			System.out.println("Test Passed: Same number of combinations in all solutions");
		} else {
			System.err.println("Test Failed: Different number of combinations in all solutions");
		}
	}

}