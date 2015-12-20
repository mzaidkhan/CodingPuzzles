package cracking.the.coding.interview.trials.TreesAndGraphs;

class BTreeNode {
	public int data;
	public BTreeNode left;
	public BTreeNode right;

	public BTreeNode(int data) {
		this.data = data;
	}
	
	public void print(){
		System.out.print("In order traversal: ");
		traverse(this);
		System.out.print("End\n");
	}

	private void traverse(BTreeNode node) {
		if(node != null){
			traverse(node.left);
			System.out.printf("%d,", node.data);
			traverse(node.right);
		}
	}
}

class FirstCommonAncestor {

	public static BTreeNode findCommonAncestor(BTreeNode root, BTreeNode p, BTreeNode q) {
		// Error check: check if p and q are part of the Tree
		if (!checkIfTreeContainsTheNode(root, p) || !checkIfTreeContainsTheNode(root, q)) {
			return null;
		}
		return findFirstCommonAncestor(root, p, q);
	}
	
	// The time complexity is O(N)
	public static BTreeNode findFirstCommonAncestor(BTreeNode root, BTreeNode p, BTreeNode q) {

		// End of Tree has been reached, we have not been able to find an
		// ancestor.
		if (root == null) {
			return null;
		}
		// if root is equal to p, then p is the ancestor of q.
		else if (root == p) {
			return p;
		}
		// if root is equal to q, then q is the ancestor of p.
		else if (root == q) {
			return q;
		}

		boolean pIsOnLeftSubTree = checkIfTreeContainsTheNode(root.left, p);
		boolean qIsOnLeftSubTree = checkIfTreeContainsTheNode(root.left, q);

		// The nodes p and q are part of different SubTrees,in which case the
		// ancestor is root
		if (pIsOnLeftSubTree != qIsOnLeftSubTree) {
			return root;
		}

		// Look for the ancestor either on left or right SubTree
		BTreeNode subTree = pIsOnLeftSubTree ? root.left : root.right;
		return findFirstCommonAncestor(subTree, p, q);
	}

	// Time Complexity O(Log N)
	public static boolean checkIfTreeContainsTheNode(BTreeNode root, BTreeNode node) {
		// We have reach the end of the Tree, node is not part of the Tree
		if (root == null) {
			return false;
		}
		// If root is equal to node, node is part of the Tree
		if (root == node) {
			return true;
		}
		// check if node is either on left or right SubTrees
		return checkIfTreeContainsTheNode(root.left, node) || checkIfTreeContainsTheNode(root.right, node);
	}
}

public class Exercise4_8 {

	public static void main(String[] args) {
	    /* Sample Binary Search Tree 
	     * 
	     *   	root ->   20
	     *   		     /  \
	     *  		    10   30
	     *  		   /  \ 
	     *  		  5    15 
	     *  		 / \     \
	     *  		3   7     17
	     *
		 * In order is: 3, 5, 7, 10, 15, 17, 20, 30
		 */
		BTreeNode a = new BTreeNode(20);
		BTreeNode b = new BTreeNode(10);
		BTreeNode c = new BTreeNode(30);
		BTreeNode d = new BTreeNode(5);
		BTreeNode e = new BTreeNode(15);
		BTreeNode f = new BTreeNode(3);
		BTreeNode g = new BTreeNode(7);
		BTreeNode h = new BTreeNode(17);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		e.right = h;
		d.left = f;
		d.right = g;
		BTreeNode ancestor = FirstCommonAncestor.findCommonAncestor(a, g, h);
		a.print();
		System.out.println("First common ancestor of " + g.data + " and " + h.data + " is: " + ancestor.data);
	}

}
