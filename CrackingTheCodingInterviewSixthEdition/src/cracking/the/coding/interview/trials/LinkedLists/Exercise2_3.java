package cracking.the.coding.interview.trials.LinkedLists;

public class Exercise2_3 {

	public static class Node {
		int data;
		Node next;
		int nodeId;

		public Node(int data) {
			this.data = data;
		}

		// Just changing reference wont work because doing so will only
		// increment the reference and not the underlying reference. Hence a
		// copy of the underlying data from the next element must be done.
		// This will not work for the last element of the Linked List. A work
		// around would be to have the last element of the Linked List to be a
		// sentinel.
		public static void deleteNode(Node mid) {
			if (mid != null) {
				Node n = mid.next;
				mid.data = n.data;
				mid.nodeId = n.nodeId;
				mid.next = n.next;
			}
		}

		public static void printLinkedList(Node head) {
			System.out.println("The contents of the linked list are:");
			int i = 0;
			while (head != null) {
				System.out.print("Node " + head.nodeId + " : " + head.data + " -> ");
				head = head.next;
				i++;
			}
			System.out.print("Null");
			System.out.println("\n The size of the Linked List is :" + i);
		}
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.nodeId = 1;
		Node tmp = head;
		Node mid = null;
		for (int i = 2; i <= 100; i++) {
			int rand = (int) (Math.random() * 100);
			Node n = new Node(rand);
			if (i == 50) {
				mid = n;
			}
			n.nodeId = i;
			tmp.next = n;
			tmp = n;
		}
		tmp.next = new Node(100);
		tmp.nodeId = 101;
		tmp = tmp.next;

		tmp.next = new Node(100);
		tmp.nodeId = 102;
		tmp = tmp.next;

		tmp.next = new Node(100);
		tmp.nodeId = 103;
		tmp = tmp.next;

		tmp.next = new Node(101);
		tmp.nodeId = 104;
		// Sentinel for the end of the Linked List
		tmp.next = new Node(Integer.MAX_VALUE);

		// checking if possible to delete last elment
		// mid = tmp;

		System.out.println("Middle Node Id: " + mid.nodeId + ", Data: " + mid.data + "\n");
		Node.printLinkedList(head);
		Node.deleteNode(mid);
		System.out.println("\nMiddle Node Id: " + mid.nodeId + ", Data: " + mid.data + "\n");
		Node.printLinkedList(head);

	}

}
