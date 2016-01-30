package cracking.the.coding.interview.trials.Misc;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;

import cracking.the.coding.interview.trials.Helper.Helper;

/**
 * Given materials of different values per unit volume and maximum amounts, find
 * the most valuable mix of materials which fit in a knapsack of fixed volume.
 * Since we may take pieces (fractions) of materials, a greedy algorithm finds
 * the optimum. Take as much as possible of the material that is most valuable
 * per unit volume. If there is still room, take as much as possible of the next
 * most valuable material. Continue until the knapsack is full.
 * 
 * There are n items in a store. For i =1,2, . . . , n, item i has weight wi > 0
 * and worth vi > 0. Thief can carry a maximum weight of W pounds in a knapsack.
 * In this version of a problem the items can be broken into smaller piece, so
 * the thief may decide to carry only a fraction xi of object i, where 0 ≤ xi ≤
 * 1. Item i contributes xi*wi to the total weight in the knapsack, and xi*vi to
 * the value of the load.
 * 
 * If the items are already sorted into decreasing order of vi / wi, then the
 * while-loop takes a time in O(n); Therefore, the total time including the sort
 * is in O(n log n).
 **/

class Item {
	double value;
	double weight;
	private double valuePerKg;

	public Item(double value, double weight) {
		this.value = value;
		this.weight = weight;
		valuePerKg = (value / weight);
	}

	@Override
	public String toString() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return "Item [value = " + formatter.format(value) + ", weight = " + formatter.format(weight)
				+ " kg, value per kg " + formatter.format(valuePerKg) + "]";
	}

	public double getValuePerKg() {
		return valuePerKg;
	}
}

class ValuePerKgComparator implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		if (o1 == null && o2 == null) {
			return 0;
		} else if (o1 == null && o2 != null) {
			return 1;
		} else if (o2 == null && o1 != null) {
			return -1;
		} else if (o2.getValuePerKg() == o1.getValuePerKg()) {
			return 0;
		} else if (o2.getValuePerKg() > o1.getValuePerKg()) {
			return 1;
		} else {
			return -1;
		}
	}
}

public class FractionalKnapSack {

	public int noOfItems = 10;
	public Item[] items = new Item[noOfItems];
	public double[] take = new double[noOfItems];

	public FractionalKnapSack() {
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(Helper.randomIntInRange(11, 1000), Helper.randomIntInRange(11, 1000));
		}
		System.out.println("The unsorted list of items to pick from are: ");
		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
		Arrays.sort(items, new ValuePerKgComparator());
	}

	public void processFracKnapSack(int capacity) {
		int i;
		for (i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].weight <= capacity) {
				capacity -= items[i].weight;
				take[i] = 1.00;
			} else {
				break;
			}
		}
		if (i < items.length && items[i] != null) {
			take[i] = capacity / items[i].weight;
		}
		printResults();
	}

	private void printResults() {
		System.out.println("\nThe following items with picked:");
		double totalValue = 0, totalWt = 0;
		for (int i = 0; i < take.length; i++) {
			if (take[i] > 0) {
				System.out.printf("	%s with quantity %.4f kg.\n", items[i], take[i]);
				totalValue += items[i].value * take[i];
				totalWt += items[i].weight * take[i];
			}
		}
		System.out.printf("\nOptimal value %.2f and weight %.2f kg", totalValue, totalWt);
	}

	public static void main(String[] args) {
		FractionalKnapSack fKS = new FractionalKnapSack();
		int capacity = 1000;
		System.out.println("\nThe sorted value per kg list of items to pick from are: ");
		for (int i = 0; i < fKS.items.length; i++) {
			System.out.println(fKS.items[i]);
		}
		fKS.processFracKnapSack(capacity);
	}
}