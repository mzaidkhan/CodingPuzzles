package advent.puzzles.part2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Advent2016_5_2 {
	public static void main(String... args) {
		int times = 0;
		char[] passcode = new char[8];
		Arrays.fill(passcode, ' ');
		for (int i = 0;; i++) {
			if (times >= 8) {
				break;
			}
			String plaintext = "wtnhxymk" + String.valueOf(i);
			MessageDigest m;
			try {
				m = MessageDigest.getInstance("MD5");
				m.reset();
				m.update(plaintext.getBytes());
				byte[] digest = m.digest();
				BigInteger bigInt = new BigInteger(1, digest);
				String hashtext = bigInt.toString(16);
				// Now we need to zero pad it if you actually want the full 32
				// chars.
				while (hashtext.length() < 32) {
					hashtext = "0" + hashtext;
				}
				if (hashtext.startsWith("00000")) {
					int pos = Character.getNumericValue(hashtext.charAt(5));
					if ((pos >= 0 && pos <= 7) && passcode[pos] == ' ') {
						passcode[pos] = hashtext.charAt(6);
						times++;
					}
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Arrays.toString(passcode));
	}
}
