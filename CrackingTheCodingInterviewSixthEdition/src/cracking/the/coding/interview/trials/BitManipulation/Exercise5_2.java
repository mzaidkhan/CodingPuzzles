package cracking.the.coding.interview.trials.BitManipulation;

class DoubleToBinary {

	public static String doubleToBinary(double num) {

		if (num >= 1 || num <= 0) {
			System.out.println("Error!");
			return "";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("0.");

		while (num > 0) {

			// Tweaking the problem for 64 bits since Doubles in Java are 64
			// bits (replace 64 with 32 for the original problem).
			if (sb.length() > 64) {
				System.out.println("Error!");
				return "";
			}

			num *= 2;

			if (num >= 1) {
				sb.append("1");
				num -= 1;
			} else {
				sb.append("0");
			}
		}
		return sb.toString();
	}

}

public class Exercise5_2 {

	public static void main(String[] args) {

		double rand = Math.random();
		System.out.println("The random number " + rand + " in binary is: " + DoubleToBinary.doubleToBinary(rand));
	}

}
