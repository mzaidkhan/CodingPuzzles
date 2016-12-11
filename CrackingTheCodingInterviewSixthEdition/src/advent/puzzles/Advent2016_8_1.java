package advent.puzzles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Advent2016_8_1 {

	public static boolean[][] display = new boolean[6][50];

	static {
		System.out.println("Init Display... all bits off");
		display();
	}

	public static void display() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 50; j++) {
				System.out.print((display[i][j] ? "#" : ".") + " ");
			}
			System.out.println();
		}
	}

	public static void main(String... args) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(new File("./src/advent/puzzles/advent2016_8.txt"));
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine().trim();
			System.out.println(line);
			if (line.startsWith("rect")) {
				String[] split = line.split("\\s+");
				if (split.length > 1) {
					split = split[1].split("x");
					System.out.println("	" + Integer.valueOf(split[0]) + ", " + Integer.valueOf(split[1]));
					rectOperation(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
				}
			} else if (line.startsWith("rotate row y")) {
				String[] split = line.split("=");
				if (split.length > 1) {
					split = split[1].split(" by ");
					System.out.println("	" + Integer.valueOf(split[0]) + ", " + Integer.valueOf(split[1]));
					rotateRowOperation(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
				}
			} else if (line.startsWith("rotate column x")) {
				String[] split = line.split("=");
				if (split.length > 1) {
					split = split[1].split(" by ");
					System.out.println("	" + Integer.valueOf(split[0]) + ", " + Integer.valueOf(split[1]));
					rotateColOperation(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
				}
			}
		}
		fileScanner.close();
		System.out.println("\n Final display....");
		display();
		int noOfOnPixels = 0;
		for (int i = 0; i < display.length; i++) {
			for (int j = 0; j < display[i].length; j++) {
				if (display[i][j]) {
					noOfOnPixels++;
				}
			}
		}
		System.out.println("\n No of ON Pixels " + noOfOnPixels);
	}

	public static void rectOperation(int cols, int rows) {
		if (rows > 0 && cols > 0) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					display[i][j] = true;
				}
			}
		}
	}

	public static void rotateColOperation(int colNo, int rotateBy) {
		boolean[] tmp = new boolean[6];
		for (int i = 0; i < 6; i++) {
			tmp[i] = display[i][colNo];
		}
		boolean[] tmp2 = new boolean[rotateBy];
		for (int i = 0; i < rotateBy; i++)
			tmp2[i] = tmp[tmp.length - rotateBy + i];
		System.arraycopy(tmp, 0, tmp, rotateBy, tmp.length - rotateBy);
		for (int i = 0; i < rotateBy; i++) {
			tmp[i] = tmp2[i];
		}
		for (int i = 0; i < 6; i++) {
			display[i][colNo] = tmp[i];
		}
	}

	public static void rotateRowOperation(int rowNo, int rotateBy) {
		boolean[] tmp = new boolean[rotateBy];
		for (int i = 0; i < rotateBy; i++) {
			tmp[i] = display[rowNo][display[rowNo].length - rotateBy + i];
		}
		System.arraycopy(display[rowNo], 0, display[rowNo], rotateBy, display[rowNo].length - rotateBy);
		for (int i = 0; i < rotateBy; i++) {
			display[rowNo][i] = tmp[i];
		}
	}
}
