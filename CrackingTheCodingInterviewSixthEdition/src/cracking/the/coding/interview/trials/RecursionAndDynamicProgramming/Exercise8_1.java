package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.Arrays;

public class Exercise8_1 {

	// Run time and space complexity O(3^N)
	public static int noOfPossibleWays(int n) {
		// Base cases
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		}
		// There are the different possibilities of child either taking 1, 2 or
		// 3 steps
		return noOfPossibleWays(n - 1) + noOfPossibleWays(n - 2) + noOfPossibleWays(n - 3);
	}

	// Using Memoization optimization
	public static int noOfPossibleWaysOptimized(int n) {
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		return countPossibleWays(n, memo);
	}

	private static int countPossibleWays(int n, int[] memo) {
		// Base cases
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (memo[n] > -1) {
			return memo[n];
		} else {
			memo[n] = countPossibleWays(n - 1, memo) + countPossibleWays(n - 2, memo) + countPossibleWays(n - 3, memo);
			return memo[n];
		}
	}

	public static void main(String[] args) {
		int n = 50;
		System.out.println(
				"(Brute force) The no of possible way to the child can run up " + n + " = " + noOfPossibleWays(n));
		System.out.println("(Memoization) The no of possible way to the child can run up " + n + " = "
				+ noOfPossibleWaysOptimized(n));

	}

}
