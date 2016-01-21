package cracking.the.coding.interview.trials.MathAndLogicPuzzles;

/**
 * The most optimal solution can be obtained in 7 days. Since there are 1000
 * bottles and 10 strips. Total number of binary representations of 2^10 = 1024.
 * Hence, these 10 strips can be used to represent 1000 bottles. The solution
 * would be to convert each bottle number to their binary representation and
 * label the strips from 0 to 9. For each binary representation; if there is a
 * 1s bit at ith location, add a drop to strip i. At the end of the 7 days, take
 * all the strips that have turned positive. And generate a binary number by
 * adding a 1 to ith location from strip i. This binary number will identify the
 * poisoned bottle.
 */

class Bottle {
	int id;

	public Bottle() {
	}

	public Bottle(int id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

class TestSim {
	int noOfStrips = 10;
	int noOfBottles = 1000;
	Bottle[] bottles = new Bottle[noOfBottles];
	boolean[] test = new boolean[noOfStrips];

	public TestSim(int poisonedBottleId) {
		for (int i = 0; i < noOfBottles; i++) {
			int bottleId = i + 1;
			bottles[i] = new Bottle(bottleId);

			int stripId = 0;

			// only the poisoned bottle will turn the test strip
			// positive
			if (bottleId == poisonedBottleId) {
				while (bottleId > 0) {
					if ((bottleId & 1) == 1) {
						test[stripId] = true;
					}
					stripId++;
					bottleId >>= 1;
				}
			}
		}

		int identifiedBottleId = 0;
		for (int i = 0; i < test.length; i++) {
			if (test[i]) {
				identifiedBottleId |= 1 << i;
			}
		}

		if (identifiedBottleId == poisonedBottleId) {
			System.out.println("The poisoned bottle is " + poisonedBottleId);
		}
	}

}

public class Exercise6_10 {

	public static void main(String[] args) {
		new TestSim(838);
	}

}
