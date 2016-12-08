package advent.puzzles.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Advent2016_3_2_adv {
	public static int noOfValidTriangles = 0, noOfInvalidTriangles = 0;

	public static void main(String... args) throws FileNotFoundException {

		Scanner fileScanner = new Scanner(new File("./src/advent/puzzles/advent2016_3_1.txt"));

		String[][] lines = new String[3][3];

		int count = 0;

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine().trim();
			lines[count] = line.split("\\s+");
			if (count == 2) {
				count = 0;
				isValidTriangle(lines);
				lines = new String[3][3];
			} else {
				count++;
			}
		}
		fileScanner.close();

		System.out.println("No. of Valid Triangles:" + noOfValidTriangles);
		System.out.println("No. of InValid Triangles:" + noOfInvalidTriangles);
	}

	public static void isValidTriangle(String[][] lines) {

		for (int i = 0, j = 0; i < 3; i++) {
			try {
				if (isTriangle2(Integer.valueOf(lines[j][i].trim()), Integer.valueOf(lines[j + 1][i].trim()),
						Integer.valueOf(lines[j + 2][i].trim()))) {
					noOfValidTriangles++;
				} else {
					noOfInvalidTriangles++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isTriangle2(int... sides) throws Exception {
		if (sides.length != 3) {
			throw new Exception("Triangle must have three sides.");
		}
		Arrays.sort(sides);
		// System.out.println("Sorted - > A = " + sides[0] + ", B = " + sides[1]
		// + ", C = " + sides[2]);
		return (sides[2] < (sides[1] + sides[0]));
	}

	public static boolean isTriangle(String... sides) throws Exception {
		if (sides.length != 3) {
			throw new Exception("Triangle must have three sides.");
		}
		return (((Integer.valueOf(sides[0]) + Integer.valueOf(sides[1])) > Integer.valueOf(sides[2]))
				&& ((Integer.valueOf(sides[1]) + Integer.valueOf(sides[2])) > Integer.valueOf(sides[0]))
				&& ((Integer.valueOf(sides[2]) + Integer.valueOf(sides[0])) > Integer.valueOf(sides[1])));
	}
}
