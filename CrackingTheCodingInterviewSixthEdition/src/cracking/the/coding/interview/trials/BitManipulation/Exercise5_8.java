package cracking.the.coding.interview.trials.BitManipulation;

public class Exercise5_8 {

	public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {

		int startOffset = x1 % 8;
		int firstByte = x1 / 8;

		if (startOffset != 0) {
			firstByte++;
		}

		int endOffset = x2 % 8;
		int endByte = x2 / 8;

		if (endOffset != 7) {
			endByte--;
		}

		for (int b = firstByte; b <= endByte; b++) {
			screen[(width / 8) * y + b] = (byte) 0xFF;
		}

		byte startMask = (byte) (0xFF >> startOffset);
		byte endMask = (byte) ~(0xFF >> endOffset + 1);

		// if x1 and x2 belong to the same byte
		if ((x1 / 8) == (x2 / 8)) {
			byte mask = (byte) (startMask & endMask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			// set first x1 bits
			if (startOffset != 0) {
				screen[(width / 8) * y + firstByte - 1] |= startMask;
			}
			// set last x2 bits
			if (endOffset != 7) {
				screen[(width / 8) * y + endByte + 1] |= endMask;
			}
		}
	}
}
