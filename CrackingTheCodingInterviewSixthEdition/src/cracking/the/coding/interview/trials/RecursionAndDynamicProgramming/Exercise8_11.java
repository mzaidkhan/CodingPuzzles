package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Exercise8_11 {

	enum Cents {
		Quarter(25), Dime(10), Nickel(5), Penny(1);

		public int value;

		private Cents(int value) {
			this.value = value;
		}

		public static int getDenominationValue(int index) {
			switch (index) {
			case 0:
				return Quarter.value;
			case 1:
				return Dime.value;
			case 2:
				return Nickel.value;
			case 3:
				return Penny.value;
			default:
				return 0;
			}
		}
	}

	static class Deno {
		int expectedAmt;
		int denominationIdx;

		public Deno(int expectedAmt, int denominationIdx) {
			this.expectedAmt = expectedAmt;
			this.denominationIdx = denominationIdx;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + denominationIdx;
			result = prime * result + expectedAmt;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Deno other = (Deno) obj;
			if (denominationIdx != other.denominationIdx)
				return false;
			if (expectedAmt != other.expectedAmt)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Deno [expectedAmt=" + expectedAmt + ", denominationIdx=" + denominationIdx + "]";
		}
	}

	public static int noOfPossibleWays(int expectedAmount, int denominationIdx, HashMap<Deno, Integer> memoization) {

		Deno key = new Exercise8_11.Deno(expectedAmount, denominationIdx);
		if (memoization.containsKey(key)) {
			return memoization.get(key);
		}
		// It is always possible to change any amount with unlimited Pennies
		if (denominationIdx >= Cents.values().length - 1) {
			return 1;
		}

		int denominationValue = Cents.getDenominationValue(denominationIdx);
		int noOfWays = 0;
		for (int noOfCoins = 0; noOfCoins * denominationValue <= expectedAmount; noOfCoins++) {
			noOfWays += noOfPossibleWays(expectedAmount - noOfCoins * denominationValue, denominationIdx + 1,
					memoization);
		}
		memoization.put(key, noOfWays);
		return noOfWays;
	}

	public static void main(String[] args) {
		// Only works with Java version 7 onwards. Using memoization to store
		// results to quickly evaluate redundant calls
		HashMap<Deno, Integer> memoization = new LinkedHashMap<>();
		int expectedCents = 50;
		System.out.println("No of possible ways = " + noOfPossibleWays(expectedCents, 0, memoization));

		System.out.println("Cached Map results");
		for (Deno deno : memoization.keySet()) {
			System.out.println("Expected Amount: " + deno.expectedAmt + " Denomination Value "
					+ Cents.getDenominationValue(deno.denominationIdx) + " -> No of Ways " + memoization.get(deno));
		}
	}

}
