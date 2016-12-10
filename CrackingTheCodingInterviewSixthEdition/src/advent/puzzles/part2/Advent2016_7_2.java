package advent.puzzles.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Advent2016_7_2 {

	public static void main(String... args) throws FileNotFoundException {

		int noOfSSls = 0;
		Scanner fileScanner = new Scanner(new File("./src/advent/puzzles/advent2016_7.txt"));

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine().trim();
			System.out.println("String " + line);
			String[] lines = line.split("[\\[\\]]");
			HashSet<String> IP = new HashSet();
			HashSet<String> Hypernet = new HashSet();
			boolean check = true;
			for (String abba : lines) {
				if (check) {
					System.out.print("	checking for presence aba pattern in IP " + abba + "\n");
					IP.addAll(findXYXSubstring(abba, true));
				} else {
					System.out.print("	checking for presence bab pattern in Hypernet " + abba + "\n");
					Hypernet.addAll(findXYXSubstring(abba, false));
				}
				check = !check;
			}
			for (String string : Hypernet) {
				if (IP.contains(string)) {
					noOfSSls++;
					break;
				}
			}
		}
		fileScanner.close();
		System.out.print("No of IPs that support SSL are " + noOfSSls);
	}

	public static HashSet<String> findXYXSubstring(String str, boolean inv) {
		HashSet<String> set = new HashSet();
		for (int i = 0; i < str.length() - 2; i = i + 1) {
			if ((str.charAt(i) != str.charAt(i + 1)) && (str.charAt(i) == str.charAt(i + 2))) {
				System.out.print("		Found ABA substring in " + str.substring(i, i + 3) + "\n");
				StringBuilder sb = new StringBuilder();
				if (inv) {
					sb.append(str.charAt(i + 1)).append(str.charAt(i)).append(str.charAt(i + 1));
					set.add(sb.toString());
				} else {
					sb.append(str.charAt(i)).append(str.charAt(i + 1)).append(str.charAt(i + 2));
					set.add(sb.toString());
				}
			}
		}
		return set;
	}
}
