package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

public class Exercise8_7 {

	// Works only for unique characters within a string. Time complexity O(N!)
	public static void permutationsOfaString(String txt, String prefix) {

		int lengthOfStr = txt.length();
		if (lengthOfStr == 0) {
			System.out.println(prefix);
		}

		for (int i = 0; i < lengthOfStr; i++) {
			permutationsOfaString(txt.substring(0, i) + txt.substring(i + 1, lengthOfStr), prefix + txt.charAt(i));
		}
	}

	public static void main(String[] args) {
		permutationsOfaString("1234", "");
	}

}
