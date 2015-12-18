package cracking.the.coding.interview.trials.LinkedLists;

public class Exercise2_7 {

	static class Node {

		@Override
		public String toString() {
			return "Node [data=" + data + "]";
		}

		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}

		static class Result {
			Node tail;
			int size;

			public Result(Node tail, int size) {
				this.tail = tail;
				this.size = size;
			}
		}

		public static Result getTailAndSize(Node head) {

			if (head == null)
				return null;

			Result res = null;
			int size = 1;

			while (head.next != null) {
				head = head.next;
				size++;
			}
			res = new Result(head, size);
			return res;
		}

		public static Node advanceLinkedList(Node l, int times) {
			while (l != null && times > 0) {
				l = l.next;
				times--;
			}
			return l;
		}

		public static Node findIntersection(Node l1, Node l2) {

			Result r1 = getTailAndSize(l1);
			Result r2 = getTailAndSize(l2);

			if (r1 == null || r2 == null || r1.tail != r2.tail) {
				return null; // The linked lists do not intersect
			}

			int diff = r1.size - r2.size;

			if (diff < 0) {
				l2 = advanceLinkedList(l2, Math.abs(diff));
			} else if (diff > 0) {
				l1 = advanceLinkedList(l1, diff);
			}

			// Assumption that l1 and l2 iterations are of same length
			while (l1 != l2) {
				l1 = l1.next;
				l2 = l2.next;
			}

			return l1; // or l2
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
	}

	public static void main(String[] args) {

		// intersecting elements
		Node l = new Node(20);
		Node iL = l;

		for (int i = 21; i <= 30; i++) {
			Node tmp = new Node((int) (Math.random() * 100));
			l.next = tmp;
			l = l.next;
		}

		l = new Node(1);
		Node head1 = l;

		for (int i = 1; i < 10; i++) {
			Node tmp = new Node((int) (Math.random() * 5));
			l.next = tmp;
			l = l.next;
		}
		l.next = iL;

		Node.printLinkedList(head1);

		l = new Node(6);
		Node head2 = l;

		for (int i = 7; i < 11; i++) {
			Node tmp = new Node((int) (Math.random() * 10));
			l.next = tmp;
			l = l.next;
		}
		l.next = iL;

		Node.printLinkedList(head2);

		System.out.println("The intersecting node is " + Node.findIntersection(head1, head2));

		System.out.println("The intersecting node is " + Node.findIntersection(head1, null));
		System.out.println("The intersecting node is " + Node.findIntersection(null, head2));
		System.out.println("The intersecting node is " + Node.findIntersection(null, null));

	}

}
