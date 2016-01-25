package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.HashSet;

public class Exercise8_9 {

	// First instinct brute force solution using HashSet to eliminate duplicates
	// (very slow :))
	public static void genParens(int remaining, int lenOfComb, String prefix, HashSet<String> hSet) {
		if (prefix.length() == lenOfComb) {
			if (hSet.add(prefix)) {
				System.out.println(prefix);
			}
		} else {
			for (int i = 1; i <= remaining; i++) {
				genParens(remaining - i, lenOfComb, prefix + "()", hSet);
				genParens(remaining - i, lenOfComb, "(" + prefix + ")", hSet);
				genParens(remaining - i, lenOfComb, "()" + prefix, hSet);
			}
		}
	}

	public static void main(String[] args) {
		HashSet<String> hSet = new HashSet<String>();
		int noOfParens = 3;
		genParens(noOfParens, noOfParens * 2, "", hSet);
	}

}
