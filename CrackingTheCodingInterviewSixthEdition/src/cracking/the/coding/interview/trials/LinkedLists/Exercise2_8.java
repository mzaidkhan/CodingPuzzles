package cracking.the.coding.interview.trials.LinkedLists;

public class Exercise2_8 {

	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + "]";
		}

		public static void printLinkedList(Node head) {
			System.out.println("The contents of the linked list are:");
			int i = 0;
			while (head != null) {
				System.out.print(head.data + " -> ");
				head = head.next;
				i++;
			}
			System.out.println("Null");
			System.out.println("The size of the Linked List is :" + i);
			System.out.println("");
		}

		public static Node findLoopStart(Node head) {
			if (head == null) {
				return null;
			}

			Node slow = head;
			Node fast = head;

			while (fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;

				if (slow == fast) {
					break; // collision found
				}
			}

			if (fast == null || fast.next == null) {
				return null; // no loop identified
			}

			// Reset either fast or slow to head of the linked list
			fast = head;

			while (slow != fast) {
				slow = slow.next;
				fast = fast.next;
			}
			return slow; // fast
		}
	}

	public static void main(String[] args) {

		Node l = new Node(6);
		System.out.print(l.data + "->");
		Node head = l;

		Node loop = null;
		for (int i = 1; i <= 10; i++) {
			if (i == 6) {
				loop = l;
			}

			Node tmp = new Node((int) (Math.random() * 101));
			l.next = tmp;
			l = l.next;
			System.out.print(l.data + "->");
		}
		l.next = loop;
		System.out.print(loop.data + "\n");

		System.out.println("Loop starts at " + Node.findLoopStart(head));
	}

}
