package cracking.the.coding.interview.trials.MathAndLogicPuzzles;

import java.util.Arrays;

public class SieveOfErathothenes {

	// every non prime number is divisible by a prime number
	public static void generatePrimes(int max) {
		boolean[] flags = new boolean[max + 1];

		// initializing all the element of the boolean array to true
		Arrays.fill(flags, true);

		int prime = 2;
		while (prime <= Math.sqrt(max)) {
			setNonPrimes(flags, prime);
			prime = getNextPrime(flags, prime);
		}

		System.out.println("The prime numbers upto " + max + " are:");
		for (int i = 0; i < flags.length; i++) {
			if (flags[i]) {
				System.out.print(i + " ");
			}
		}
	}

	public static void setNonPrimes(boolean[] flags, int prime) {
		for (int i = prime * prime; i < flags.length; i += prime) {
			flags[i] = false;
		}
	}

	public static int getNextPrime(boolean[] flags, int prime) {
		int next = prime + 1;

		while (next < flags.length && !flags[next]) {
			next++;
		}
		return next;
	}

	public static void main(String[] args) {
		generatePrimes(1000);
	}

}
