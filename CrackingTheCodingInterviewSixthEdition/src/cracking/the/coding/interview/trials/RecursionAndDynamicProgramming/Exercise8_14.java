package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.HashMap;

// With memoizatoin
class BoolEvaluationWithMemoization {

	public static int noOfWays(boolean result, String expr) {
		// HashMap that serves as a cache
		HashMap<String, Integer> memo = new HashMap<>();
		return noOfWays(result, expr, memo);
	}

	private static int noOfWays(boolean result, String expr, HashMap<String, Integer> memo) {
		// Base case 1
		if (expr.length() == 0) {
			return 0;
		}
		// Base case 2
		if (expr.length() == 1) {
			return charToBooleanEvaluation(expr) == result ? 1 : 0;
		}
		// Base case 3: use the cached results from previous computations if
		// they are available
		if (memo.containsKey(result + expr)) {
			return memo.get(result + expr);
		}

		int noOfWays = 0;
		for (int i = 1; i < expr.length(); i += 2) {

			String leftExpr = expr.substring(0, i);
			char op = expr.charAt(i);
			String rightExpr = expr.substring(i + 1);

			int leftResTrue = noOfWays(true, leftExpr);
			int leftResFalse = noOfWays(false, leftExpr);
			int rightResTrue = noOfWays(true, rightExpr);
			int rightResFalse = noOfWays(false, rightExpr);

			int allPossibilities = (leftResTrue + leftResFalse) * (rightResTrue + rightResFalse);

			int totalTrue;

			switch (op) {
			case '^':
				totalTrue = (leftResTrue * rightResFalse) + (leftResFalse * rightResTrue);
				break;
			case '&':
				totalTrue = (leftResTrue * rightResTrue);
				break;
			case '|':
				totalTrue = (leftResTrue * rightResFalse) + (leftResFalse * rightResTrue)
						+ (leftResTrue * rightResTrue);
				break;
			default:
				totalTrue = 0;
				break;
			}

			// if expected result is true, return totatTrue; Otherwise
			// allPossibilities - totalTrue gives us the all results that are
			// false
			noOfWays += result ? totalTrue : allPossibilities - totalTrue;
		}
		memo.put(result + expr, noOfWays);
		return noOfWays;
	}

	public static boolean charToBooleanEvaluation(String s) {
		return s.equals("1") ? true : false;
	}

}

// Without memoizatoin
class BoolEvaluation {

	public static int noOfWays(boolean result, String expr) {
		// Base case 1
		if (expr.length() == 0) {
			return 0;
		}
		// Base case 2
		if (expr.length() == 1) {
			return charToBooleanEvaluation(expr) == result ? 1 : 0;
		}

		int noOfWays = 0;
		for (int i = 1; i < expr.length(); i += 2) {

			String leftExpr = expr.substring(0, i);
			char op = expr.charAt(i);
			String rightExpr = expr.substring(i + 1);

			int leftResTrue = noOfWays(true, leftExpr);
			int leftResFalse = noOfWays(false, leftExpr);
			int rightResTrue = noOfWays(true, rightExpr);
			int rightResFalse = noOfWays(false, rightExpr);

			int allPossibilities = (leftResTrue + leftResFalse) * (rightResTrue + rightResFalse);

			int totalTrue;

			switch (op) {
			case '^':
				totalTrue = (leftResTrue * rightResFalse) + (leftResFalse * rightResTrue);
				break;
			case '&':
				totalTrue = (leftResTrue * rightResTrue);
				break;
			case '|':
				totalTrue = (leftResTrue * rightResFalse) + (leftResFalse * rightResTrue)
						+ (leftResTrue * rightResTrue);
				break;
			default:
				totalTrue = 0;
				break;
			}

			// if expected result is true, return totatTrue; Otherwise
			// allPossibilities - totalTrue gives us the all results that are
			// false
			noOfWays += result ? totalTrue : allPossibilities - totalTrue;
		}
		return noOfWays;
	}

	public static boolean charToBooleanEvaluation(String s) {
		return s.equals("1") ? true : false;
	}

}

public class Exercise8_14 {

	public static void main(String[] args) {

		String expr = "1^0|0|1";
		boolean result = false;
		System.out.println("No of ways to paranthesized the expr '" + expr + "' to evaluate to " + result + " = "
				+ BoolEvaluation.noOfWays(result, expr));
		expr = "0&0&0&1^1|0";
		result = true;
		System.out.println("No of ways to paranthesized the expr '" + expr + "' to evaluate to " + result + " = "
				+ BoolEvaluation.noOfWays(result, expr));

		expr = "1^0|0|1";
		result = false;
		System.out.println("[With memoization] No of ways to paranthesized the expr '" + expr + "' to evaluate to "
				+ result + " = " + BoolEvaluationWithMemoization.noOfWays(result, expr));
		expr = "0&0&0&1^1|0";
		result = true;
		System.out.println("[With memoization] No of ways to paranthesized the expr '" + expr + "' to evaluate to "
				+ result + " = " + BoolEvaluationWithMemoization.noOfWays(result, expr));
	}
}
