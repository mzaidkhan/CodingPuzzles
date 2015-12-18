package cracking.the.coding.interview.trials.StacksAndQueues;

import java.util.Stack;

class MyQueue<T> {

	private Stack<T> orgStack;
	private Stack<T> revStack;

	public MyQueue() {
		orgStack = new Stack<T>();
		revStack = new Stack<T>();
	}

	public int size() {
		return orgStack.size() + revStack.size();
	}

	public void enqueue(T item) {
		orgStack.push(item);
	}

	public T dequeue() {
		shiftStacks();
		return revStack.pop();
	}

	private void shiftStacks() {
		if (revStack.isEmpty()) {
			while (!orgStack.isEmpty()) {
				revStack.push(orgStack.pop());
			}
		}
	}

	public T peek() {
		shiftStacks();
		return revStack.peek();
	}

	public void printMyQueue(T addItem) {
		System.out.print("The contents of the queue are...\nFirst->");
		boolean flag = true;
		while (size() > 0) {
			System.out.print(dequeue() + "->");
			if (flag) {
				flag = false;
				enqueue((T) addItem);
			}
		}
		System.out.println("Last");
	}
}

public class Exercise3_4 {

	public static void main(String[] args) {

		MyQueue<Integer> queue = new MyQueue<Integer>();

		for (int i = 1; i < 16; i++) {
			queue.enqueue(i);
		}

		queue.printMyQueue(786);
	}

}
