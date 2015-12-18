package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;

class ListOfDepths {

	public static ArrayList<LinkedList<BinaryTreeNode<Integer>>> listOfDepths(BinaryTreeNode<Integer> root) {

		ArrayList<LinkedList<BinaryTreeNode<Integer>>> lists = new ArrayList<LinkedList<BinaryTreeNode<Integer>>>();
		createListOfDepths(root, lists, 0);
		return lists;
	}

	// Solution with pre-order depth first search algorithm
	private static void createListOfDepths(BinaryTreeNode<Integer> root,
			ArrayList<LinkedList<BinaryTreeNode<Integer>>> lists, int level) {

		if (root == null) {
			return;
		}
		LinkedList<BinaryTreeNode<Integer>> list = null;

		if (lists.size() == level) {
			list = new LinkedList<BinaryTreeNode<Integer>>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}

		list.add(root);
		createListOfDepths(root.getLeft(), lists, level + 1);
		createListOfDepths(root.getRight(), lists, level + 1);
		return;
	}

	// Solution with breadth first search algorithm
	public static ArrayList<LinkedList<BinaryTreeNode<Integer>>> listOfDepths2(BinaryTreeNode<Integer> root) {

		ArrayList<LinkedList<BinaryTreeNode<Integer>>> lists = new ArrayList<LinkedList<BinaryTreeNode<Integer>>>();

		LinkedList<BinaryTreeNode<Integer>> current = new LinkedList<BinaryTreeNode<Integer>>();
		if (root != null) {
			current.add(root);
		}

		while (current.size() > 0) {
			lists.add(current);
			LinkedList<BinaryTreeNode<Integer>> parents = current;
			current = new LinkedList<BinaryTreeNode<Integer>>();
			for (BinaryTreeNode<Integer> parent : parents) {
				if (parent.getLeft() != null) {
					current.add(parent.getLeft());
				}
				if (parent.getRight() != null) {
					current.add(parent.getRight());
				}
			}
		}
		return lists;
	}
}

public class Exercise4_3 {

	public static void main(String[] args) {

		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		BinaryTreeNode<Integer> root = MinimalTree.createMinTree(arr, 0, arr.length - 1);

		ArrayList<LinkedList<BinaryTreeNode<Integer>>> lists = ListOfDepths.listOfDepths(root);
		int i = 1;
		System.out.println("With Depth-First Search......");
		for (LinkedList<BinaryTreeNode<Integer>> linkedList : lists) {
			System.out.print("List " + i++ + " = [start, ");
			for (BinaryTreeNode<Integer> node : linkedList) {
				System.out.print(node.getRoot() + ", ");
			}
			System.out.println(" end]");
		}

		lists = ListOfDepths.listOfDepths2(root);
		i = 1;
		System.out.println("\nWith Breadth-First Search......");
		for (LinkedList<BinaryTreeNode<Integer>> linkedList : lists) {
			System.out.print("List " + i++ + " = [start, ");
			for (BinaryTreeNode<Integer> node : linkedList) {
				System.out.print(node.getRoot() + ", ");
			}
			System.out.println(" end]");
		}

	}

}
