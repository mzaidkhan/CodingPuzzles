package advent.puzzles;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Advent2016_5_1 {
	public static void main(String... args) {
		int times = 0;
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
					//System.out.println("Number " + i + " produces hash " + hashtext);
					System.out.print(hashtext.charAt(5));
					times++;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
	}
}
