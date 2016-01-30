package cracking.the.coding.interview.trials.Misc;

import java.util.Arrays;

import cracking.the.coding.interview.trials.Helper.Helper;

public class KnapSack01 {

	static int[] values = Helper.randomArray(10, 1, 1000);
	static int[] weights = Helper.randomArray(10, 1, 1000);

	public static int knapSack(int expectedWeight) {
		return knapSack(expectedWeight, 0);
	}

	// Time complexity O(2^N)
	private static int knapSack(int expectedWeight, int revIndex) {

		if (revIndex >= weights.length || expectedWeight <= 0) {
			return 0;
		}

		// check if the weight of current item is more than expectedWeight
		if (weights[revIndex] > expectedWeight) {
			// Don't consider this item in the knapsack
			return knapSack(expectedWeight, revIndex + 1);
		} else {
			// Take either the max of either considering the item or leaving it
			// behind
			return Math.max(values[revIndex] + knapSack(expectedWeight - weights[revIndex], revIndex + 1),
					knapSack(expectedWeight, revIndex + 1));
		}
	}

	// With dynamic programming. Time complexity O(No. of items * Expected
	// Weight)
	public static int knapSackWithDP(int expectedWeight) {
		int N = values.length;
		int[][] dP = new int[N + 1][expectedWeight + 1];
		boolean[][] takeSoln = new boolean[N + 1][expectedWeight + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= expectedWeight; j++) {
				if (weights[i - 1] > j) {
					dP[i][j] = dP[i - 1][j];
				} else {
					int option1 = dP[i - 1][j], option2 = values[i - 1] + dP[i - 1][j - weights[i - 1]];
					dP[i][j] = Math.max(option2, option1);
					takeSoln[i][j] = (option2 > option1);
				}
			}
		}

		System.out.println("Selected items are: ");
		int totalWt = 0, totalValue = 0;
		for (int k = N, wt = expectedWeight; k > 0; k--) {
			if (takeSoln[k][wt]) {
				totalWt += weights[k - 1];
				totalValue += values[k - 1];
				System.out.printf("	Item %d with weight %d and value %d\n", k, weights[k - 1], values[k - 1]);
				wt -= weights[k - 1];
			}
		}
		System.out.printf("Calculated total weight= %d and total value= %d\n", totalWt, totalValue);
		return dP[N][expectedWeight];
	}

	public static void main(String[] args) {
		int expectedWeights = 400;
		System.out.println("Values are " + Arrays.toString(values));
		System.out.println("Weights are " + Arrays.toString(weights));
		System.out.println("Total knapsack capacity " + expectedWeights);
		
		System.out.println("\n[Recursive Solution] Optimal value: " + knapSack(expectedWeights));
		System.out.println("\n[Dynamic Programming Solution]");
		System.out.println("Optimal value: " + knapSackWithDP(expectedWeights));
	}
}