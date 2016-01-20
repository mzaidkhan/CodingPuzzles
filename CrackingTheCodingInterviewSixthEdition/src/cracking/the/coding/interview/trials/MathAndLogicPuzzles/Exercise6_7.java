package cracking.the.coding.interview.trials.MathAndLogicPuzzles;

import java.util.Random;

/**
 * The policy enforced by the queen does not have ANY effect on gender
 * inequality between boys and girls. Since the probability of having a boy or
 * girl is 0.5. There is a chance that the first child is a girl and the family
 * stops having kids. Or, they have a sequence of boys(1 or more) followed by a
 * girl. Therefore, there are zero or more boys until eventually a girl is born
 * into the family. When we group such strings together of births from various
 * families, eventually the gender equality is almost restored. We can simulate
 * this case using a computer program.
 */

class GenderSimulation {

	public static double ratioOfBoysVsGirls(int n) {
		double boys = 0;
		double girls = 0;

		for (int i = 0; i < n; i++) {
			int[] gender = runSimOnOneFamily();
			girls += gender[0];
			boys += gender[1];
		}
		return (girls) / (boys + girls);
	}

	public static int[] runSimOnOneFamily() {
		Random random = new Random();
		int girl = 0;
		int boys = 0;

		while (girl == 0) {
			boolean female = random.nextBoolean();
			if (female) {
				girl++;
			} else {
				boys++;
			}
		}

		int[] gender = { girl, boys };
		return gender;
	}

}

public class Exercise6_7 {

	public static void main(String[] args) {
		int n = 1000000;
		System.out.println(
				"The ratio of girls to boys in a population of " + n + " = " + GenderSimulation.ratioOfBoysVsGirls(n));
	}

}
