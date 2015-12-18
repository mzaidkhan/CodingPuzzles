package cracking.the.coding.interview.trials.LinkedLists;

import java.util.HashSet;
import java.util.Set;

public class Exercise2_1 {

	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}

		public void appendToTheEnd(Node head, int data) {
			Node newNode = new Node(data);
			if (head == null) {
				head = newNode;
			} else {
				Node node = head;
				while (node.next != null) {
					node = node.next;
				}
				node.next = newNode;
			}
		}

		// 0(N) Space and time complexity
		public static void removeDuplicates(Node node) {
			Set<Integer> set = new HashSet<Integer>();
			Node prev = null;
			while (node != null) {
				if (set.contains(node.data)) {
					prev.next = node.next;
				} else {
					set.add(node.data);
					prev = node;
				}
				node = node.next;
			}

		}

		// No temp buffer 0(1) space and 0(N^2) for time
		public static void removeDuplicatesWithoutBuffer(Node node) {
			Node runner = null;
			while (node != null) {
				runner = node;
				while (runner.next != null) {
					if (runner.next.data == node.data) {
						runner.next = runner.next.next;
					} else {
						runner = runner.next;
					}
				}
				node = node.next;
			}

		}

		// With for loops 0(1) space and 0(N^2) time complexities
		public static void removeDuplicatesWithoutBufferForLoops(Node node) {
			for (Node current = node; current != null; current = current.next) {
				for (Node runner = current; runner.next != null;) {
					if (runner.next.data == current.data) {
						runner.next = runner.next.next;
					} else {
						runner = runner.next;
					}
				}
			}
		}

		public static void printLinkedList(Node node) {
			if (node == null) {
				System.out.println("Linked List is empty");
			}

			System.out.println("Contents of the Linked List are:");
			int i = 1;
			System.out.print("Head -> ");
			while (node != null) {
				System.out.print("Node " + i++ + ": " + node.data + " -> ");
				node = node.next;
			}
			System.out.println("TAIL");
			i -= 1;
			System.out.println("No of elements " + i);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Node head = new Node(1);

		Node tmp = head;
		for (int i = 2; i <= 100; i++) {
			int rand = (int) (Math.random() * 100);
			Node n = new Node(rand);
			tmp.next = n;
			tmp = n;
		}
		tmp.next = new Node(100);
		tmp = tmp.next;
		tmp.next = new Node(100);
		tmp = tmp.next;
		tmp.next = new Node(100);
		tmp = tmp.next;
		tmp.next = new Node(101);
		Node.printLinkedList(head);
		// Node.removeDuplicates(head);
		// Node.printLinkedList(head);
		// Node.removeDuplicatesWithoutBuffer(head);
		// Node.printLinkedList(head);
		Node.removeDuplicatesWithoutBufferForLoops(head);
		Node.printLinkedList(head);
	}

}
