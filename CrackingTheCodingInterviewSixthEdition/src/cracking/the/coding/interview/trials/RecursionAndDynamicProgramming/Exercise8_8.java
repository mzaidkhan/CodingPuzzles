package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.HashMap;

public class Exercise8_8 {

	public static HashMap<String, String> hTable = new HashMap<String, String>();

	// Time and space complexity of O(N!)
	public static void permutations(String prefix, String str) {
		int sLen = str.length();

		if (sLen == 0) {
			if (!hTable.containsKey(prefix)) {
				hTable.put(prefix, prefix);
				// System.out.println(prefix);
			}
			// else {
			// System.out.println(prefix + " is already in the table. Hence
			// not added.");
			// }
		}

		for (int index = 0; index < sLen; index++) {
			permutations(prefix + str.charAt(index), str.substring(0, index) + str.substring(index + 1, sLen));
		}
	}

	// Better solution to minimize the no of calculations when there characters
	// of the string are not unique. Time and space complexity is O(N!)
	public static void permutations(HashMap<Character, Integer> map, String prefix, int remaining) {
		if (remaining == 0) {
			hTable.put(prefix, prefix);
			// System.out.println(prefix);
		}

		for (Character ch : map.keySet()) {
			int count = map.get(ch);
			if (count > 0) {
				map.put(ch, count - 1);
				permutations(map, prefix + ch, remaining - 1);
				map.put(ch, count);
			}
		}

	}

	public static HashMap<Character, Integer> buildFreqTable(String str) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (Character ch : str.toCharArray()) {
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String str = new String("aabbbcccb");
		hTable.clear();
		System.out.println("Not so clever solution....");
		permutations("", str);
		System.out.println("Total no of permutations: " + hTable.size());
		hTable.clear();

		System.out.println("Much more clever solution....");
		permutations(buildFreqTable(str), "", str.length());
		System.out.println("Total no of permutations: " + hTable.size());
		hTable.clear();
	}

}
