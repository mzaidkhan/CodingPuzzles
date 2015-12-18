package cracking.the.coding.interview.trials.ArraysAndStrings;

public class Exercise1_6 {

	public static String stringCompress(String org) {
		if (org == null || "".equals(org) || org.length() < 2) {
			return org;
		}
		// Optimization when if the length of compressed string is less than
		// that of original string
		int finalLength = checkOrPerformCompress(org, null, false);

		if (finalLength < org.length()) {
			StringBuilder comp = new StringBuilder(finalLength);
			checkOrPerformCompress(org, comp, true);
			return comp.toString();
		} else {
			return org;
		}
	}

	private static void appendToComp(StringBuilder comp, char tmp, int count) {
		comp.append(tmp);
		comp.append(count);
	}

	private static int checkOrPerformCompress(String org, StringBuilder comp, boolean compress) {
		int finalLength = 0;
		int consecutiveCount = 0;
		for (int i = 0; i < org.length(); i++) {
			consecutiveCount++;
			if (i + 1 >= org.length() || org.charAt(i) != org.charAt(i + 1)) {
				if (compress) {
					appendToComp(comp, org.charAt(i), consecutiveCount);
				} else {
					finalLength += 1 + String.valueOf(consecutiveCount).length();
				}
				consecutiveCount = 0;
			}
		}
		return finalLength;
	}

	public static void main(String[] args) {
		String org = new String();
		org = "aabcccccaaa";
		System.out.println(org + " is compressed to " + stringCompress(org));
		org = "abcd";
		System.out.println(org + " is compressed to " + stringCompress(org));
		org = "a";
		System.out.println(org + " is compressed to " + stringCompress(org));
		org = "ab";
		System.out.println(org + " is compressed to " + stringCompress(org));
		org = "aaa";
		System.out.println(org + " is compressed to " + stringCompress(org));
		org = "";
		System.out.println(org + " is compressed to " + stringCompress(org));
		org = null;
		System.out.println(org + " is compressed to " + stringCompress(org));
	}

}
