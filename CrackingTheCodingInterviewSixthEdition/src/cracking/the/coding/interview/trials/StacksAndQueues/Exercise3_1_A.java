package cracking.the.coding.interview.trials.StacksAndQueues;

class MultiStack<T> {

	public T[] array;
	public int[] size;

	private int totalCapOfEachStack;

	@SuppressWarnings("unchecked")
	public MultiStack(int noOfStacks, int capacity) {
		array = (T[]) new Object[capacity];
		size = new int[noOfStacks];
		totalCapOfEachStack = capacity / noOfStacks;
	}

	public boolean isEmpty(int fromStack) {
		if (size[fromStack] == 0) {
			return true;
		}
		return false;
	}

	public T peek(int fromStack) {
		if (isEmpty(fromStack - 1)) {
			System.out.println("This stack is empty");
			return null;
		} else {
			int offset = calcOffset(fromStack - 1);
			return (T) array[size[fromStack - 1] + offset];
		}
	}

	public void push(int fromStack, T value) {
		if (size[fromStack - 1] == totalCapOfEachStack) {
			System.out.println("Stack " + fromStack + " is full.");
			return;
		}
		int offset = calcOffset(fromStack - 1);
		array[++size[fromStack - 1] + offset] = value;
	}

	private int calcOffset(int fromStack) {
		return fromStack * totalCapOfEachStack;
	}

	public T pop(int fromStack) {
		if (isEmpty(fromStack - 1)) {
			System.out.println("Stack " + fromStack + " is empty.");
			return null;
		}
		int offset = calcOffset(fromStack - 1);
		return array[size[fromStack - 1]-- + offset];
	}

	public void printStackContents() {
		for (int i = 1; i <= size.length; i++) {
			System.out.println("The contents of Stack " + i + " are:");
			System.out.print("First->");
			for (int j = 0; j <= size[i - 1]; j++) {
				System.out.print(array[calcOffset(i - 1) + j] + "->");
			}
			System.out.println("Last");
		}
	}

}

public class Exercise3_1_A {

	public static void main(String[] args) throws Exception {

		MultiStack<Integer> mStacks = new MultiStack<Integer>(3, 100);

		for (int i = 0; i <= 100 / 3; i++) {
			mStacks.push(1, 100 + i);
			mStacks.push(2, 200 + i);
			mStacks.push(3, 300 + i);
		}

		mStacks.printStackContents();

		mStacks.push(1, 199);
		mStacks.push(2, 299);
		mStacks.push(3, 399);

		System.out.println("Peeking value from Stack 1: " + mStacks.peek(1));

		mStacks.printStackContents();

		System.out.println("Poping value from Stack 2: " + mStacks.pop(2));
		mStacks.printStackContents();

		for (int i = 0; i < 100 / 3; i++) {
			mStacks.pop(1);
			mStacks.pop(2);
			mStacks.pop(3);
		}
		mStacks.printStackContents();
	}

}
