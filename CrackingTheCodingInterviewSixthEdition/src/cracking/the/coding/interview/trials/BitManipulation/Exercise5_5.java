package cracking.the.coding.interview.trials.BitManipulation;

public class Exercise5_5 {

	public static void main(String[] args) {

		// The equation ((n & (n - 1)) == 0) checks is n is a power of 2.
		for (int n = 0; n < Integer.MAX_VALUE; n++) {
			if ((n & (n - 1)) == 0) {
				System.out.println(n);
			}
		}
	}

}
