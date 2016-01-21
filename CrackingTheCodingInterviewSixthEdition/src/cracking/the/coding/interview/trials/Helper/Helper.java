package cracking.the.coding.interview.trials.Helper;

public class Helper {

	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}

	public static boolean randomBoolean() {
		return randomIntInRange(0, 1) == 0;
	}

	public static boolean randomBoolean(int percentTrue) {
		return randomIntInRange(1, 100) <= percentTrue;
	}

	public static boolean[][] randomBooleanMatrix(int M, int N, int percentTrue) {
		boolean[][] matrix = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = randomBoolean(percentTrue);
			}
		}
		return matrix;
	}

	public static int[][] randomMatrix(int M, int N, int min, int max) {
		int[][] matrix = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = randomIntInRange(min, max);
			}
		}
		return matrix;
	}

	public static int[] randomArray(int N, int min, int max) {
		int[] array = new int[N];
		for (int j = 0; j < N; j++) {
			array[j] = randomIntInRange(min, max);
		}
		return array;
	}
}
