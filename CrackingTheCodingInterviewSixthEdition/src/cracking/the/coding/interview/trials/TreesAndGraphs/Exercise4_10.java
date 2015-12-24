package cracking.the.coding.interview.trials.TreesAndGraphs;

class CheckSubTree {

	// Time complexity O(N*M) and Space complexity of O(Log N + Log M)
	public static boolean checkIfSubTree(BinaryTreeNode<Integer> T1, BinaryTreeNode<Integer> T2) {
		if (T2 == null) {
			// null is part of subTree T1
			return true;
		}
		return checkSubTree(T1, T2);
	}

	private static boolean checkSubTree(BinaryTreeNode<Integer> T1, BinaryTreeNode<Integer> T2) {
		if (T1 == null) {
			// T1 is null we cannot search anymore, hence T2 cannot be it's
			// subTree
			return false;
		} else if (T1.getRoot() == T2.getRoot() && matchTree(T1, T2)) {
			return true;
		}
		// Check T2 in either left or right subTree of T1
		return (checkSubTree(T1.getLeft(), T2) || checkSubTree(T1.getRight(), T2));
	}

	private static boolean matchTree(BinaryTreeNode<Integer> T1, BinaryTreeNode<Integer> T2) {
		if (T1 == null && T2 == null) {
			// End of tree reached. There is nothing to search
			return true;
		} else if (T1 == null || T2 == null) {
			// No more search possible in either of the Trees. Cannot search
			// anymore
			return false;
		} else if (T1.getRoot() != T2.getRoot()) {
			// Node do not match up. Stop search.
			return false;
		} else {
			// Continue checking sub nodes.
			return (matchTree(T1.getLeft(), T2.getLeft()) && matchTree(T1.getRight(), T2.getRight()));
		}
	}
}

public class Exercise4_10 {

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
		b.setRight(e);
		e.setLeft(h);
		e.setRight(i);
		root.setRight(b);
		System.out.println("B is subTree of Root: " + CheckSubTree.checkIfSubTree(root, b));

		BinaryTreeNode<Integer> aRoot = new BinaryTreeNode<Integer>(100);
		aRoot.setLeft(new BinaryTreeNode<Integer>(90));
		aRoot.setRight(new BinaryTreeNode<Integer>(110));

		System.out.println("aRoot is subTree of Root: " + CheckSubTree.checkIfSubTree(root, aRoot));
	}

}
