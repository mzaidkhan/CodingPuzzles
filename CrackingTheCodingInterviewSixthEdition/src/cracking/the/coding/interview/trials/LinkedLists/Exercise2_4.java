package cracking.the.coding.interview.trials.LinkedLists;

public class Exercise2_4 {

	public static class Node {
		int data;
		int nodeId;
		Node next;

		public Node(int data) {
			this.data = data;
		}

		public static void printLinkedList(Node head) {
			System.out.println("The contents of the linked list are:");
			int i = 0;
			while (head != null) {
				System.out.println("Node " + head.nodeId + " : " + head.data);
				head = head.next;
				i++;
			}
			System.out.print("Null");
			System.out.println("\n The size of the Linked List is :" + i);
		}

		public static Node partitionLinkedList(Node sNode, int pValue) {
			Node head = sNode;
			Node tail = sNode;

			while (sNode != null) {
				Node next = sNode.next;

				if (sNode.data < pValue) {
					sNode.next = head;
					head = sNode;
				} else {
					tail.next = sNode;
					tail = sNode;
				}

				sNode = next;
			}

			return head;
		}
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.nodeId = 1;
		Node tmp = head;
		for (int i = 2; i <= 100; i++) {
			int rand = (int) (Math.random() * 100);
			Node n = new Node(rand);
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

		Node.printLinkedList(head);
		head = Node.partitionLinkedList(head, 20);
		Node.printLinkedList(head);
	}

}
