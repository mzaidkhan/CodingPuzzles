package cracking.the.coding.interview.trials.BitManipulation;

/**
 * This this a brute force solution that takes O(N^2), where N is the number of
 * bits used to represent the integer value
 */
class FlipBit {

	public static int NO_OF_BITS_TO_REPRESENT_INT = 32;

	public static boolean isTheBitValueOne(int number, int bit) {
		return ((number & (1 << bit)) != 0);
	}

	public static int longestSequencesOfOnes(int number) {
		int longestSeq = 0;
		for (int i = 0; i < NO_OF_BITS_TO_REPRESENT_INT; i++) {
			longestSeq = Math.max(longestSeq, longestSequencesOfOnes(number, i));
		}
		return longestSeq;
	}

	private static int longestSequencesOfOnes(int number, int flippedBit) {
		int counter = 0, max = 0;
		for (int i = 0; i < NO_OF_BITS_TO_REPRESENT_INT; i++) {
			if (i == flippedBit || isTheBitValueOne(number, i)) {
				counter++;
				max = Math.max(counter, max);
			} else {
				counter = 0;
			}
		}
		return max;
	}

}

class OptimizedFlipBit {

	public static int NO_OF_BITS_TO_REPRESENT_INT = 32;

	public static int longestSequencesOfOnes(int number) {
		// if number is -1, then the all the bits are 1
		if (~number == 0) {
			return NO_OF_BITS_TO_REPRESENT_INT;
		}

		int prevLen = 0, currLen = 0;
		// Longest sequence length will at least be 1
		int maxLen = 1;

		while (number != 0) {
			// if bit is 1, increment the counter
			if ((number & 1) == 1) {
				currLen++;
			} else {
				// check if the preceding bit is 1, if yes then prevLen should
				// be set to currLen (before resetting currLen)
				prevLen = ((number & 2) == 0) ? 0 : currLen;
				currLen = 0;
			}
			maxLen = Math.max(prevLen + currLen + 1, maxLen);
			number >>>= 1;
		}

		return maxLen;
	}
}

public class Exercise5_3 {

	public static void main(String[] args) {
		int test = -1;
		System.out.println("(Brute-force solution) The longest sequences of 1s in " + test + " = "
				+ FlipBit.longestSequencesOfOnes(test));
		System.out.println("(Optimized solution) The longest sequences of 1s in " + test + " = "
				+ OptimizedFlipBit.longestSequencesOfOnes(test));

	}

}
