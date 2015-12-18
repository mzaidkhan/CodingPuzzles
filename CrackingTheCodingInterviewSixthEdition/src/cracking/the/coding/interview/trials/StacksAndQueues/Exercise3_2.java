package cracking.the.coding.interview.trials.StacksAndQueues;

import java.util.ArrayList;
import java.util.List;

class MyStack<T> {

	private List<T> list;

	private List<T> minList;

	public MyStack() {
		list = new ArrayList<T>();
		minList = new ArrayList<T>();
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	public boolean isMinEmpty() {
		if (minList.size() == 0) {
			return true;
		}
		return false;
	}

	public T peek() {
		if (!isEmpty()) {
			return list.get(list.size() - 1);
		}
		return null;
	}

	public T peekMinElement() {
		if (!isMinEmpty()) {
			return minList.get(minList.size() - 1);
		}
		return null;
	}

	private T popMinElement() {
		if (!isMinEmpty()) {
			System.out.println("Popping the element " + peekMinElement());
			return minList.remove(minList.size() - 1);
		}
		return null;
	}

	public void push(T data) {
		if (minList.isEmpty() || ((Comparable<T>) data).compareTo((T) peekMinElement()) < 0) {
			minList.add(data);
		}
		// This is utilizing unnecessary space
		// else {
		// minList.add(peekMinElement());
		// }
		list.add(data);
	}

	public T pop() {
		if (!isEmpty()) {
			T ret = list.remove(list.size() - 1);
			if (ret.equals((T) peekMinElement())) {
				popMinElement();
			}
			return ret;
		}
		return null;
	}

	public void printMyStack() {
		printMyStack(list);
		System.out.println("The minimum element is: " + peekMinElement());
		printMyStack(minList);
		System.out.println("");
	}

	public void printMyStack(List<T> stc) {
		System.out.print("The contents of the stack are: First->");
		for (T t : stc) {
			System.out.print(t + "->");
		}
		System.out.println("Last\nSize of the stack is: " + this.size());
	}

}

public class Exercise3_2 {

	public static void main(String[] args) {

		MyStack<Integer> myStack = new MyStack<Integer>();

		for (int i = 0; i < 50; i++) {
			myStack.push((int) (Math.random() * 1000));
		}
		myStack.printMyStack();

		for (int i = 0; i < 30; i++) {
			myStack.pop();
		}
		myStack.printMyStack();

		for (int i = 0; i < 50; i++) {
			myStack.push((int) (Math.random() * 1000));
		}
		myStack.printMyStack();

		int size = myStack.size();
		for (int i = 0; i < size; i++) {
			myStack.pop();
		}
		myStack.printMyStack();
	}

}
