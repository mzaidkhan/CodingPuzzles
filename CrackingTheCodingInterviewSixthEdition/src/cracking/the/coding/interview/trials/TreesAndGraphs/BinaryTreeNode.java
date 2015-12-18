package cracking.the.coding.interview.trials.TreesAndGraphs;

public class BinaryTreeNode<T> {
	private T root;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;

	public BinaryTreeNode(T root) {
		this.root = root;
	}

	public T getRoot() {
		return root;
	}

	public void setRoot(T root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}

	public void printBinaryTree() {
		System.out.println("In order traversal of the binary tree..");
		visit(this);
	}

	private void visit(BinaryTreeNode<T> root) {
		if (root != null) {
			visit(root.left);
			System.out.println(root);
			visit(root.right);
		}
	}

	@Override
	public String toString() {
		return "[" + root + "]";
	}

}
