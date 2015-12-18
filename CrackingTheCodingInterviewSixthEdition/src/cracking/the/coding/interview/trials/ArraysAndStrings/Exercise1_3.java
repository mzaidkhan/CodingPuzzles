package cracking.the.coding.interview.trials.ArraysAndStrings;

public class Exercise1_3 {

	public static String urlify(char[] cArr, int length) {

		int spaceCount = 0, newLength = 0;

		for (int i = 0; i < length; i++) {
			if (cArr[i] == ' ') {
				spaceCount++;
			}
		}
		newLength = length + spaceCount * 2;
		for (int i = length - 1; i >= 0; i--) {
			if (cArr[i] == ' ') {
				cArr[newLength - 1] = '0';
				cArr[newLength - 2] = '2';
				cArr[newLength - 3] = '%';
				newLength -= 3;
			} else {
				cArr[newLength - 1] = cArr[i];
				newLength -= 1;
			}
		}

		return new String(cArr);

	}

	public static void main(String[] args) {
		char[] cArr = new char[17];
		cArr[0] = 'M';
		cArr[1] = 'r';
		cArr[2] = ' ';
		cArr[3] = 'J';
		cArr[4] = 'o';
		cArr[5] = 'h';
		cArr[6] = 'n';
		cArr[7] = ' ';
		cArr[8] = 'S';
		cArr[9] = 'm';
		cArr[10] = 'i';
		cArr[11] = 't';
		cArr[12] = 'h';
		System.out.println("The urlify verision of \"Mr John Smith\" is: \"" + urlify(cArr, 13) +"\"");
	}

}
