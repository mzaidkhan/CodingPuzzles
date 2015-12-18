package cracking.the.coding.interview.trials.LinkedLists;

import java.util.Stack;

public class Exercise2_6 {

	static class Node {
		char data;
		Node next;

		public Node(char data) {
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

		public static boolean isPalindrome(Node head, int len) {

			if (head == null) {
				return false;
			}

			int mid = len / 2;
			Stack<Character> stack = new Stack<Character>();
			for (int i = 1; i <= mid && head != null; i++, head = head.next) {
				stack.push(head.data);
			}

			int startIdx = mid + 1;
			if (len % 2 != 0) {
				startIdx += 1;
				head = head.next;
			}

			for (int i = startIdx; i <= len && head != null; i++, head = head.next) {
				if (stack.pop() != head.data) {
					return false;
				}
			}
			return true;
		}

		public static boolean isPalindrome(Node head) {

			Node slow = head;
			Node fast = head;

			Stack<Character> stack = new Stack<Character>();

			while (fast != null && fast.next != null) {
				stack.push(slow.data);
				slow = slow.next;
				fast = fast.next.next;
			}

			// If the List is Odd then advance one step
			if (fast != null) {
				slow = slow.next;
			}

			while (slow != null) {
				if (stack.pop() != slow.data) {
					return false;
				}
				slow = slow.next;
			}
			return true;
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

		static class Result {
			Node node;
			boolean res;

			public Result(Node node, boolean res) {
				this.node = node;
				this.res = res;
			}
		}

		public static Result rIsPalindrome(Node head, int len) {
			if (head != null && len == 0) {
				return new Result(head, true);
			} else if (head != null && len == 1) {
				return new Result(head.next, true);
			}

			Result res = rIsPalindrome(head.next, len - 2);

			if (res.res != true || res.node == null) {
				res.res = false;
				return res;
			}

			res.res = (res.node.data == head.data);
			res.node = res.node.next;

			return res;
		}
	}

	public static void main(String[] args) {

		String pString = new String("RACECAR").toLowerCase();

		Node head = null;
		for (char ch : pString.toCharArray()) {
			Node tmp = new Node(ch);
			tmp.next = head;
			head = tmp;
		}

		Node.printLinkedList(head);

		System.out.println(pString + " is a Palindrome: " + Node.isPalindrome(head, Node.lengthOfLinkedList(head)));

		System.out.println(pString + " is a Palindrome: " + Node.isPalindrome(head));

		System.out
				.println(pString + " is a Palindrome: " + Node.rIsPalindrome(head, Node.lengthOfLinkedList(head)).res);

	}

}
