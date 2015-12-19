package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.ArrayList;
import java.util.List;

class CheckBST {

	public static boolean isBST(BinaryTreeNode<Integer> root) {
		return checkBST(root, null, null);
	}

	// Solution 1:
	// All the elements in the left subtree must be <= root
	// And all the elements in the right subtree must be > root
	// Time Complexity 0(N) and Space Complexity O(log N)
	private static boolean checkBST(BinaryTreeNode<Integer> root, Integer min, Integer max) {

		if (root == null) {
			return true; // This is the base case
		}

		if ((min != null && root.getRoot() <= min) || (max != null && root.getRoot() > max)) {
			return false;
		}

		if (!checkBST(root.getLeft(), min, root.getRoot()) || !checkBST(root.getRight(), root.getRoot(), max)) {
			return false;
		}
		return true;
	}

	// Solution 2:
	// This algorithm works only when there are no duplicates in the BST
	// Time Complexity O(N) and Space Complexity 0(N) due to use of an array &
	// recursive inOrderTraversal
	public static boolean isBSTwithoutDuplicates(BinaryTreeNode<Integer> root) {
		List<Integer> arr = new ArrayList<Integer>();
		inOrderTraversal(root, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i - 1) > arr.get(i)) {
				return false;
			}
		}
		return true;
	}

	private static void inOrderTraversal(BinaryTreeNode<Integer> root, List<Integer> arr) {
		if (root != null) {
			inOrderTraversal(root.getLeft(), arr);
			arr.add(root.getRoot());
			inOrderTraversal(root.getRight(), arr);
		}
	}

	// Solution 3:
	// The previous solution required an array which is can be avoided with the
	// following algorithm
	// Time complexity O(N)
	private static Integer lastPrinted;

	public static boolean isBSTWithOutArray(BinaryTreeNode<Integer> root) {
		boolean retVal = isBST2(root);
		// reset lastPrinted for subsequent calls
		lastPrinted = null;
		return retVal;
	}

	private static boolean isBST2(BinaryTreeNode<Integer> root) {

		if (root == null) {
			return true;
		}

		if (!isBST2(root.getLeft())) {
			return false;
		}

		if (lastPrinted != null && root.getRoot() <= lastPrinted) {
			return false;
		}

		lastPrinted = root.getRoot();

		if (!isBST2(root.getRight())) {
			return false;
		}

		return true;
	}
}

public class Exercise4_5 {

	public static void main(String[] args) {

		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		BinaryTreeNode<Integer> root = MinimalTree.createMinTree(arr, 0, arr.length - 1);
		System.out.println("The tree is a BST: " + CheckBST.isBST(root));
		System.out.println("The tree is a BST: " + CheckBST.isBSTWithOutArray(root));
		System.out.println("The tree is a BST: " + CheckBST.isBSTwithoutDuplicates(root) + "\n");

		arr = new int[] { 100, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		root = MinimalTree.createMinTree(arr, 0, arr.length - 1);
		System.out.println("The tree is a BST: " + CheckBST.isBST(root));
		System.out.println("The tree is a BST: " + CheckBST.isBSTWithOutArray(root));
		System.out.println("The tree is a BST: " + CheckBST.isBSTwithoutDuplicates(root) + "\n");

		arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100 };
		root = MinimalTree.createMinTree(arr, 0, arr.length - 1);
		System.out.println("The tree is a BST: " + CheckBST.isBST(root));
		System.out.println("The tree is a BST: " + CheckBST.isBSTWithOutArray(root));
		System.out.println("The tree is a BST: " + CheckBST.isBSTwithoutDuplicates(root));
	}
}
