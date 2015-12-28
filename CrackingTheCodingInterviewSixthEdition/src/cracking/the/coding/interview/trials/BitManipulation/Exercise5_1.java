package cracking.the.coding.interview.trials.BitManipulation;

class BruteForceSolution {

	// Assuming bit positions i through j are upper and lower bounds of number m
	public static int insertion(int N, int M, int i, int j) {

		for (int k = i; k <= j; k++) {
			N = clearIthBit(N, k);
		}
		System.out.println("After clearing bits " + i + " through " + j + " in N = " + Integer.toBinaryString(N));
		M = leftShiftNum(M, i);
		System.out.println("After shifting " + i + " bits in M = " + Integer.toBinaryString(M));
		return N | M;

	}

	public static int clearIthBit(int n, int i) {
		return n & ~(1 << i);
	}

	public static int leftShiftNum(int n, int i) {
		return n << i;
	}

}

class SmartSolution {

	// Assuming bit positions i through j are upper and lower bounds of number m
	public static int insertion(int N, int M, int i, int j) {

		int mask;
		int left = ~0;
		int right = 1;

		// left shift j + 1 times, because we need to clear
		left = left << (j + 1);
		right = ((1 << i) - 1);
		mask = left | right;

		N = N & mask;
		System.out.println("After clearing bits " + i + " through " + j + " in N = " + Integer.toBinaryString(N));

		M = M << i;
		System.out.println("After shifting " + i + " bits in M = " + Integer.toBinaryString(M));

		return N | M;
	}

}

public class Exercise5_1 {

	public static void main(String[] args) {

		int N = 0b10001111100, M = 0b10011;
		int i = 2, j = 6;
		System.out.println("Original : N  = " + Integer.toBinaryString(N));
		System.out.println("Original : M  = " + Integer.toBinaryString(M));

		System.out.println("\nBrute force solution....");
		System.out.println("Solution : " + Integer.toBinaryString(BruteForceSolution.insertion(N, M, i, j)));

		System.out.println("\nSmart solution....");
		System.out.println("Solution : " + Integer.toBinaryString(SmartSolution.insertion(N, M, i, j)));
	}

}
