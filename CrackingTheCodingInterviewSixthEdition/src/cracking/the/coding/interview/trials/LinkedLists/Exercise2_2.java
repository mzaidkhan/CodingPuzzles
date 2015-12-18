package cracking.the.coding.interview.trials.LinkedLists;

public class Exercise2_2 {

	public static int SIZE_OF_LINKED_LIST;

	public static class Index {
		int value = 0;
	}

	public static class Node {
		int data;
		Node next;
		int nodeId;

		public Node(int data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + next + "]";
		}

		// If Size of LinkedList is known 0(N) time complexity and brute force
		public static Node findKthLastElement(Node node, int kThElement) {
			Node kThNode = null;

			if (SIZE_OF_LINKED_LIST >= (SIZE_OF_LINKED_LIST - kThElement)) {
				int i = 1;
				for (; i <= (SIZE_OF_LINKED_LIST - kThElement); i++) {
					node = node.next;
				}
				kThNode = node;
				System.out.println(i + "Th Node: data->" + kThNode.data + ".");

			}
			return kThNode;
		}

		// Space O(1) & Time O(N)
		public static Node findKthElement(Node head, int kThEle) throws Exception {
			Node p1 = head;
			Node p2 = head;

			for (int i = 0; i < kThEle; i++) {
				if (p2 != null) {
					p2 = p2.next;
				} else {
					throw new Exception("LinkedList Out of Bounds.");
				}
			}

			while (p2 != null) {
				p1 = p1.next;
				p2 = p2.next;
			}

			System.out.println((SIZE_OF_LINKED_LIST - kThEle + 1) + "Th Node: data->" + p1.data + ".");
			return p1;
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
			SIZE_OF_LINKED_LIST = i;
			System.out.println();
		}

		public static Node recursiveFindKthLastElement(Node head, int kThEle) {
			Index idx = new Index();
			Node kThNode = callRecFindKthLastElement(head, kThEle, idx);
			System.out.println((SIZE_OF_LINKED_LIST - kThEle + 1) + "Th Node: data->" + kThNode.data + ".");
			return kThNode;
		}

		private static Node callRecFindKthLastElement(Node node, int pos, Index index) {
			if (node == null) {
				return null;
			}
			Node tmp = callRecFindKthLastElement(node.next, pos, index);
			index.value = 1 + index.value;
			if (index.value == pos) {
				return node;
			}
			return tmp;
		}

		public static Node recKthFirstEle(Node head, int kThEle) {
			Index idx = new Index();
			idx.value = SIZE_OF_LINKED_LIST + 1;
			Node kThNode = recKthFirstEle(head, kThEle, idx);
			System.out.println(kThEle + "Th Node: data->" + kThNode.data + ".");
			return kThNode;
		}

		public static Node recKthFirstEle(Node head, int kThEle, Index idx) {
			if (head == null) {
				return null;
			}
			Node node = recKthFirstEle(head.next, kThEle, idx);
			--idx.value;
			if (idx.value == kThEle) {
				return head;
			}
			return node;
		}

	}

	public static void main(String[] args) throws Exception {
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
		Node.findKthLastElement(head, 1);
		Node.findKthLastElement(head, 2);
		Node.findKthLastElement(head, 3);
		Node.findKthLastElement(head, 4);
		Node.findKthLastElement(head, 5);
		Node.findKthLastElement(head, 50);
		System.out.println("With recursion....");
		Node.recursiveFindKthLastElement(head, 1);
		Node.recursiveFindKthLastElement(head, 2);
		Node.recursiveFindKthLastElement(head, 3);
		Node.recursiveFindKthLastElement(head, 4);
		Node.recursiveFindKthLastElement(head, 5);
		Node.recursiveFindKthLastElement(head, 50);
		System.out.println("With iterative....");
		Node.findKthElement(head, 1);
		Node.findKthElement(head, 2);
		Node.findKthElement(head, 3);
		Node.findKthElement(head, 4);
		Node.findKthElement(head, 5);
		Node.findKthElement(head, 50);

		System.out.println("Kth First Element....");
		Node.recKthFirstEle(head, 1);
		Node.recKthFirstEle(head, 2);
		Node.recKthFirstEle(head, 3);
		Node.recKthFirstEle(head, 4);
		Node.recKthFirstEle(head, 5);
		Node.recKthFirstEle(head, 50);
	}

}
