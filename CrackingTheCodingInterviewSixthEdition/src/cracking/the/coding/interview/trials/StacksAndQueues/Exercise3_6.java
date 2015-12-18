package cracking.the.coding.interview.trials.StacksAndQueues;

import java.util.LinkedList;

abstract class Animal {

	private int order;
	protected String name;

	public Animal(String name) {
		this.name = name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

	public boolean isOlderThan(Animal other) {
		return this.order < other.order;
	}

}

class Dog extends Animal {
	public Dog(String name) {
		super(name);
	}
}

class Cat extends Animal {
	public Cat(String name) {
		super(name);
	}
}

class AnimalShelter {

	private static int order = 0;
	private LinkedList<Animal> dogList = new LinkedList<Animal>();
	private LinkedList<Animal> catList = new LinkedList<Animal>();

	public void enqueue(Animal ani) {
		ani.setOrder(order);
		order += 1;

		if (ani instanceof Dog) {
			dogList.add(ani);
		} else if (ani instanceof Cat) {
			catList.add(ani);
		}
	}

	public Animal dequeueAny() {
		if (catList.size() == 0) {
			return dequeueDog();
		} else if (dogList.size() == 0) {
			return dequeueCat();
		}

		Animal dog = dogList.peek();
		Animal cat = catList.peek();

		if (dog.isOlderThan(cat)) {
			return dequeueDog();
		} else {
			return dequeueCat();
		}
	}

	public Animal dequeueDog() {
		if (!dogList.isEmpty()) {
			return dogList.poll();
		}
		System.out.println("Yay, all our dogs have found homes. We might have cats who need homes.");
		return null;
	}

	public Animal dequeueCat() {
		if (!catList.isEmpty()) {
			return catList.poll();
		}
		System.out.println("Yay, all our cats have found homes. We might have dogs who need homes.");
		return null;
	}
}

public class Exercise3_6 {

	public static void main(String[] args) {

	}

}
