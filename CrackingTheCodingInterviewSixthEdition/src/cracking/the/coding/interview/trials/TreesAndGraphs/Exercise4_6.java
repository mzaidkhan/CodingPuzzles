package cracking.the.coding.interview.trials.TreesAndGraphs;

class SuccessorNode {

	public static TreeNode getSuccessor(TreeNode node) {

		if (node == null) {
			return null;
		}

		if (node.right != null) {
			return getLeftMostNode(node.right);
		} else {
			TreeNode parent = node.parent;
			
			// if (parent.left == node) {
			// return parent;
			// } else {
			
			// if node is left child, then its parent is the successor
			// else if node is right child, then its grand parent is the
			// successor who has not been visited yet
			while (parent != null && parent.left != node) {
				node = parent;
				parent = parent.parent;
			}
			return parent;
			
			// }
		}
	}

	// This method returns the left most node of tree.
	// If there is no left subtree then it returns the node itself
	public static TreeNode getLeftMostNode(TreeNode node) {
		if (node == null) {
			return null;
		}

		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode parent;

	public TreeNode(int val) {
		this.val = val;
	}
}

public class Exercise4_6 {

	public static void main(String[] args) {
	    /* Sample Binary Search Tree 
	     * 
	     *   		      20
	     *   		     /  \
	     *  		    10   30
	     *  		   /  \ 
	     *  		  5    15 
	     *  		 / \     \
	     *  		3   7     17
	     *
		 * In order is: 3, 5, 7, 10, 15, 17, 20, 30
		 */
		TreeNode a = new TreeNode(20);
		TreeNode b = new TreeNode(10);
		TreeNode c = new TreeNode(30);
		TreeNode d = new TreeNode(5);
		TreeNode e = new TreeNode(15);
		TreeNode f = new TreeNode(3);
		TreeNode g = new TreeNode(7);
		TreeNode h = new TreeNode(17);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		d.left = f;
		d.right = g;
		e.right = h;
		h.parent = e;
		g.parent = d;
		f.parent = d;
		e.parent = b;
		d.parent = b;
		b.parent = a;
		c.parent = a;
		System.out.println("The successor of 10 is " + SuccessorNode.getSuccessor(b).val);
		System.out.println("The successor of 5 is " + SuccessorNode.getSuccessor(d).val);
		System.out.println("The successor of 20 is " + SuccessorNode.getSuccessor(a).val);
		System.out.println("The successor of 17 is " + SuccessorNode.getSuccessor(h).val);
		System.out.println("The successor of 30 is " + SuccessorNode.getSuccessor(c));

	}

}
