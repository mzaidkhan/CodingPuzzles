package cracking.the.coding.interview.trials.StacksAndQueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class SetOfStacks {

	private List<Stack<Integer>> stacks;
	private int THRESHOLD_STACK_SIZE = 10;

	public SetOfStacks() {
		stacks = new ArrayList<Stack<Integer>>();
	}

	public Integer getLastStackIndex() {
		if (stacks.size() > 0)
			return maxIndexOfStacks();
		else {
			return -1;
		}
	}

	public Stack<Integer> getLastStack() {
		if (stacks.size() > 0) {
			return stacks.get(maxIndexOfStacks());
		}
		return null;
	}

	public int size() {
		int size = 0;
		for (Stack<Integer> stack : stacks) {
			size += stack.size();
		}
		return size;
	}

	public boolean isEmpty() {
		if (size() > 0) {
			return false;
		}
		System.out.println("There is nothing to pop, the stack is empty");
		return true;
	}

	public Integer peek() {
		if (!isEmpty()) {
			Stack<Integer> lastStack = stacks.get(maxIndexOfStacks());
			return (Integer) lastStack.get(lastStack.size() - 1);
		}
		return null;
	}

	public void push(Integer data) {
		Integer lastStack = getLastStackIndex();
		if (lastStack >= 0) {
			if (stacks.get(lastStack).size() == THRESHOLD_STACK_SIZE) {
				addToNewStack(data);
			} else {
				stacks.get(maxIndexOfStacks()).add(data);
			}
		} else {
			addToNewStack(data);
		}
		System.out.println("Pushed this into the stack ->" + data);
	}

	public void printMyStack() {

		System.out.println("The contents of the stack are as follows...");
		System.out.println("The no of sub stacks are:" + stacks.size());
		for (Stack<Integer> stack : stacks) {
			System.out.print("Sub-stack contents...\nFirst->");
			for (Integer value : stack) {
				System.out.print(value + "->");
			}
			System.out.print("Last\n");
			System.out.println("The size is " + stack.size() + "\n");
		}

	}

	private void addToNewStack(Integer data) {
		Stack<Integer> newStack = new Stack<Integer>();
		newStack.add(data);
		stacks.add(newStack);
	}

	public Integer pop() {
		if (!isEmpty()) {
			Stack<Integer> lastStack = getLastStack();
			if (lastStack != null) {
				Integer poppedEle = removeFromTop(lastStack);
				if (lastStack.size() == 0) {
					stacks.remove(lastStack);
				}
				return poppedEle;
			}
		}
		return null;
	}

	public Integer popIndex(int stackIndex) {
		stackIndex -= 1;
		int lastStackIndex = getLastStackIndex();
		if (stackIndex >= 0 && (lastStackIndex >= stackIndex)) {
			if (lastStackIndex == stackIndex) {
				return pop();
			} else {
				Stack<Integer> removeFromThisStack = stacks.get(stackIndex);
				Integer popEle = removeFromTop(removeFromThisStack);

				Integer shiftEle = removeFromBottom(lastStackIndex);
				for (int i = lastStackIndex - 1; i > stackIndex; i--) {
					stacks.get(i).add(shiftEle);
					shiftEle = removeFromBottom(i);
				}
				stacks.get(stackIndex).add(shiftEle);
				return popEle;
			}
		}
		System.out.println("The specified stack index does not exist " + (stackIndex + 1));
		return null;
	}

	private Integer removeFromBottom(int lastStackIndex) {
		return stacks.get(lastStackIndex).remove(0);
	}

	/**
	 * This is a recursive verion of the popAt method
	 * 
	 * @param stackIdx
	 * @return
	 */
	public Integer popAt(Integer stackIdx) {
		return leftShift(stackIdx - 1, true);
	}

	private Integer leftShift(Integer stackIdx, boolean removeTop) {

		if (maxIndexOfStacks() < stackIdx || stackIdx < 0) {
			System.out.println("No elements in the stack pertaining to id: " + (stackIdx + 1));
			return null;
		}

		Integer popEle = null;
		Stack<Integer> curStack = stacks.get(stackIdx);
		if (removeTop) {
			popEle = removeFromTop(curStack);
		} else {
			popEle = removeFromBottom(curStack);
		}
		if (curStack.isEmpty()) {
			stacks.remove(curStack);
		} else if (maxIndexOfStacks() > stackIdx) {
			Integer v = leftShift(stackIdx + 1, false);
			curStack.add(v);
		}
		return popEle;
	}

	private int maxIndexOfStacks() {
		return stacks.size() - 1;
	}

	private Integer removeFromTop(Stack<Integer> curStack) {
		return curStack.remove(curStack.size() - 1);
	}

	private Integer removeFromBottom(Stack<Integer> curStack) {
		return curStack.remove(0);
	}
}

public class Exercise3_3 {

	public static void main(String[] args) {

		SetOfStacks myStacks = new SetOfStacks();

		int times = 50;

		for (int i = 0; i < times; i++) {
			myStacks.push(i);
		}

		myStacks.printMyStack();

		System.out.println("The current top of the stack is: " + myStacks.peek());

		for (int i = 0; i <= times / 10; i++) {

			if (i % 5 == 0) {
				myStacks.push(i);
			}
			myStacks.printMyStack();

			System.out.println("This element has been popped off the stack ->" + myStacks.pop());
			myStacks.printMyStack();

		}
		myStacks.printMyStack();

		int stackIdx = myStacks.getLastStackIndex() > 0 ? 4 : 0;
		System.out.println("Poppin of the stack " + stackIdx + " -> " + myStacks.popIndex(stackIdx) + "\n");

		myStacks.printMyStack();

		stackIdx = myStacks.getLastStackIndex() > 0 ? 4 : 0;
		for (int i = 0; i < 16; i++) {
			System.out.println("Poppin of the stack " + stackIdx + " -> " + myStacks.popAt(stackIdx) + "\n");
		}

		myStacks.printMyStack();

	}

}
