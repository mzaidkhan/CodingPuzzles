package advent.puzzles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Advent2016_3_1 {

	public static void main(String... args) {
		int noOfValidTriangles = 0, noOfInvalidTriangles = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("./src/advent/puzzles/advent2016_3_1.txt"));

			String line = br.readLine();

			while (line != null) {
				if (!"".equals(line)) {
					line = line.trim();
					String[] sides = line.split("  ");

					if (sides.length == 3) {
						if (isTriangle(Integer.valueOf(sides[0].trim()), Integer.valueOf(sides[1].trim()),
								Integer.valueOf(sides[2].trim()))) {
							noOfValidTriangles++;
						} else {
							noOfInvalidTriangles++;
						}

					} else {
						noOfInvalidTriangles++;
					}
				}
				line = br.readLine();
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
		return (a + b > c) && (b + c > a) && (c + a > b);
	}
}
