package advent.puzzles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Advent2016_4_1 {
	public static int noOfValidCheckSums = 0, sumOfValidCheckSums = 0;

	public static void main(String... args) throws FileNotFoundException {

		Scanner fileScanner = new Scanner(new File("./src/advent/puzzles/advent2016_4.txt"));

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine().trim();
			checkValidRoom(line.split("-"));
		}
		fileScanner.close();

		System.out.println("No. of Valid CheckSums: " + noOfValidCheckSums);
		System.out.println("Sum of Valid CheckSums: " + sumOfValidCheckSums);
	}

	public static void checkValidRoom(String[] splitLine) {
		Room room = new Room();
		for (int i = 0; i < splitLine.length - 1; i++) {
			char[] chArr = splitLine[i].toCharArray();
			for (int j = 0; j < chArr.length; j++) {
				room.addCount(chArr[j]);
			}
			room.encrypted.append(" ");
		}
		char[] more = splitLine[splitLine.length - 1].toCharArray();
		room.addMoreStuff(more);
		if (room.check()) {
			sumOfValidCheckSums += room.code;
			noOfValidCheckSums++;
		}
	}
}

class Room {
	Alphabet[] alphabetCount = new Alphabet[26];

	StringBuilder encrypted = new StringBuilder();

	StringBuilder decrypted = new StringBuilder();

	public static final Map<Character, Integer> map;

	public static final Map<Integer, Character> cMap;

	char[] givenCheckSum = new char[5];

	char[] checkSum = new char[5];

	int code = 0;

	static {
		map = new HashMap<>();
		map.put('a', 0);
		map.put('b', 1);
		map.put('c', 2);
		map.put('d', 3);
		map.put('e', 4);
		map.put('f', 5);
		map.put('g', 6);
		map.put('h', 7);
		map.put('i', 8);
		map.put('j', 9);
		map.put('k', 10);
		map.put('l', 11);
		map.put('m', 12);
		map.put('n', 13);
		map.put('o', 14);
		map.put('p', 15);
		map.put('q', 16);
		map.put('r', 17);
		map.put('s', 18);
		map.put('t', 19);
		map.put('u', 20);
		map.put('v', 21);
		map.put('w', 22);
		map.put('x', 23);
		map.put('y', 24);
		map.put('z', 25);

		cMap = new HashMap<>();
		cMap.put(0, 'a');
		cMap.put(1, 'b');
		cMap.put(2, 'c');
		cMap.put(3, 'd');
		cMap.put(4, 'e');
		cMap.put(5, 'f');
		cMap.put(6, 'g');
		cMap.put(7, 'h');
		cMap.put(8, 'i');
		cMap.put(9, 'j');
		cMap.put(10, 'k');
		cMap.put(11, 'l');
		cMap.put(12, 'm');
		cMap.put(13, 'n');
		cMap.put(14, 'o');
		cMap.put(15, 'p');
		cMap.put(16, 'q');
		cMap.put(17, 'r');
		cMap.put(18, 's');
		cMap.put(19, 't');
		cMap.put(20, 'u');
		cMap.put(21, 'v');
		cMap.put(22, 'w');
		cMap.put(23, 'x');
		cMap.put(24, 'y');
		cMap.put(25, 'z');
	}

	public Room() {
		for (int i = 0; i < 26; i++) {
			alphabetCount[i] = new Alphabet((char) ('a' + i));
		}
	}

	public void addCount(char ch) {
		alphabetCount[map.get(ch)].addCount();
		encrypted.append(ch);
	}

	public void addMoreStuff(char[] more) {
		StringBuilder strNum = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			strNum.append(more[i]);
		}
		this.code = Integer.parseInt(strNum.toString());
		for (int i = 4; i < 9; i++) {
			givenCheckSum[i - 4] = more[i];
		}
		Arrays.sort(alphabetCount);
		for (int i = 0; i < 5; i++) {
			checkSum[i] = alphabetCount[i].ch;
		}

		for (int i = 0; i < encrypted.length() - 1; i++) {
			Character ch = encrypted.charAt(i);
			if (ch != null && ch != ' ') {
				int kp = map.get(ch);
				// System.out.println(ch + " " + kp);
				int cc = (((kp + code) % 26));
				char dec = cMap.get(cc);
				// System.out.println(cc +" replace " + dec);
				decrypted.append(dec);
			} else {
				decrypted.append(" ");
			}
		}
		// System.out.println(encrypted.toString());
		if ("northpole object storage".equalsIgnoreCase(decrypted.toString())) {
			System.out.println("Encrypted -> " + encrypted.toString());
			System.out.println("Decrypted -> " + decrypted.toString());
			System.out.println("Code " + code);

		}
	}

	public boolean check() {
		for (int i = 0; i < 5; i++) {
			if (givenCheckSum[i] != checkSum[i]) {
				return false;
			}
		}
		return true;
	}
}

class Alphabet implements Comparable {
	char ch;
	private int count = 0;

	public Alphabet(char ch) {
		this.ch = ch;
	}

	public void addCount() {
		count++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ch;
		result = prime * result + count;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Alphabet))
			return false;
		Alphabet other = (Alphabet) obj;
		if (ch != other.ch)
			return false;
		if (count != other.count)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alphabet [ch = " + ch + " count = " + count + "]";
	}

	@Override
	public int compareTo(Object o) {
		Alphabet oth = (Alphabet) o;
		if (o != null) {
			if (this.count == oth.count) {
				if (this.ch == oth.ch) {
					return 0;
				} else if (this.ch > oth.ch) {
					return 1;
				} else {
					return -1;
				}
			} else if (this.count > oth.count) {
				return -1;
			} else {
				return 1;
			}
		}
		return -1;
	}
}