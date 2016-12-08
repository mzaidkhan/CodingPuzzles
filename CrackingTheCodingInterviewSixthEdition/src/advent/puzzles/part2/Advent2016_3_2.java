package advent.puzzles.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Advent2016_3_2 {

	public static void main(String... args) {
		int noOfValidTriangles = 0, noOfInvalidTriangles = 0;
		LinkedList<Integer>[] lists = new LinkedList[3];
		lists[0] = new LinkedList<Integer>();
		lists[1] = new LinkedList<Integer>();
		lists[2] = new LinkedList<Integer>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"/Users/mzaidkhan/git/CodingPuzzles/CrackingTheCodingInterviewSixthEdition/src/advent/puzzles/advent2016_3_1.txt"));

			String line = br.readLine();

			while (!"".equals(line) && line != null) {
				line = line.trim();
				String[] sides = line.split("\\s+");
				// System.out.println(Arrays.toString(sides));
				for (int i = 0; i < sides.length; i++) {
					if (!"".equals(sides[i])) {
						lists[i].add(Integer.valueOf(sides[i].trim()));
					}
				}
				line = br.readLine();
			}
			LinkedList<Integer> oneList = new LinkedList();
			oneList.addAll(lists[0]);
			oneList.addAll(lists[1]);
			oneList.addAll(lists[2]);
			lists = null;
			System.out.println(Arrays.toString(oneList.toArray()));

			for (int i = 0; i < oneList.size(); i = i + 3) {
				if (isTriangle(oneList.get(i), oneList.get(i + 1), oneList.get(i + 2))) {
					noOfValidTriangles++;
					System.out.println("Found");
				} else {
					noOfInvalidTriangles++;
					System.out.println("Not Found");

				}
			}
			System.out.println("No. of Valid Triangles:" + noOfValidTriangles);
			System.out.println("No. of InValid Triangles:" + noOfInvalidTriangles);

		} catch (Exception Ex) {
			Ex.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	public static boolean isTriangle(int a, int b, int c) {
		System.out.println("A " + a + " B " + b + " C " + c);
		return (a + b > c) && (b + c > a) && (c + a > b);
	}
}
