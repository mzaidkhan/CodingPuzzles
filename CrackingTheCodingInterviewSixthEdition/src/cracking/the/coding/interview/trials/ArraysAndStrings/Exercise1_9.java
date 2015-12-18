package cracking.the.coding.interview.trials.ArraysAndStrings;

public class Exercise1_9 {

	public static boolean isRotation(String str1, String str2) {
		boolean retVal = false;
		if (str1 != null && str2 != null && str1.length() == str2.length()) {
			String str1str1 = str1 + str1;
			return str1str1.contains(str2);
		}
		return retVal;
	}

	public static void main(String[] args) {
		System.out.println("erbottlewat is a rotation of waterbottle: " + isRotation("waterbottle", "erbottlewat"));
		System.out.println("erbottlwat is a rotation of waterbottle: " + isRotation("waterbottle", "erbottlwat"));
	}

}
