package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;

class BSTSequence {

	public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, LinkedList<Integer> prefix,
			ArrayList<LinkedList<Integer>> results) {

		// Base case when either of the lists are empty.
		// 1. Clone the prefix list to a new list
		// 2. Add all the contents of first or second to this new list
		// 3. Add new list to the results list of lists
		if (first.size() == 0 || second.size() == 0) {
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
			result.addAll(first);
			result.addAll(second);
			results.add(result);
			return;
		}

		// The sub problem can be based on selecting the first element from
		// Either first OR second list

		// Remove first element from first list
		// Append headFirst to the prefix
		prefix.addLast(first.removeFirst());
		// Recursively invoke the weaveList with modified prefix and first lists
		weaveLists(first, second, prefix, results);
		// Reconstruct prefix by removing the last element added
		// add the remove element to the first list. This is required for the
		// subsequent processing
		first.addFirst(prefix.removeLast());

		// Remove first element from the second list
		// Append secondHead to the prefix
		prefix.addLast(second.removeFirst());
		// Recursively invoke weaveLists with modified prefix and second lists
		weaveLists(first, second, prefix, results);
		// Reconstruct prefix by removing the most recently added last element
		// Add the previously removed element to the second list to reconstruct
		// it to the original form prior to recursive invocation
		second.addFirst(prefix.removeLast());
	}

	public static ArrayList<LinkedList<Integer>> getSeqs(BinaryTreeNode<Integer> node) {

		ArrayList<LinkedList<Integer>> results = new ArrayList<LinkedList<Integer>>();

		if (node == null) {
			results.add(new LinkedList<Integer>());
			return results;
		}

		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.getRoot());

		ArrayList<LinkedList<Integer>> leftSeqs = getSeqs(node.getLeft());
		ArrayList<LinkedList<Integer>> rightSeqs = getSeqs(node.getRight());

		for (LinkedList<Integer> left : leftSeqs) {
			for (LinkedList<Integer> right : rightSeqs) {
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
				weaveLists(left, right, prefix, weaved);
				results.addAll(weaved);
			}
		}

		return results;
	}

}

public class Exercise4_9 {

	public static void main(String[] args) {

		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(50);
		BinaryTreeNode<Integer> a = new BinaryTreeNode<Integer>(20);
		BinaryTreeNode<Integer> b = new BinaryTreeNode<Integer>(60);
		BinaryTreeNode<Integer> c = new BinaryTreeNode<Integer>(10);
		BinaryTreeNode<Integer> d = new BinaryTreeNode<Integer>(25);
		BinaryTreeNode<Integer> e = new BinaryTreeNode<Integer>(70);
		BinaryTreeNode<Integer> f = new BinaryTreeNode<Integer>(5);
		BinaryTreeNode<Integer> g = new BinaryTreeNode<Integer>(15);
		BinaryTreeNode<Integer> h = new BinaryTreeNode<Integer>(65);
		BinaryTreeNode<Integer> i = new BinaryTreeNode<Integer>(80);

		root.setLeft(a);
		a.setLeft(c);
		c.setLeft(f);
		c.setRight(g);
		a.setRight(d);
		d.setRight(e);
		e.setLeft(h);
		e.setRight(i);

		root.setRight(b);

		ArrayList<LinkedList<Integer>> results = BSTSequence.getSeqs(root);

		int count = 1;
		for (LinkedList<Integer> linkedList : results) {
			System.out.println("Combination: " + count++ + " = " + linkedList.toString());
		}
	}
}
