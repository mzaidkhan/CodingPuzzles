package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.HashMap;

class PathWithSumsBruteForce {

	// This Algorithm has a time complexity of O(N Log N) is balanced BST
	// Otherwise it has a time complexity of O(N^2)
	public static int countOfPathWithSums(TreeNode root, int totalSum) {
		// Base case: we have either reached the end of the tree or root is null
		if (root == null) {
			return 0;
		}

		// Get the count of all paths with a sum equal to totalSum from the root
		int noOfPathsFromRoot = getCountOfPathWithSums(root, totalSum, 0);

		// Get the count of all paths with a sum equal to totalSum in the
		// leftSubTree
		int noOfPathsFromLeftSubTree = countOfPathWithSums(root.left, totalSum);

		// Get the count of all paths with a sum equal to totalSum in the
		// rightSubTree
		int noOfPathsFromRightSubTree = countOfPathWithSums(root.right, totalSum);

		// Return sum of all the counts from root, left and right subTrees
		return noOfPathsFromRoot + noOfPathsFromLeftSubTree + noOfPathsFromRightSubTree;
	}

	private static int getCountOfPathWithSums(TreeNode node, int totalSum, int currentCount) {
		// Base case
		if (node == null) {
			return 0;
		}

		// Add the node value to the currentCount
		currentCount += node.val;

		int totalCounts = 0;

		// If currentCount is equal to totalSum, a path was found. Hence,
		// increment totalCounts
		if (currentCount == totalSum) {
			totalCounts++;
		}

		// Find if there are paths that sum up to totalSum in leftSubTree
		totalCounts += getCountOfPathWithSums(node.left, totalSum, currentCount);

		// Find if there are paths that sum up to totalSum in rightSubTree
		totalCounts += getCountOfPathWithSums(node.right, totalSum, currentCount);
		return totalCounts;
	}

}

class PathWithSumsOptimized {

	public static int countOfPathWithSums(TreeNode root, int targetSum) {
		return getCountOfPathsWithSums(root, new HashMap<Integer, Integer>(), targetSum, 0);
	}

	private static int getCountOfPathsWithSums(TreeNode node, HashMap<Integer, Integer> pathCounter, int targetSum,
			int runningSum) {

		// Base case
		if (node == null) {
			return 0;
		}

		runningSum += node.val;

		/* Count paths with sum ending at the current node. */
		int sum = runningSum - targetSum;
		int totalPaths = pathCounter.getOrDefault(sum, 0);

		/*
		 * If runningSum equals targetSum, then one additional path starts at
		 * root, in which case add in this path.
		 */
		if (runningSum == targetSum) {
			totalPaths++;
		}

		/* Add runningSum to pathCounts. */
		incrementValInHashMap(pathCounter, runningSum, 1);

		/* Count paths with sum on the left and right. */
		totalPaths += getCountOfPathsWithSums(node.left, pathCounter, targetSum, runningSum);
		totalPaths += getCountOfPathsWithSums(node.right, pathCounter, targetSum, runningSum);

		incrementValInHashMap(pathCounter, runningSum, -1); // Remove runningSum
		return totalPaths;
	}

	private static void incrementValInHashMap(HashMap<Integer, Integer> pathCounter, int key, int value) {
		int newCount = pathCounter.getOrDefault(key, 0) + value;
		if (newCount == 0) {
			// Remove when zero to reduce space usage
			pathCounter.remove(key);
		} else {
			pathCounter.put(key, newCount);
		}
	}
}

public class Exercise4_12 {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(10);
		TreeNode a = new TreeNode(5);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(2);
		TreeNode e = new TreeNode(3);
		TreeNode f = new TreeNode(-2);
		TreeNode g = new TreeNode(1);

		TreeNode b = new TreeNode(-3);
		TreeNode h = new TreeNode(11);

		root.left = a;
		a.left = c;
		a.right = d;
		c.left = e;
		c.right = f;
		d.right = g;
		root.right = b;
		b.right = h;

		System.out.println(
				"(Brute Force) The total no. of paths: " + PathWithSumsBruteForce.countOfPathWithSums(root, 8));
		System.out.println("(Optimized) The total no. of paths: " + PathWithSumsOptimized.countOfPathWithSums(root, 8));

	}

}
