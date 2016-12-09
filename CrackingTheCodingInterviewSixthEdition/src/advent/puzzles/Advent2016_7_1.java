package advent.puzzles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Advent2016_7_1 {

	public static void main(String... args) throws FileNotFoundException {

		int noOfValidIPs = 0;
		Scanner fileScanner = new Scanner(new File("./src/advent/puzzles/advent2016_7.txt"));

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine().trim();
			System.out.println("String " + line);
			String[] lines = line.split("[\\[\\]]");
			boolean check = true;
			boolean flag = false, flag2 = false;
			for (String abba : lines) {
				if (check) {
					System.out.print("	checking for presence abba pattern " + abba + "\n");
					flag = checkAbbaSubstring(abba) || flag;
				} else {
					System.out.print("	checking for non presence abba pattern " + abba + "\n");
					flag2 = checkNonAbbaSubstring(abba);
					if(!flag2){
						break;
					}
				}
				check = !check;
			}
			if (flag & flag2) {
				noOfValidIPs++;
			}
		}
		fileScanner.close();
		System.out.print("No of valid IPs " + noOfValidIPs);
	}

	public static boolean checkAbbaSubstring(String str) {
		for (int i = 0; i < str.length() - 3; i = i + 1) {
			if ((str.charAt(i) == str.charAt(i + 1)) && (str.charAt(i + 1) == str.charAt(i + 2))
					&& (str.charAt(i + 2) == str.charAt(i + 3))) {
				System.out.print("		Found interior characters in IP " + str.substring(i, i + 4) + "\n");
				return false;
			} else if ((str.charAt(i) != str.charAt(i + 1)) && (str.charAt(i) == str.charAt(i + 3))
					&& (str.charAt(i + 1) == str.charAt(i + 2))) {
				System.out.print("		Found ABBA substring in " + str.substring(i, i + 4) + "\n");
				return true;
			}
		}
		return false;
	}

	public static boolean checkNonAbbaSubstring(String str) {
		for (int i = 0; i < str.length() - 3; i = i + 1) {
			if ((str.charAt(i) != str.charAt(i + 1)) && (str.charAt(i) == str.charAt(i + 3))
					&& (str.charAt(i + 1) == str.charAt(i + 2))) {
				System.out.println("		Found interior characters in hypernet " + str.substring(i, i + 4));
				return false;
			}
		}
		return true;
	}
}
