package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.Random;

class MyTree {

	MyTreeNode root;

	public int size() {
		return root == null ? 0 : root.getSize();
	}

	public void insertInOrder(Integer data) {
		if (root == null) {
			root = new MyTreeNode(data);
		} else {
			root.insertInOrder(data);
		}
	}

	// Time complexity O(Log N) or O(Depth of the Tree)
	public MyTreeNode getRandomNode() {
		if (root == null) {
			return null;
		}
		Random rand = new Random();
		Integer randNode = rand.nextInt(this.size());
		return root.getIthRandomNode(randNode);
	}

	public boolean remove(int value) {
		if (root == null)
			return false;
		else {
			// check if root value is the one to be remove
			if (root.getData() == value) {
				// if root needs to be deleted, dummy root node is created and
				// real root hanged to it as a left child. After real root is
				// deleted, set root link to the link to the left child of the
				// dummy root.
				MyTreeNode auxRoot = new MyTreeNode(0);
				auxRoot.setLeft(root);
				boolean result = root.remove(value, auxRoot);
				root = auxRoot.getLeft();
				return result;
			} else {
				return root.remove(value, null);
			}
		}
	}

}

class MyTreeNode {

	private int data;
	private MyTreeNode left;
	private MyTreeNode right;
	private int size;

	public MyTreeNode(int data) {
		this.data = data;
		size = 1;
	}

	public int getData() {
		return this.data;
	}

	public int getSize() {
		return this == null ? 0 : size;
	}

	public MyTreeNode getLeft() {
		return left;
	}

	public void setLeft(MyTreeNode left) {
		this.left = left;
	}

	public MyTreeNode getRight() {
		return right;
	}

	public void setRight(MyTreeNode right) {
		this.right = right;
	}

	public void insertInOrder(int data) {

		if (data <= this.data) {
			if (left == null) {
				left = new MyTreeNode(data);
			} else {
				left.insertInOrder(data);
			}

		} else {
			if (right == null) {
				right = new MyTreeNode(data);
			} else {
				right.insertInOrder(data);
			}
		}
		size++;
	}

	public MyTreeNode find(int data) {
		if (data == this.data) {
			return this;
		} else if (data <= this.data) {
			return left == null ? null : left.find(data);
		} else if (data > this.data) {
			return right == null ? null : right.find(data);
		}
		return null;
	}

	public MyTreeNode getIthRandomNode(int iThNode) {
		int leftSide = left == null ? 0 : left.getSize();
		if (iThNode < leftSide) {
			return left.getIthRandomNode(iThNode);
		} else if (iThNode == leftSide) {
			return this;
		} else {
			return right.getIthRandomNode(iThNode - (leftSide + 1));
		}
	}

	// Removal based on three cases:
	// 1. Node to be removed has no children
	// (Solution: simple remove that node)
	//
	// 2. Node to be removed has one child
	// (Solution: In this case, node is cut from the tree and algorithm links
	// single child (with it's subtree) directly to the parent of the removed
	// node)
	//
	// 3. Node to be removed has two children
	// (Solution: In this case,
	// 1. find a minimum value in the right subtree;
	// 2. replace value of the node to be removed with found minimum.
	// Now, right subtree contains a duplicate!
	// 3. apply remove to the right subtree to remove a duplicate)

	public boolean remove(int data, MyTreeNode parent) {
		if (data < this.data) {
			if (left != null)
				return left.remove(data, this);
			else
				return false;
		} else if (data > this.data) {
			if (right != null)
				return right.remove(data, this);
			else
				return false;
		} else {
			if (left != null && right != null) {
				this.data = right.minValue();
				right.remove(this.data, this);
			} else if (parent.left == this) {
				parent.left = (left != null) ? left : right;
			} else if (parent.right == this) {
				parent.right = (left != null) ? left : right;
			}
			return true;
		}
	}

	public int minValue() {
		if (left == null)
			return data;
		else
			return left.minValue();
	}
}

public class Exercise4_11 {

	public static void main(String[] args) {
		int[] counts = new int[10];
		for (int i = 0; i < 1000000; i++) {
			MyTree tree = new MyTree();
			int[] array = { 1, 0, 6, 2, 3, 9, 4, 5, 8, 7 };
			for (int x : array) {
				tree.insertInOrder(x);
			}
			int d = tree.getRandomNode().getData();
			counts[d]++;
		}

		System.out.println("Probability check for each random node selection: ");
		for (int i = 0; i < counts.length; i++) {
			System.out.println(i + ": " + counts[i]);
		}

	}

}
