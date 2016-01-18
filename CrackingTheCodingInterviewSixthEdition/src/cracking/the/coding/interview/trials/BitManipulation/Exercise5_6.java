package cracking.the.coding.interview.trials.BitManipulation;

class CountBits {

	public static int noOfBitsToSwapToChangeNumFromAtoB(int a, int b) {
		int count = 0;

		int c = a ^ b;

		while (c != 0) {
			if ((c & 1) == 1) {
				count++;
			}
			c >>= 1;
		}

		return count;
	}

	public static int noOfBitsToSwapToChangeNumFromAtoBOptimized(int a, int b) {
		int count = 0;

		int c = a ^ b;

		while (c != 0) {
			count++;
			// this will clear the least significant digit
			c = c & (c - 1);
		}

		return count;
	}
}

public class Exercise5_6 {

	public static void main(String[] args) {
		int a = 35;
		int b = 15;

		System.out.println(a + " in binary is " + Integer.toBinaryString(a));
		System.out.println(b + " in binary is " + Integer.toBinaryString(b));

		System.out.println("(Normal) No of bits to swap to change " + a + " to " + b + " = "
				+ CountBits.noOfBitsToSwapToChangeNumFromAtoB(a, b));

		System.out.println("(Optimized) No of bits to swap to change " + a + " to " + b + " = "
				+ CountBits.noOfBitsToSwapToChangeNumFromAtoBOptimized(a, b));

	}

}
