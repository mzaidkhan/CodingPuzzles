package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Exercise8_4 {

	// Time complexity is O(2^N)
	public static Set<Set<String>> powerSet(ArrayList<String> set) {
		// Computing 2 power of N
		int noOfElem = 1 << set.size();
		Set<Set<String>> powSet = new HashSet<Set<String>>();
		for (int i = 0; i < noOfElem; i++) {
			powSet.add(prepareSet(i, set));
		}
		return powSet;
	}

	private static Set<String> prepareSet(int i, ArrayList<String> set) {
		Set<String> subSet = new HashSet<String>();
		int idx = 0;
		while (i > 0) {
			if ((i & 1) == 1) {
				subSet.add(set.get(idx));
			}
			idx++;
			i >>= 1;
		}
		return subSet;
	}

	// Recursive solution: Time complexity of O(2^N)
	public static Set<Set<String>> powerSetRec(ArrayList<String> set) {
		return powerSetRec(set, 0);
	}

	private static Set<Set<String>> powerSetRec(ArrayList<String> set, int index) {
		Set<Set<String>> powSet;
		if (set.size() == index) {
			powSet = new HashSet<Set<String>>();
			powSet.add(new HashSet<String>());
			return powSet;
		} else {
			powSet = powerSetRec(set, index + 1);
			String item = set.get(index);
			Set<Set<String>> moreSubSets = new HashSet<Set<String>>();
			for (Set<String> subSet : powSet) {
				Set<String> newsubset = new HashSet<String>();
				newsubset.addAll(subSet);
				newsubset.add(item);
				moreSubSets.add(newsubset);
			}
			powSet.addAll(moreSubSets);
		}
		return powSet;
	}

	public static void main(String[] args) {
		ArrayList<String> set = new ArrayList<String>();
		set.add("a1");
		set.add("a2");
		set.add("a3");
		set.add("a4");
		Set<Set<String>> powerSet = powerSet(set);
		System.out.println("Solution using binary representation: " + powerSet);

		powerSet = powerSetRec(set);
		System.out.println("Solution using recursion: " + powerSet);
	}
}
