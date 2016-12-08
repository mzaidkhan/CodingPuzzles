package advent.puzzles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Advent2016_6_1 {
	public static void main(String... args) throws FileNotFoundException {

		Scanner fileScanner = new Scanner(new File("./src/advent/puzzles/advent2016_6.txt"));

		Code[] codes = new Code[8];
		for (int i = 0; i < 8; i++) {
			codes[i] = new Code();
		}
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine().trim();
			int i = 0;
			for (char ch : line.toCharArray()) {
				codes[i].addCount(ch);
				i++;
			}
		}
		fileScanner.close();
		System.out.print("Part 1 ");
		for (int i = 0; i < 8; i++) {
			Arrays.sort(codes[i].alphabetCount, Collections.reverseOrder());
			// System.out.println(Arrays.toString(codes[i].alphabetCount));
			System.out.print(codes[i].alphabetCount[0].ch);
		}
		System.out.print("\nPart 2 ");
		for (int i = 0; i < 8; i++) {
			// System.out.println(Arrays.toString(codes[i].alphabetCount));
			System.out.print(codes[i].alphabetCount[codes[i].alphabetCount.length - 1].ch);
		}
	}
}

class Code {
	Alphabet2[] alphabetCount = new Alphabet2[26];

	public static final Map<Character, Integer> map;

	public Code() {
		for (int i = 0; i < 26; i++) {
			alphabetCount[i] = new Alphabet2((char) ('a' + i));
		}
	}

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
	}

	public void addCount(char ch) {
		alphabetCount[map.get(ch)].addCount();
	}
}

class Alphabet2 implements Comparable {
	char ch;
	private int count = 0;

	public Alphabet2(char ch) {
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
		if (!(obj instanceof Alphabet2))
			return false;
		Alphabet2 other = (Alphabet2) obj;
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
		Alphabet2 oth = (Alphabet2) o;
		if (o != null) {
			if (this.count == oth.count) {
				if (this.ch == oth.ch) {
					return 0;
				} else if (this.ch > oth.ch) {
					return -1;
				} else {
					return 1;
				}
			} else if (this.count > oth.count) {
				return 1;
			} else {
				return -1;
			}
		}
		return -1;
	}
}