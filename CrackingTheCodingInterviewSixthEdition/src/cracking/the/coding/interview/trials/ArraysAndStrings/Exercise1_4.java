package cracking.the.coding.interview.trials.ArraysAndStrings;

import java.util.Arrays;

public class Exercise1_4 {

	public static boolean checkPalindromePermutation(String a, String b) {
		if (a.length() == b.length()) {

			String[] aArr = a.split(" ");
			String[] bArr = b.split(" ");

			// System.out.println(Arrays.toString(aArr));
			// System.out.println(Arrays.toString(bArr));

			if (aArr.length == bArr.length) {
				for (int i = 0; i < aArr.length; i++) {
					if (!checkSortEqual(aArr[i], bArr[i])) {
						return false;
					}
				}
				return true;

			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean checkSortEqual(String a, String b) {
		if (a.length() != b.length()) {
			return false;
		}
		return sort(a).equals(sort(b));
	}

	public static String sort(String a) {
		char[] arr = a.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}

	public static void main(String[] args) {

		System.out.println("\"Toca cat\" and \"acTo tac\" are permutations of each other: "
				+ checkPalindromePermutation("Toca cat", "acTo tac"));

		System.out.println("\"Toca cat\" and \"acto tac\" are permutations of each other: "
				+ checkPalindromePermutation("Toca cat", "acto tac"));
	}

}
