package cracking.the.coding.interview.trials.TreesAndGraphs;

class MinimalTree {

	public static BinaryTreeNode<Integer> createMinTree(int[] arr, int start, int end) {

		if (arr == null || arr.length <= 0 || start > end) {
			return null;
		}

		int mid = (start + end) / 2;

		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(arr[mid]);
		root.setLeft(createMinTree(arr, start, mid - 1));
		root.setRight(createMinTree(arr, mid + 1, end));
		return root;
	}

}

public class Exercise4_2 {

	public static void main(String[] args) {

		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		BinaryTreeNode<Integer> root = MinimalTree.createMinTree(arr, 0, arr.length - 1);
		if (root != null) {
			root.printBinaryTree();
		}
	}
}
