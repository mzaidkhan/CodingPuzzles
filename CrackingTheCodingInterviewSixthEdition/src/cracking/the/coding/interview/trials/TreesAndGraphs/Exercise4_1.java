package cracking.the.coding.interview.trials.TreesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

class GraphSearch {

	// Based on breadth first search since it finds the shortest path
	public boolean searchRouteBtwNodes(Graph g, Node start, Node end) throws Exception {

		boolean result = false;

		if (g == null || start == null || end == null) {
			throw new Exception("Null values passed.");
		}

		if (start == end) // start.getName().equals(end.getName())
		{
			result = true;
			return result;
		}

		g.clearVisited();

		Queue<Node> queue = new LinkedList<Node>();
		start.setVisited(true);
		queue.add(start);

		while (!queue.isEmpty()) {
			Node v = queue.poll();
			for (Node node : v.getAdjNodes()) {
				if (!node.isVisited()) {
					if (node == end) // node.getName().equals(end.getName())
					{
						result = true;
						break;
					}
					node.setVisited(true);
					queue.add(node);
				}
			}

		}

		g.clearVisited();
		return result;
	}

}

public class Exercise4_1 {

	public static void main(String[] args) {

	}

}
