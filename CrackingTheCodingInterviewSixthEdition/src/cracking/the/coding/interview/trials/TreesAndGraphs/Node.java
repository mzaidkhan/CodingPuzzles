package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String name;
	private List<Node> adjNodes;
	
	private boolean visited;

	public Node(String name) {
		this.name = name;
		adjNodes = new ArrayList<Node>();
	}

	public Node(String name, List<Node> adjNodes) {
		this.name = name;
		this.adjNodes = adjNodes;
	}

	public void addAdjNode(Node adjNode) {
		adjNodes.add(adjNode);
	}

	public boolean removeAdjNode(Node adjNode) {
		if (adjNodes.contains(adjNode)) {
			return adjNodes.remove(adjNode);
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public List<Node> getAdjNodes() {
		return adjNodes;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
