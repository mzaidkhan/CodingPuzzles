package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private List<Node> nodes;

	public Graph() {
		nodes = new ArrayList<Node>();
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	public boolean remove(Node n) {
		if (nodes.contains(n)) {
			return nodes.remove(n);
		}
		return false;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void clearVisited() {
		for (Node node : nodes) {
			node.setVisited(false);
		}
	}

}
