package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import cracking.the.coding.interview.trials.Helper.Helper;

class Index {
	int row;
	int col;

	public Index(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Exercise8_10 {

	public static Color[][] copy(Color[][] src) {
		Color[][] dst = new Color[src.length][];
		for (int i = 0; i < src.length; i++) {
			dst[i] = Arrays.copyOf(src[i], src[i].length);
		}
		return dst;
	}

	public enum Color {
		Red, Orange, Yellow, Green, Blue, Indigo, Violet, Black, White;

		public static String getColor(Color c) {
			switch (c) {
			case Red:
				return "R";
			case Orange:
				return "O";
			case Yellow:
				return "Y";
			case Green:
				return "G";
			case Blue:
				return "Bu";
			case Indigo:
				return "I";
			case Violet:
				return "V";
			case Black:
				return "B";
			case White:
				return "W";
			default:
				return "";

			}
		}
	}

	public static void printScreen(Color[][] screen) {
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[i].length; j++) {
				System.out.printf("%3s", Color.getColor(screen[i][j]));
				System.out.flush();
			}
			System.out.println();
		}
	}

	public static boolean fillPaintDFS(Color[][] screen, int row, int col, Color newColor) {
		if (screen[row][col] == newColor) {
			return false;
		}
		return paintFillerDFS(screen, row, col, newColor, screen[row][col]);
	}

	// Based on Depth First Search
	private static boolean paintFillerDFS(Color[][] screen, int row, int col, Color newColor, Color oldColor) {
		if (row < 0 || row >= screen.length || col < 0 || col >= screen[0].length) {
			return false;
		}

		if (screen[row][col] == oldColor) {
			screen[row][col] = newColor;
			paintFillerDFS(screen, row + 1, col, newColor, oldColor);
			paintFillerDFS(screen, row - 1, col, newColor, oldColor);
			paintFillerDFS(screen, row, col - 1, newColor, oldColor);
			paintFillerDFS(screen, row, col + 1, newColor, oldColor);
		}
		return true;
	}

	public static boolean fillPaintBFS(Color[][] screen, int row, int col, Color newColor) {
		if (screen[row][col] == newColor) {
			return false;
		}
		return paintFillerBFS(screen, row, col, newColor, screen[row][col]);
	}

	// Based on Breadth First Search but will require Queue
	private static boolean paintFillerBFS(Color[][] screen, int row, int col, Color newColor, Color oldColor) {

		Queue<Index> queue = new LinkedList<Index>();
		queue.add(new Index(row, col));

		while (!queue.isEmpty()) {
			Index curIndx = queue.poll();
			if (curIndx.row >= 0 && curIndx.row < screen.length && curIndx.col >= 0 && curIndx.col < screen[0].length) {
				if (screen[curIndx.row][curIndx.col] == oldColor) {
					screen[curIndx.row][curIndx.col] = newColor;
					queue.add(new Index(curIndx.row + 1, curIndx.col));
					queue.add(new Index(curIndx.row - 1, curIndx.col));
					queue.add(new Index(curIndx.row, curIndx.col + 1));
					queue.add(new Index(curIndx.row, curIndx.col - 1));
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int N = 10;
		Color[][] screen = new Color[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				screen[i][j] = Color.Blue;
			}
		}
		for (int i = 0; i < N; i++) {
			screen[Helper.randomInt(N)][Helper.randomInt(N)] = Color.Green;
		}

		Color[][] screenC1 = copy(screen);
		Color[][] screenC2 = copy(screen);

		System.out.println("DFS method");
		printScreen(screenC1);
		fillPaintDFS(screenC1, 2, 2, Color.White);
		System.out.println();
		printScreen(screenC1);

		System.out.println("\nBFS method");
		printScreen(screenC2);
		fillPaintBFS(screenC2, 2, 2, Color.White);
		System.out.println();
		printScreen(screenC2);

		if (Arrays.deepEquals(screenC1, screenC2)) {
			System.out.println("Test passed: same result for both methods.");
		} else {
			System.out.println("Test failed: different result for both methods.");
		}
	}
}
