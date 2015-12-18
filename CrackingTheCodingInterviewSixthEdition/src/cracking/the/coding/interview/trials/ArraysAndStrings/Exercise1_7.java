package cracking.the.coding.interview.trials.ArraysAndStrings;

import java.util.Arrays;

public class Exercise1_7 {

	public static void rotateMatrix(int[][] mat) {
		int lenOfMat = mat.length;
		for (int layer = 0; layer < lenOfMat/2; layer++) {
			int firstEle = layer;
			int lastEle = lenOfMat - 1 - layer;
			for (int i = firstEle; i < lastEle; i++) {
				int offset = i - firstEle;
				// Save top
				int tmp = mat[firstEle][i];
				// left -> top
				mat[firstEle][i] = mat[lastEle - offset][firstEle];
				// bottom -> left
				mat[lastEle - offset][firstEle] = mat[lastEle][lastEle - offset];
				// right -> bottom
				mat[lastEle][lastEle - offset] = mat[i][lastEle];
				// top -> right
				mat[i][lastEle] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		int[][] mat = { {99,98,97,96,95,94},
						{89,88,87,86,85,84},
						{13,14,15,16,17,18},
						{19,20,21,22,23,24},
						{25,26,27,28,29,30},
						{31,32,33,34,35,36}};	
		System.out.println("Matrix before rotation");
		printArray(mat);
		rotateMatrix(mat);
		System.out.println("Matrix after rotation");
		printArray(mat);
	}
	
	public static void printArray(int[][] mat){
		for(int i=0; i< mat.length;i++)
			System.out.println(Arrays.toString(mat[i]));
	}
}
