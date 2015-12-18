package cracking.the.coding.interview.trials.ArraysAndStrings;

public class Exercise1_5 {

	public static boolean checkOneEditAway(String a, String b) {
		boolean retVal = false;

		if (a.length() == b.length()) {
			retVal = checkOneReplace(a, b);
		} else if (a.length() + 1 == b.length()) {
			retVal = checkOneInsert(a, b);
		} else if (a.length() == b.length() + 1) {
			retVal = checkOneInsert(a, b);
		}
		return retVal;
	}

	private static boolean checkOneInsert(String a, String b) {
		int index1 = 0, index2 = 0;
		while (index1 < a.length() && index2 < b.length()) {
			if (a.charAt(index1) != b.charAt(index2)) {
				if(index1 != index2){
					return false;
				}
				index1++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}

	private static boolean checkOneReplace(String a, String b) {
		boolean retVal = true, found = false;

		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				if (found)
					return retVal = false;
				found = true;
			}
		}

		return retVal;
	}

	public static void main(String[] args) {
		System.out.println("Check one edit pale and bale :" + checkOneEditAway("pale", "bale"));
		System.out.println("Check one edit pale and bake :" + checkOneEditAway("pale", "bake"));
		System.out.println("Check one edit pale and ple :" + checkOneEditAway("pale", "ple"));
		System.out.println("Check one edit pale and ple :" + checkOneEditAway("pale", "ples"));
		System.out.println("Check one edit ple and ples :" + checkOneEditAway("ple", "ples"));
	}

}
