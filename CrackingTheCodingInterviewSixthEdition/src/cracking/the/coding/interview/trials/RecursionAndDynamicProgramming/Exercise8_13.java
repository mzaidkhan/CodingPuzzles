package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Box {
	int width, height, depth;

	public Box(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public boolean isBelow(Box other) {
		if (other == null) {
			return true;
		}
		if (width > other.width && height > other.height && depth > other.depth) {
			return true;
		}
		return false;
	}
}

class BoxComparator implements Comparator<Box> {

	@Override
	public int compare(Box obj1, Box obj2) {
		return obj2.height - obj1.height;
	}
}

// Without memoization version
class StackOfBoxes {

	public static int createMaxHeightStack(List<Box> boxes) {
		// Sort the boxes in descending order
		Collections.sort(boxes, new BoxComparator());
		int maxHeight = 0;
		for (int index = 0; index < boxes.size(); index++) {
			int height = createMaxHeightStack(boxes, index);
			maxHeight = Math.max(height, maxHeight);
		}
		return maxHeight;
	}

	private static int createMaxHeightStack(List<Box> boxes, int bIndex) {
		Box bottomBox = boxes.get(bIndex);
		int maxHeight = 0;
		for (int idx = bIndex + 1; idx < boxes.size(); idx++) {
			if (bottomBox.isBelow(boxes.get(idx))) {
				int height = createMaxHeightStack(boxes, idx);
				maxHeight = Math.max(height, maxHeight);
			}
		}
		maxHeight += bottomBox.height;
		return maxHeight;
	}
}

// With memoization version
class StackOfBoxesWithMemoization {

	public static int createMaxHeightStack(List<Box> boxes) {
		// Sort the boxes in descending order
		Collections.sort(boxes, new BoxComparator());
		int[] stackMap = new int[boxes.size()];
		int maxHeight = 0;
		for (int index = 0; index < boxes.size(); index++) {
			int height = createMaxHeightStack(boxes, index, stackMap);
			maxHeight = Math.max(height, maxHeight);
		}
		return maxHeight;
	}

	private static int createMaxHeightStack(List<Box> boxes, int bIndex, int[] stackMap) {
		if (bIndex < boxes.size() && stackMap[bIndex] > 0) {
			return stackMap[bIndex];
		}
		Box bottomBox = boxes.get(bIndex);
		int maxHeight = 0;
		for (int idx = bIndex + 1; idx < boxes.size(); idx++) {
			if (bottomBox.isBelow(boxes.get(idx))) {
				int height = stackMap[idx];
				height = createMaxHeightStack(boxes, idx, stackMap);
				maxHeight = Math.max(height, maxHeight);
			}
		}
		maxHeight += bottomBox.height;
		stackMap[bIndex] = maxHeight;
		return maxHeight;
	}
}

// With memoization. And also taking the decision of either choosing the bottom
// box and proceeding with calculation or proceeding with calculation without
// the bottom box
class StackOfBoxesAlternativeSoln {

	public static int createMaxHeightStack(List<Box> boxes) {
		Collections.sort(boxes, new BoxComparator());
		int[] stackMap = new int[boxes.size()];
		return createMaxHeightStack(boxes, null, 0, stackMap);
	}

	public static int createMaxHeightStack(List<Box> boxes, Box bottomBox, int offsetIdx, int[] stackMap) {

		if (offsetIdx >= boxes.size()) {
			return 0;
		}

		Box newTop = boxes.get(offsetIdx);
		int heightWithBox = 0;
		if (bottomBox == null || bottomBox.isBelow(newTop)) {
			if (stackMap[offsetIdx] == 0) {
				stackMap[offsetIdx] = createMaxHeightStack(boxes, newTop, offsetIdx + 1, stackMap);
				stackMap[offsetIdx] += newTop.height;
			}
			heightWithBox = stackMap[offsetIdx];
		}

		int heightWithOutBox = createMaxHeightStack(boxes, bottomBox, offsetIdx + 1, stackMap);
		return Math.max(heightWithBox, heightWithOutBox);
	}
}

public class Exercise8_13 {

	public static void main(String... args) {

		List<Box> boxes = new ArrayList<Box>();
		boxes.add(new Box(6, 4, 4));
		boxes.add(new Box(8, 6, 2));
		boxes.add(new Box(10, 10, 10));
		boxes.add(new Box(7, 8, 3));
		boxes.add(new Box(4, 2, 2));
		boxes.add(new Box(9, 7, 3));

		// With memoization
		int height = StackOfBoxes.createMaxHeightStack(boxes);
		System.out.println("[Without memoization] Max. height of the stack of boxes is " + height);

		// With memoization
		height = StackOfBoxesWithMemoization.createMaxHeightStack(boxes);
		System.out.println("[With memoization] Max. height of the stack of boxes is " + height);

		// With memoization
		height = StackOfBoxesAlternativeSoln.createMaxHeightStack(boxes);
		System.out.println("[With memoization alternative solutions] Max. height of the stack of boxes is " + height);
	}
}