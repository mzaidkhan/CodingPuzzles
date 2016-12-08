package advent.puzzles;

import java.util.Arrays;

public class Advent2016_2 {
	public static Digit _1 = new Digit(1, null, null, Digits.Two, Digits.Four);
	public static Digit _2 = new Digit(2, Digits.One, null, Digits.Three, Digits.Five);
	public static Digit _3 = new Digit(3, Digits.Two, null, null, Digits.Six);
	public static Digit _4 = new Digit(4, null, Digits.One, Digits.Five, Digits.Seven);
	public static Digit _5 = new Digit(5, Digits.Four, Digits.Two, Digits.Six, Digits.Eight);
	public static Digit _6 = new Digit(6, Digits.Five, Digits.Three, null, Digits.Nine);
	public static Digit _7 = new Digit(7, null, Digits.Four, Digits.Eight, null);
	public static Digit _8 = new Digit(8, Digits.Seven, Digits.Five, Digits.Nine, null);
	public static Digit _9 = new Digit(9, Digits.Eight, Digits.Six, null, null);

	public static int[] passCode = new int[5];

	public static Digit startDigit = _5;

	public static String input = "LURLDDLDULRURDUDLRULRDLLRURDUDRLLRLRURDRULDLRLRRDDULUDULURULLURLURRRLLDURURLLUURDLLDUUDRRDLDLLRUUDURURRULURUURLDLLLUDDUUDRULLRUDURRLRLLDRRUDULLDUUUDLDLRLLRLULDLRLUDLRRULDDDURLUULRDLRULRDURDURUUUDDRRDRRUDULDUUULLLLURRDDUULDRDRLULRRRUUDUURDULDDRLDRDLLDDLRDLDULUDDLULUDRLULRRRRUUUDULULDLUDUUUUDURLUDRDLLDDRULUURDRRRDRLDLLURLULDULRUDRDDUDDLRLRRDUDDRULRULULRDDDDRDLLLRURDDDDRDRUDUDUUDRUDLDULRUULLRRLURRRRUUDRDLDUDDLUDRRURLRDDLUUDUDUUDRLUURURRURDRRRURULUUDUUDURUUURDDDURUDLRLLULRULRDURLLDDULLDULULDDDRUDDDUUDDUDDRRRURRUURRRRURUDRRDLRDUUULLRRRUDD;"
			+ "DLDUDULDLRDLUDDLLRLUUULLDURRUDLLDUDDRDRLRDDUUUURDULDULLRDRURDLULRUURRDLULUDRURDULLDRURUULLDLLUDRLUDRUDRURURUULRDLLDDDLRUDUDLUDURLDDLRRUUURDDDRLUDDDUDDLDUDDUUUUUULLRDRRUDRUDDDLLLDRDUULRLDURLLDURUDDLLURDDLULLDDDRLUDRDDLDLDLRLURRDURRRUDRRDUUDDRLLUDLDRLRDUDLDLRDRUDUUULULUDRRULUDRDRRLLDDRDDDLULURUURULLRRRRRDDRDDRRRDLRDURURRRDDULLUULRULURURDRRUDURDDUURDUURUURUULURUUDULURRDLRRUUDRLLDLDRRRULDRLLRLDUDULRRLDUDDUUURDUDLDDDUDL;"
			+ "RURDRUDUUUUULLLUULDULLLDRUULURLDULULRDDLRLLRURULLLLLLRULLURRDLULLUULRRDURRURLUDLULDLRRULRDLDULLDDRRDLLRURRDULULDRRDDULDURRRUUURUDDURULUUDURUULUDLUURRLDLRDDUUUUURULDRDUDDULULRDRUUURRRDRLURRLUUULRUDRRLUDRDLDUDDRDRRUULLLLDUUUULDULRRRLLRLRLRULDLRURRLRLDLRRDRDRLDRUDDDUUDRLLUUURLRLULURLDRRULRULUDRUUURRUDLDDRRDDURUUULLDDLLDDRUDDDUULUDRDDLULDDDDRULDDDDUUUURRLDUURULRDDRDLLLRRDDURUDRRLDUDULRULDDLDDLDUUUULDLLULUUDDULUUDLRDRUDLURDULUDDRDRDRDDURDLURLULRUURDUDULDDLDDRUULLRDRLRRUURRDDRDUDDLRRLLDRDLUUDRRDDDUUUDLRRLDDDUDRURRDDUULUDLLLRUDDRULRLLLRDLUDUUUUURLRRUDUDDDDLRLLULLUDRDURDDULULRDRDLUDDRLURRLRRULRL;"
			+ "LDUURLLULRUURRDLDRUULRDRDDDRULDLURDDRURULLRUURRLRRLDRURRDRLUDRUUUULLDRLURDRLRUDDRDDDUURRDRRURULLLDRDRDLDUURLDRUULLDRDDRRDRDUUDLURUDDLLUUDDULDDULRDDUUDDDLRLLLULLDLUDRRLDUUDRUUDUDUURULDRRLRRDLRLURDRURURRDURDURRUDLRURURUUDURURUDRURULLLLLUDRUDUDULRLLLRDRLLRLRLRRDULRUUULURLRRLDRRRDRULRUDUURRRRULDDLRULDRRRDLDRLUDLLUDDRURLURURRLRUDLRLLRDLLDRDDLDUDRDLDDRULDDULUDDLLDURDULLDURRURRULLDRLUURURLLUDDRLRRUUDULRRLLRUDRDUURLDDLLURRDLRUURLLDRDLRUULUDURRDULUULDDLUUUDDLRRDRDUDLRUULDDDLDDRUDDD;"
			+ "DRRDRRURURUDDDRULRUDLDLDULRLDURURUUURURLURURDDDDRULUDLDDRDDUDULRUUULRDUDULURLRULRDDLDUDLDLULRULDRRLUDLLLLURUDUDLLDLDRLRUUULRDDLUURDRRDLUDUDRULRRDDRRLDUDLLDLURLRDLRUUDLDULURDDUUDDLRDLUURLDLRLRDLLRUDRDUURDDLDDLURRDDRDRURULURRLRLDURLRRUUUDDUUDRDRULRDLURLDDDRURUDRULDURUUUUDULURUDDDDUURULULDRURRDRDURUUURURLLDRDLDLRDDULDRLLDUDUDDLRLLRLRUUDLUDDULRLDLLRLUUDLLLUUDULRDULDLRRLDDDDUDDRRRDDRDDUDRLLLDLLDLLRDLDRDLUDRRRLDDRLUDLRLDRUURUDURDLRDDULRLDUUUDRLLDRLDLLDLDRRRLLULLUDDDLRUDULDDDLDRRLLRDDLDUULRDLRRLRLLRUUULLRDUDLRURRRUULLULLLRRURLRDULLLRLDUUUDDRLRLUURRLUUUDURLRDURRDUDDUDDRDDRUD;";

	public static void main(String... args) {
		String[] instruction = input.split(";");
		for (int i = 0; i < 5; i++) {
			String input = instruction[i];
			char[] charIp = input.toCharArray();

			for (char ip : charIp) {
				switch (ip) {
				case 'L':
					if (startDigit.lDigit != null) {
						startDigit = findDigit(startDigit.lDigit);
					}
					break;

				case 'U':
					if (startDigit.uDigit != null) {
						startDigit = findDigit(startDigit.uDigit);
					}
					break;
				case 'R':
					if (startDigit.rDigit != null) {
						startDigit = findDigit(startDigit.rDigit);
					}
					break;
				case 'D':
					if (startDigit.dDigit != null) {
						startDigit = findDigit(startDigit.dDigit);
					}
					break;
				}
			}
			passCode[i] = startDigit.getValue();
		}
		System.out.println(Arrays.toString(passCode));
	}

	public static Digit findDigit(Digits find) {
		Digit ret = null;
		switch (find) {
		case One:
			ret = _1;
			break;
		case Two:
			ret = _2;
			break;
		case Three:
			ret = _3;
			break;
		case Four:
			ret = _4;
			break;
		case Five:
			ret = _5;
			break;
		case Six:
			ret = _6;
			break;
		case Seven:
			ret = _7;
			break;
		case Eight:
			ret = _8;
			break;
		case Nine:
			ret = _9;
			break;

		}
		return ret;
	}
}

enum Digits {
	One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9);

	int value;

	Digits(int value) {
		this.value = value;
	}

	int getValue() {
		return value;
	}
}

class Digit {
	int value;
	Digits lDigit, uDigit, rDigit, dDigit;

	Digit(int value, Digits lDigit, Digits uDigit, Digits rDigit, Digits dDigit) {
		this.value = value;
		this.lDigit = lDigit;
		this.uDigit = uDigit;
		this.rDigit = rDigit;
		this.dDigit = dDigit;
	}

	int getValue() {
		return value;
	}
}