package cracking.the.coding.interview.trials.ArraysAndStrings;

import java.util.Arrays;

public class Exercise1_2 {

	public static boolean checkPermutations(String a, String b) {
		if (a.length() != b.length())
			return false;
		return sort(a).equals(sort(b));
	}

	public static String sort(String a){
		char[] cArr = a.toCharArray();
		Arrays.sort(cArr);
		return new String(cArr);
	}
	public static void main(String[] args) {
		System.out.println("A, A are permutations " + checkPermutations("A", "A"));
		System.out.println("AB, A are not permutations " + checkPermutations("AB", "A"));
		System.out.println("A, AB are not permutations " + checkPermutations("A", "AB"));
		System.out.println("ABC, CBA are permutations " + checkPermutations("ABC", "CBA"));
		System.out.println("ABC, BAC are  permutations " + checkPermutations("ABC", "BAC"));
	}

}
