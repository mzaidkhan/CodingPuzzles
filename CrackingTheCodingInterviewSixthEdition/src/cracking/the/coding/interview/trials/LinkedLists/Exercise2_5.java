package cracking.the.coding.interview.trials.LinkedLists;

public class Exercise2_5 {

	static class PartialSum {
		int carry;

		public PartialSum(int carry) {
			this.carry = carry;
		}
	}

	static class Node {
		int data;
		Node next;

		public Node() {
		}

		public Node(int data) {
			this.data = data;
		}

		public static void printLinkedList(Node head) {
			System.out.println("The contents of the linked list are:");
			int i = 0;
			while (head != null) {
				System.out.print("Node: " + head.data + " -> ");
				head = head.next;
				i++;
			}
			System.out.println("Null");
			System.out.println("The size of the Linked List is :" + i);
			System.out.println("");
		}

		public static Node addlLengthLinkedListReverseOrder(Node first, Node second, int carry) {

			if (first == null && second == null && carry == 0) {
				return null;
			}

			Node result = new Node();

			if (first != null) {
				carry += first.data;
			}

			if (second != null) {
				carry += second.data;
			}

			result.data = carry % 10;

			if (first != null || second != null) {

				result.next = addlLengthLinkedListReverseOrder(first == null ? null : first.next,
						second == null ? null : second.next, carry > 9 ? 1 : 0);
			}

			return result;
		}

		public static int lengthOfLinkedList(Node head) {
			int len = 0;
			if (head != null) {
				while (head != null) {
					head = head.next;
					len++;
				}
			}
			return len;
		}

		public static int diffLinkedListLengths(Node l1, Node l2) {
			int lenL1 = lengthOfLinkedList(l1);
			int lenL2 = lengthOfLinkedList(l2);
			int diff = (lenL1 - lenL2);
			return diff;
		}

		public static Node paddLinkedList(Node head, int paddLength, int paddVal) {
			while (paddLength > 0) {
				Node node = new Node();
				node.data = paddVal;
				node.next = head;
				head = node;
				paddLength--;
			}
			return head;
		}

		public static Node sumOrderedLinkedList(Node l1, Node l2, int carry) {
			if (l1 == null && l2 == null) {
				return null;
			}
			int diff = diffLinkedListLengths(l1, l2);

			if (diff != 0) {
				if (diff < 0) {
					l1 = paddLinkedList(l1, Math.abs(diff), 0);
				} else {
					l2 = paddLinkedList(l2, diff, 0);
				}
			}

			Node.printLinkedList(l1);
			Node.printLinkedList(l2);

			PartialSum pSum = new PartialSum(carry);

			Node result = recSumOrdLinkedList(l1, l2, pSum);

			if (pSum.carry == 1) {
				result = paddLinkedList(result, 1, pSum.carry);
			}
			return result;

		}

		public static Node recSumOrdLinkedList(Node l1, Node l2, PartialSum pSum) {

			if (l1 == null && l2 == null) {
				return null;
			}

			Node result = new Node();

			if (l1 != null && l2 != null) {
				result.next = recSumOrdLinkedList(l1 == null ? null : l1.next, l2 == null ? null : l2.next, pSum);

				if (l1 != null) {
					pSum.carry += l1.data;
				}
				if (l2 != null) {
					pSum.carry += l2.data;
				}

				result.data = pSum.carry % 10;

				if (pSum.carry > 9) {
					pSum.carry = 1;
				} else {
					pSum.carry = 0;
				}

			}
			return result;
		}
	}

	public static void main(String[] args) {
		Node first = new Node(9);
		Node tmp = first;
		for (int i = 2; i <= 5; i++) {
			int rand = (int) (Math.random() * 9);
			Node n = new Node(rand);
			tmp.next = n;
			tmp = n;
		}

		Node second = new Node(1);
		tmp = second;
		for (int i = 2; i <= 7; i++) {
			int rand = (int) (Math.random() * 9);
			Node n = new Node(rand);
			tmp.next = n;
			tmp = n;
		}

		// Node result = Node.addlLengthLinkedListReverseOrder(first, second,
		// 0);
		//
		// Node.printLinkedList(result);

		Node result2 = Node.sumOrderedLinkedList(first, second, 0);

		Node.printLinkedList(result2);

	}

}
