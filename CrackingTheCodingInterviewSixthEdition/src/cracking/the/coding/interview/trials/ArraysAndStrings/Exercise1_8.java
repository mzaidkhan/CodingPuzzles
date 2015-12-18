package cracking.the.coding.interview.trials.ArraysAndStrings;

import java.util.Arrays;

public class Exercise1_8 {

	public static void optimizedZeroMat(int[][] mat) {
		boolean firstRowHasZeros = false, firstColHasZeros = false;
		int rowLen = mat.length;
		int colLen = mat[0].length;
		for (int i = 0; i < rowLen; i++) {
			if (mat[i][0] == 0) {
				firstRowHasZeros = true;
				break;
			}
		}
		for (int i = 0; i < colLen; i++) {
			if (mat[0][i] == 0) {
				firstColHasZeros = true;
				break;
			}
		}

		for (int i = 1; i < rowLen; i++)
			for (int j = 1; j < colLen; j++) {
				if (mat[i][j] == 0) {
					mat[i][0] = 0;
					mat[0][j] = 0;
				}
			}

		for (int i = 1; i < rowLen; i++) {
			if (mat[i][0] == 0) {
				for (int j = 1; j < colLen; j++) {
					mat[i][j] = 0;
				}
			}
		}
		
		for (int i = 1; i < colLen; i++) {
			if (mat[0][i] == 0) {
				for (int j = 1; j < rowLen; j++) {
					mat[j][i] = 0;
				}
			}
		}
		
		if(firstRowHasZeros){
			for (int j = 1; j < colLen; j++) {
				mat[0][j] = 0;
			}
		}
		
		if(firstColHasZeros){
			for (int j = 1; j < rowLen; j++) {
				mat[j][0] = 0;
			}
		}
	}

	public static void zeroMat(int[][] mat) {
		int rowLen = mat.length;
		int colLen = mat[0].length;
		boolean[] arrRow = new boolean[rowLen];
		boolean[] arrCol = new boolean[colLen];
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				if (mat[i][j] == 0) {
					arrRow[i] = true;
					arrCol[j] = true;
				}
			}
		}
		iterateArr(arrRow, mat, true);
		iterateArr(arrCol, mat, false);
	}

	public static void nullifyMat(int[][] mat, int index, boolean rowOrColumn) {
		if (rowOrColumn) {
			for (int i = 0; i < mat[index].length; i++) {
				mat[index][i] = 0;
			}
		} else {
			for (int i = 0; i < mat.length; i++) {
				mat[i][index] = 0;
			}
		}
	}

	public static void iterateArr(boolean[] arr, int[][] mat, boolean rowOrColumn) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]) {
				nullifyMat(mat, i, rowOrColumn);
			}
		}
	}

	public static void main(String[] args) {
		int[][] mat = { { 0, 98, 97, 96, 95, 94 }, { 89, 88, 87, 86, 85, 84 }, { 13, 14, 15, 16, 17, 18 },
				{ 19, 20, 21, 0, 23, 24 }, { 25, 26, 27, 28, 29, 30 }, { 31, 32, 33, 34, 35, 36 } };
		System.out.println("Matrix before");
		printArray(mat);
		zeroMat(mat);
		System.out.println("Matrix after");
		printArray(mat);
		System.out.println("\n\n Optimisied Algorithm");
		
		int[][] mat2 = { { 0, 98, 97, 96, 95, 94 }, { 89, 88, 87, 86, 85, 84 }, { 13, 14, 15, 16, 17, 18 },
				{ 19, 20, 21, 0, 23, 24 }, { 25, 26, 27, 28, 29, 30 }, { 31, 32, 33, 34, 35, 36 } };
		System.out.println("Matrix before");
		printArray(mat2);
		optimizedZeroMat(mat2);
		System.out.println("Matrix after");
		printArray(mat2);

	}

	public static void printArray(int[][] mat) {
		for (int i = 0; i < mat.length; i++)
			System.out.println(Arrays.toString(mat[i]));
	}
}
