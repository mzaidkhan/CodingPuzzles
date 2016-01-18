package cracking.the.coding.interview.trials.BitManipulation;

class SwapEvenOdd {

	// since Integers is 32 bits in Java the masks are chosen appropriately
	// 0xa is 1010
	// 0x5 is 0101
	public static int swapEvenOdd(int num) {
		// 1. Arithmetic right shift all the even digits
		// 2. Left shift all the odd digits
		// 3. Result of 1 Bitwise-OR Result of 2 is the number that represents
		// the swapped even odd bits
		return ((num & 0xaaaaaaaa) >>> 1 | (num & 0x55555555) << 1);
	}
}

public class Exercise5_7 {

	public static void main(String[] args) {
		int num = 1055;
		System.out.println(num + " in binary is " + Integer.toBinaryString(num));
		num = SwapEvenOdd.swapEvenOdd(num);
		System.out.println(num + " in binary is " + Integer.toBinaryString(num));
	}
}
