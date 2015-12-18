package cracking.the.coding.interview.trials.StacksAndQueues;

import java.util.Stack;

class SortedStack {

	Stack<Integer> sortedStack;

	public SortedStack() {
		sortedStack = new Stack<Integer>();
	}

	public void printSortedStack() {
		for (int i = 0; i < 100; i++) {
			Integer rand = (int) (Math.random() * 13 * i);
			sortedStack.push(rand);
		}

		Stack<Integer> sStk = sort();

		int size = sStk.size();
		System.out.print("Top->");
		while (!sStk.isEmpty()) {
			System.out.println(sStk.pop());
		}
		System.out.print("Bottom\n");
		System.out.println("The size of the stack is " + size);
	}

	public Stack<Integer> sort() {
		Stack<Integer> sStk = new Stack<Integer>();
		while (!sortedStack.isEmpty()) {
			int tmp = sortedStack.pop();
			while (!sStk.empty() && tmp > sStk.peek()) {
				sortedStack.push(sStk.pop());
			}
			sStk.push(tmp);
		}
		return sStk;
	}
}

public class Exercise3_5 {

	public static void main(String[] args) {
		SortedStack sStk = new SortedStack();
		sStk.printSortedStack();
	}

}
