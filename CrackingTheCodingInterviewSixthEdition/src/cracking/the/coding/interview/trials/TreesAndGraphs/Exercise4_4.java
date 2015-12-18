package cracking.the.coding.interview.trials.TreesAndGraphs;

class CheckBalanced {

	public static Integer getHeightOfBinaryTree(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return Math.max(getHeightOfBinaryTree(root.getLeft()), getHeightOfBinaryTree(root.getRight())) + 1;
	}

	// Time complexity of O(N log N) and due to recursive additional space of
	// O(N)
	public static boolean isBalanced(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return true;
		}

		int heightOfLeftSubTree = getHeightOfBinaryTree(root.getLeft());
		int heightOfRightSubTree = getHeightOfBinaryTree(root.getRight());

		int diff = Math.abs(heightOfLeftSubTree - heightOfRightSubTree);

		if (diff > 1) {
			return false;
		} else {
			return isBalanced(root.getLeft()) && isBalanced(root.getRight());
		}
	}

	public static int checkHeightOfBinaryTree(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}

		int heightOfLeftSubTree = checkHeightOfBinaryTree(root.getLeft());

		if (heightOfLeftSubTree == -1) {
			return -1;
		}

		int heightOfRightSubTree = checkHeightOfBinaryTree(root.getRight());

		if (heightOfRightSubTree == -1) {
			return -1;
		}

		int heightDifference = Math.abs(heightOfLeftSubTree - heightOfRightSubTree);

		if (heightDifference > 1) {
			return -1;
		} else {
			return Math.max(heightOfLeftSubTree, heightOfRightSubTree) + 1;
		}
	}

	// Time complexity of O(N) and Space complexity of O(H) where H is the
	// height of the Binary Tree
	public static boolean isBalancedOptimised(BinaryTreeNode<Integer> root) {
		if (checkHeightOfBinaryTree(root) == -1) {
			return false;
		} else {
			return true;
		}
	}
}

public class Exercise4_4 {

	public static void main(String[] args) {

		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		BinaryTreeNode<Integer> root = MinimalTree.createMinTree(arr, 0, arr.length - 1);

		arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16 };
		BinaryTreeNode<Integer> root2 = MinimalTree.createMinTree(arr, 0, arr.length - 1);

		System.out.println("Height of the tree is " + CheckBalanced.getHeightOfBinaryTree(root));
		System.out.println("Root is balanced " + CheckBalanced.isBalanced(root));
		System.out.println("Height of the tree is " + CheckBalanced.getHeightOfBinaryTree(root2));
		System.out.println("Root is balanced " + CheckBalanced.isBalancedOptimised(root2));
	}

}
