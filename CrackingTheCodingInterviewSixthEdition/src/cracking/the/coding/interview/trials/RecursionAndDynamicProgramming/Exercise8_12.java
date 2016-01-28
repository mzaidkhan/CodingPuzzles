package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Exercise8_12 {

	public static int CHESS_BOARD_GRID = 8;

	public static int PRINT_SPACING = 4;

	private static boolean checkIfQueenCanBePlaced(int row, int col, Integer[] columns) {

		for (int row2 = 0; row2 < row; row2++) {

			int col2 = columns[row2];

			// Queen cannot be placed in the same column
			if (col == col2) {
				return false;
			}

			int rowDistance = row - row2;
			int colDistance = Math.abs(col - col2);

			// The row distance and column distance cannot be equal. Hence the
			// Queen cannot be placed in this position
			if (rowDistance == colDistance) {
				return false;
			}
		}
		return true;
	}

	public static void allPossibleQueenPlacement(Integer[] columns, List<Integer[]> resultsList) {
		allPossibleQueenPlacements(0, columns, resultsList);
	}

	private static void allPossibleQueenPlacements(int row, Integer[] columns, List<Integer[]> resultsList) {
		if (row == CHESS_BOARD_GRID) {
			resultsList.add(columns.clone());
		} else {
			for (int col = 0; col < CHESS_BOARD_GRID; col++) {
				if (checkIfQueenCanBePlaced(row, col, columns)) {
					columns[row] = col;
					allPossibleQueenPlacements(row + 1, columns, resultsList);
				}
			}
		}
	}

	public static void printValidBoards(List<Integer[]> results) {
		System.out.println("Number of possible ways 8 Queens can placed on a 8X8 grid are " + results.size());
		int i = 1;
		for (Integer[] soln : results) {
			System.out.println("Solution #" + i++);
			printBoard(soln);
		}
	}

	private static void printBoard(Integer[] soln) {
		displayLines();
		for (int i = 0; i < CHESS_BOARD_GRID; i++) {
			System.out.print("|");
			for (int j = 0; j < CHESS_BOARD_GRID; j++) {
				if (soln[i] == j) {
					System.out.printf("%" + PRINT_SPACING + "s", "Q |");
				} else {
					System.out.printf("%" + PRINT_SPACING + "s", "|");
				}
			}
			System.out.println();
			displayLines();
		}
	}

	private static void displayLines() {
		for (int k = 0; k < CHESS_BOARD_GRID * PRINT_SPACING + 1; k++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Integer[] columns = new Integer[CHESS_BOARD_GRID];
		Arrays.fill(columns, -1);
		List<Integer[]> resultsList = new LinkedList<>();
		allPossibleQueenPlacement(columns, resultsList);
		printValidBoards(resultsList);
	}
}
