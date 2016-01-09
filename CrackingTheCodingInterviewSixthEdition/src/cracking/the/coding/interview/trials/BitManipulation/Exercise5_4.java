package cracking.the.coding.interview.trials.BitManipulation;

// Brute force approach
class Sequence {

	public static boolean checkTheNoOfOnesInNumber(int number, int eCount) {
		int count = 0;
		// Integer is represented as 32 bits
		count = getCountOf1Bits(number);
		return (count == eCount);
	}

	public static int getCountOf1Bits(int number) {
		int count = 0;
		for (int i = 0; i < Exercise5_4.SIZE_OF_INTS; i++) {
			if ((number & (1 << i)) != 0) {
				count++;
			}
		}
		return count;
	}

	public static int getCountOf0Bits(int number) {
		return Exercise5_4.SIZE_OF_INTS - getCountOf1Bits(number);
	}

	public static boolean possibleToLowerHigherNum(int i) {

		/*
		 * If the number is a sequence of 0s followed by a sequence of 1s, then
		 * this is already the smallest possible number with such a sequence of
		 * 1s. Returning false to report an error.
		 */
		while ((i & 1) == 1) {
			i >>= 1;
		}
		if (i == 0) {
			return false;
		}
		return true;
	}

	public static boolean possibleToGetHigherNum(int i) {
		// if number has no 1s there is no higher number without any 1s
		if (i == 0) {
			return false;
		}
		/*
		 * If the number is a sequence of 1s followed by a sequence of 0s, then
		 * this is already the biggest possible number with such a sequence of
		 * 1s. Returning false to report an error.
		 */
		int count = 1;
		while ((i & 1) == 0) {
			i >>= 1;
			count++;
		}
		while ((i & 1) == 1) {
			i >>= 1;
			count++;
		}

		if (count == Exercise5_4.SIZE_OF_INTS) {
			return false;
		}
		return true;
	}

	public static int getIntWithSame1Bits(int number, int eCountNum, boolean larger) {

		if (larger) {
			if (!possibleToGetHigherNum(number)) {
				return -1;
			}
		} else {
			if (!possibleToLowerHigherNum(number)) {
				return -1;
			}
		}

		while (true) {
			if (larger) {
				number++;
			} else {
				number--;
			}

			if (checkTheNoOfOnesInNumber(number, eCountNum)) {
				break;
			}
		}
		return number;
	}

}

// Bit Manipulation O(N), where n is the no of bits used to represent the number
class SequenceOpt {

	public static int getLargerNumberWithSame1Bits(int number) {

		int noOf1s = 0;
		int noOf0s = 0;
		int cNum = number;

		while ((cNum & 1) == 0 && cNum != 0) {
			noOf0s++;
			cNum >>= 1;
		}

		while ((cNum & 1) == 1) {
			noOf1s++;
			cNum >>= 1;
		}

		int positionToFlip = noOf1s + noOf0s;
		// Error:
		// Either 1. The number is 0, so there is no higher number than this
		// with no 1s
		// Or 2. The number is a sequences of 1s followed by a sequence of 0s,
		// it is already the largest possible number with such a sequence
		if ((positionToFlip + 1 == Exercise5_4.SIZE_OF_INTS) || (positionToFlip == 0)) {
			return -1;
		}

		// Marking 1 to the position to flip from 0 to 1
		int pos = 1 << positionToFlip;
		// Flipping the position
		number |= pos;
		// Marking all bit to 1 after the flipped bit
		pos = pos - 1;
		// Marking all the bits before flipped bit to 1s and after it to a
		// sequence 0s
		pos = ~pos;
		// Clearing all the bits after flipped position to 0s
		number &= pos;
		// Preparing to a number with a sequence of (noOf1s - 1), 1s
		int onesToAdd = (1 << (noOf1s - 1)) - 1;
		// Adding 1s to the number
		number |= onesToAdd;
		return number;
	}

	public static int getSmallerNumberWithSameNoOf1Bits(int number) {

		int noOf1s = 0;
		int noOf0s = 0;
		int cNum = number;

		while ((cNum & 1) == 1) {
			noOf1s++;
			cNum >>= 1;
		}

		// If the number is a sequence of 0s followed by a sequence of 1s, then
		// this is already the smallest possible number with such a sequence of
		// 1s. Returning false to report an error.
		if (cNum == 0) {
			return -1;
		}

		while ((cNum & 1) == 0 && (cNum != 0)) {
			noOf0s++;
			cNum >>= 1;
		}

		int positionToFlip = noOf0s + noOf1s;

		// A number with all 1 bits
		int tmp = ~0;
		// Creating a sequence of 1s followed by (positionToFlip + 1) 0s
		tmp = (tmp << (positionToFlip + 1));
		// Clearing all bits from 0 through positionToFlip
		number &= tmp;
		// Preparing 0s followed by (noOf1s + 1) 1s
		tmp = (1 << (noOf1s + 1)) - 1;
		// Preparing (noOf1s + 1) 1s followed by (noOfOs - 1) 0s
		tmp = tmp << (noOf0s - 1);
		return number | tmp;
	}
}

// Solved with Arithmetic
class SequenceWithArithmetic {

	public static int getLargerNumberWithSame1Bits(int number) {

		int noOf1s = 0;
		int noOf0s = 0;
		int cNum = number;

		while ((cNum & 1) == 0 && cNum != 0) {
			noOf0s++;
			cNum >>= 1;
		}

		while ((cNum & 1) == 1) {
			noOf1s++;
			cNum >>= 1;
		}

		int flipPos = noOf0s + noOf1s;

		// Number is the largest possible
		if ((flipPos + 1 == Exercise5_4.SIZE_OF_INTS) || flipPos == 0) {
			return -1;
		}

		return number + (1 << noOf0s) + (1 << (noOf1s - 1)) - 1;
	}

	public static int getSmallerNumberWithSameNoOf1Bits(int number) {
		int noOf1s = 0;
		int noOf0s = 0;
		int cNum = number;

		while ((cNum & 1) == 1) {
			noOf1s++;
			cNum >>= 1;
		}

		// 0s followed by a sequence 1s, already the smallest possible number
		if (cNum == 0) {
			return -1;
		}

		while ((cNum & 1) == 0 && cNum != 0) {
			noOf0s++;
			cNum >>= 1;
		}

		return number - (1 << noOf1s) - (1 << (noOf0s - 1)) + 1;
	}

}

public class Exercise5_4 {

	public static final int SIZE_OF_INTS = Integer.BYTES * 8;

	public static void main(String[] args) {

		int number = 1775;
		System.out.println("Brute force solution...");
		int eCountNum = Sequence.getCountOf1Bits(number);
		System.out.println(number + ", has " + eCountNum + ", 1 bits");

		int smallerNum = Sequence.getIntWithSame1Bits(number, eCountNum, false);
		System.out.println("The next smaller number with " + eCountNum + " bits is " + smallerNum);
		int largerNum = Sequence.getIntWithSame1Bits(number, eCountNum, true);
		System.out.println("The next bigger number with " + eCountNum + " bits is " + largerNum);

		// System.out.println(number + " binary representation = " +
		// Integer.toBinaryString(number));
		// System.out.println(smallerNum + " binary representation = " +
		// Integer.toBinaryString(smallerNum));
		// System.out.println(largerNum + " binary representation = " +
		// Integer.toBinaryString(largerNum));

		System.out.println("\nBit Manipulation solution...");
		System.out.println("The next smaller number with " + SequenceOpt.getSmallerNumberWithSameNoOf1Bits(number));
		System.out.println("The next bigger number with " + SequenceOpt.getLargerNumberWithSame1Bits(number));

		System.out.println("\nArithmetic solution...");
		System.out.println(
				"The next smaller number with " + SequenceWithArithmetic.getSmallerNumberWithSameNoOf1Bits(number));
		System.out
				.println("The next bigger number with " + SequenceWithArithmetic.getLargerNumberWithSame1Bits(number));
	}
}
