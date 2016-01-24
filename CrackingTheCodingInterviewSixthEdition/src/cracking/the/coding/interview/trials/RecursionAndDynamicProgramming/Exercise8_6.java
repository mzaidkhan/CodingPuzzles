package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.Stack;

class Tower {
	Stack<String> stack;
	int towerIdx;

	public Tower(int towerIdx) {
		stack = new Stack<String>();
		this.towerIdx = towerIdx;
	}

	public void add(String item) {
		this.stack.push(item);
	}

	public String removeTopItem() {
		if (!stack.isEmpty()) {
			return this.stack.pop();
		}
		return null;
	}

	public String peek() {
		return this.stack.peek();
	}

	public void addToTopFrom(Tower from) {
		System.out
				.println("Moving Disk " + from.peek() + " from Tower " + from.towerIdx + " to Tower " + this.towerIdx);
		this.add(from.removeTopItem());
	}

	@Override
	public String toString() {
		return "Tower [stack=" + stack + ", towerIdx=" + towerIdx + "]";
	}

}

public class Exercise8_6 {

	// Time and space complexity O(2^N + 1) using implicit stack
	public static void towerOfHanoi(int top, char from, char intermediate, char to) {
		if (top > 0) {
			towerOfHanoi(top - 1, from, to, intermediate);
			System.out.println("Moving Disk " + top + " from " + from + " to " + to);
			towerOfHanoi(top - 1, intermediate, from, to);
		}
	}

	// Time and space complexity O(2^N + 1) using explicit stack for towers
	public static void towerOfHanoi(int top, Tower from, Tower intermediate, Tower to) {
		if (top > 0) {
			towerOfHanoi(top - 1, from, to, intermediate);
			to.addToTopFrom(from);
			towerOfHanoi(top - 1, intermediate, from, to);
		}

	}

	public static void main(String[] args) {
		int disks = 3;
		towerOfHanoi(disks, 'A', 'B', 'C');

		int noOfTowers = 3;
		Tower[] towers = new Tower[noOfTowers];
		for (int i = 0; i < noOfTowers; i++) {
			towers[i] = new Tower(i);
		}

		// add the disks to the first tower
		for (int i = disks; i > 0; --i) {
			towers[0].add("Disk " + i);
		}

		System.out.println("\nUsing Explicit stacks\nBefore: " + towers[0]);
		towerOfHanoi(disks, towers[0], towers[1], towers[2]);
		System.out.println("After: " + towers[0]);
		System.out.println("After: " + towers[1]);
		System.out.println("After: " + towers[2]);
	}

}
