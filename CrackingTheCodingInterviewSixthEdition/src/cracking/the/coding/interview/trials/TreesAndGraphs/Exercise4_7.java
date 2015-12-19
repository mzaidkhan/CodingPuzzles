package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class TopoLogicalSort {
	private Iterable<Node> order; // topological order

	public TopoLogicalSort(Graph g) {
		DirectedCycle cyclefinder = new DirectedCycle(g);
		if (!cyclefinder.isCycle()) {
			DepthFirstSeachReverseOrder dfs = new DepthFirstSeachReverseOrder(g);
			order = dfs.getReverseOrder();
		}
	}

	public Iterable<Node> order() {
		return order;
	}

	public boolean isDAG() {
		return order == null;
	}

	public void printOrder() {
		if (!this.isDAG()) {
			System.out.println("The topological sorting order is:");
			for (Node node : order) {
				System.out.println(node.getName());
			}
		} else {
			System.out.println("This graph is cyclic, hence there is no topological sort");
		}
		System.out.println();
	}
}

class DirectedCycle {

	private boolean cycle;
	private Map<Node, Boolean> map;

	public DirectedCycle(Graph g) {
		map = new HashMap<Node, Boolean>();
		g.clearVisited();
		for (Node node : g.getNodes()) {
			if (!node.isVisited()) {
				map.put(node, false);
				dfsWithCycleDetection(g, node);
			}
		}
		g.clearVisited();
	}

	private void dfsWithCycleDetection(Graph g, Node node) {
		map.put(node, true);
		node.setVisited(true);
		for (Node adjNode : node.getAdjNodes()) {
			if (this.isCycle()) {
				return;
			} else if (!adjNode.isVisited()) {
				dfsWithCycleDetection(g, adjNode);
			} else if (map.get(adjNode)) {
				cycle = true;
			}
		}
		map.put(node, false);
	}

	public boolean isCycle() {
		return cycle;
	}
}

class DepthFirstSeachReverseOrder {

	private Stack<Node> reverseOrder = new Stack<Node>();

	public DepthFirstSeachReverseOrder(Graph g) {
		g.clearVisited();
		for (Node node : g.getNodes()) {
			if (!node.isVisited()) {
				dfsReverseOrder(g, node);
			}
		}
		g.clearVisited();
	}

	private void dfsReverseOrder(Graph g, Node node) {
		node.setVisited(true);
		// Add node to a queue if preOrder is required
		for (Node adjNode : node.getAdjNodes()) {
			if (!adjNode.isVisited()) {
				adjNode.setVisited(true);
				dfsReverseOrder(g, adjNode);
			}
		}
		// Add node to a queue if postOrder is required
		reverseOrder.push(node);
	}

	public Stack<Node> getReverseOrder() {
		return reverseOrder;
	}

	public void setReverseOrder(Stack<Node> reverseOrder) {
		this.reverseOrder = reverseOrder;
	}
}

public class Exercise4_7 {

	public static void main(String[] args) {

		// Cyclic Graph
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");

		d.addAdjNode(a);
		b.addAdjNode(f);
		f.addAdjNode(a);
		d.addAdjNode(b);
		a.addAdjNode(f);
		c.addAdjNode(d);

		Graph g = new Graph();
		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addNode(d);
		g.addNode(e);
		g.addNode(f);

		TopoLogicalSort tSort = new TopoLogicalSort(g);
		tSort.printOrder();

		// DAG
		a = new Node("a");
		b = new Node("b");
		c = new Node("c");
		d = new Node("d");
		e = new Node("e");
		f = new Node("f");

		d.addAdjNode(a);
		b.addAdjNode(f);
		d.addAdjNode(b);
		a.addAdjNode(f);
		c.addAdjNode(d);

		g = new Graph();
		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addNode(d);
		g.addNode(e);
		g.addNode(f);

		tSort = new TopoLogicalSort(g);
		tSort.printOrder();
	}
}
