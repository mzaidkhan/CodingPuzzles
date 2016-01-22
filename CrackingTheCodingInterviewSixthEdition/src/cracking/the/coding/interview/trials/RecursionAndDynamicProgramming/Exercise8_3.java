package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.Arrays;

import cracking.the.coding.interview.trials.Helper.Helper;

public class Exercise8_3 {

	// Brute force solution for sorted distinct arrays: Time complexity O(N)
	public static int bruteForceMagicIndex(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == i) {
				return i;
			}
		}
		return -1;
	}

	// Brute force with recursion solution for sorted distinct arrays; Time and
	// space complexity 0(N)
	public static int bruteForceMagicIndex(int[] arr, int idx) {
		if (idx > arr.length) {
			return -1;
		} else if (arr[idx] == idx) {
			return idx;
		} else {
			return bruteForceMagicIndex(arr, ++idx);
		}
	}

	// Binary search solution for sorted distinct arrays; Time complexity O(log
	// N)
	public static int binaryMagicIndex(int[] arr, int start, int end) {
		if (start > end) {
			return -1;
		}

		int mid = (start + end) / 2;

		if (arr[mid] == mid) {
			return mid;
		} else if (arr[mid] > mid) {
			return binaryMagicIndex(arr, start, mid - 1);
		} else {
			return binaryMagicIndex(arr, mid + 1, end);
		}
	}

	// Binary solution for non distinct sorted arrays; Time complexity O(log N)
	// In case of non distinct sorted arrays we cannot if we have to look into
	// either left side or right side. Therefore, we search each side until
	// magic index is found
	public static int binaryMagicIndexForNonDistinctArrays(int[] arr, int start, int end) {
		if (start > end) {
			return -1;
		}

		int mid = (start + end) / 2;

		if (arr[mid] == mid) {
			return mid;
		}

		// Take the minimum value between the Array[Middle Index] or
		// (Middle Index -1)
		int leftEndIndex = Math.min(arr[mid], mid - 1);

		// Search left side of array
		int indexFoundInLeftSide = binaryMagicIndexForNonDistinctArrays(arr, start, leftEndIndex);
		// If Magic Index found in left side of array, stop the search.
		if (indexFoundInLeftSide > 0) {
			return indexFoundInLeftSide;
		}

		// Magic Index not found in left side, continue searching in the right side
		return binaryMagicIndexForNonDistinctArrays(arr, mid + 1, end);
	}

	public static void main(String[] args) {
		int[] arr = { -1, 0, 1, 2, 3, 4, 5, 7, 9, 10 };

		System.out.println(Arrays.toString(arr));

		System.out.println("(Brute force) The magic index is " + bruteForceMagicIndex(arr));

		System.out.println("(Recursive Brute force) The magic index is " + bruteForceMagicIndex(arr, 0));

		System.out.println("(Binary search optimized) The magic index is " + binaryMagicIndex(arr, 0, arr.length - 1));
		
		arr = Helper.randomArray(10, 1, 10);
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));

		System.out.println("(Binary search for non distinct sorted arrays) The magic index is "
				+ binaryMagicIndexForNonDistinctArrays(arr, 0, arr.length - 1));

	}

}
