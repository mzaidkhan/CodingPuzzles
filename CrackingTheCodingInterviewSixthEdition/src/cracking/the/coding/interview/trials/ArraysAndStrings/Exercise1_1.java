package cracking.the.coding.interview.trials.ArraysAndStrings;

import java.util.Arrays;

public class Exercise1_1 {

	public static boolean isUnique(String str) {
		boolean retVal = false;

		if (str != null && str.length() > 0) {
			char[] cArr = str.toCharArray();
			Arrays.sort(cArr);
			String copiedStr = new String(cArr);
			retVal = true;
			for (int i = 1; i < copiedStr.length(); i++) {
				if (copiedStr.charAt(i - 1) == copiedStr.charAt(i)) {
					retVal = false;
					break;
				}
			}
		} 
		return retVal;
	}

	public static void main(String[] args) {
		System.out.println("This is a unique string: " + isUnique("a"));
		System.out.println("This is a unique string: " + isUnique("abcd"));
		System.out.println("This is not a unique string: " + isUnique("dabcd"));
	}

}
