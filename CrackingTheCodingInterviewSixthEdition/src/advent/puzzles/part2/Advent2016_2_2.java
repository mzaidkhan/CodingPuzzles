package advent.puzzles.part2;

import java.util.Arrays;

public class Advent2016_2_2 {
	public static Digit _1 = new Digit(1, null, null, null, Digits.Three);
	public static Digit _2 = new Digit(2, null, null, Digits.Three, Digits.Six);
	public static Digit _3 = new Digit(3, Digits.Two, Digits.One, Digits.Four, Digits.Seven);
	public static Digit _4 = new Digit(4, Digits.Three, null, null, Digits.Eight);
	public static Digit _5 = new Digit(5, null, null, Digits.Six, null);
	public static Digit _6 = new Digit(6, Digits.Five, Digits.Two, Digits.Seven, Digits.A);
	public static Digit _7 = new Digit(7, Digits.Six, Digits.Three, Digits.Eight, Digits.B);
	public static Digit _8 = new Digit(8, Digits.Seven, Digits.Four, Digits.Nine, Digits.C);
	public static Digit _9 = new Digit(9, Digits.Eight, null, null, null);
	public static Digit _A = new Digit(10, null, Digits.Six, Digits.B, null);
	public static Digit _B = new Digit(11, Digits.A, Digits.Seven, Digits.C, Digits.D);
	public static Digit _C = new Digit(12, Digits.B, Digits.Eight, null, null);
	public static Digit _D = new Digit(13, null, Digits.B, null, null);

	public static int[] passCode = new int[5];

	public static Digit startDigit = _5;

	public static String input = "LURLDDLDULRURDUDLRULRDLLRURDUDRLLRLRURDRULDLRLRRDDULUDULURULLURLURRRLLDURURLLUURDLLDUUDRRDLDLLRUUDURURRULURUURLDLLLUDDUUDRULLRUDURRLRLLDRRUDULLDUUUDLDLRLLRLULDLRLUDLRRULDDDURLUULRDLRULRDURDURUUUDDRRDRRUDULDUUULLLLURRDDUULDRDRLULRRRUUDUURDULDDRLDRDLLDDLRDLDULUDDLULUDRLULRRRRUUUDULULDLUDUUUUDURLUDRDLLDDRULUURDRRRDRLDLLURLULDULRUDRDDUDDLRLRRDUDDRULRULULRDDDDRDLLLRURDDDDRDRUDUDUUDRUDLDULRUULLRRLURRRRUUDRDLDUDDLUDRRURLRDDLUUDUDUUDRLUURURRURDRRRURULUUDUUDURUUURDDDURUDLRLLULRULRDURLLDDULLDULULDDDRUDDDUUDDUDDRRRURRUURRRRURUDRRDLRDUUULLRRRUDD;"
			+ "DLDUDULDLRDLUDDLLRLUUULLDURRUDLLDUDDRDRLRDDUUUURDULDULLRDRURDLULRUURRDLULUDRURDULLDRURUULLDLLUDRLUDRUDRURURUULRDLLDDDLRUDUDLUDURLDDLRRUUURDDDRLUDDDUDDLDUDDUUUUUULLRDRRUDRUDDDLLLDRDUULRLDURLLDURUDDLLURDDLULLDDDRLUDRDDLDLDLRLURRDURRRUDRRDUUDDRLLUDLDRLRDUDLDLRDRUDUUULULUDRRULUDRDRRLLDDRDDDLULURUURULLRRRRRDDRDDRRRDLRDURURRRDDULLUULRULURURDRRUDURDDUURDUURUURUULURUUDULURRDLRRUUDRLLDLDRRRULDRLLRLDUDULRRLDUDDUUURDUDLDDDUDL;"
			+ "RURDRUDUUUUULLLUULDULLLDRUULURLDULULRDDLRLLRURULLLLLLRULLURRDLULLUULRRDURRURLUDLULDLRRULRDLDULLDDRRDLLRURRDULULDRRDDULDURRRUUURUDDURULUUDURUULUDLUURRLDLRDDUUUUURULDRDUDDULULRDRUUURRRDRLURRLUUULRUDRRLUDRDLDUDDRDRRUULLLLDUUUULDULRRRLLRLRLRULDLRURRLRLDLRRDRDRLDRUDDDUUDRLLUUURLRLULURLDRRULRULUDRUUURRUDLDDRRDDURUUULLDDLLDDRUDDDUULUDRDDLULDDDDRULDDDDUUUURRLDUURULRDDRDLLLRRDDURUDRRLDUDULRULDDLDDLDUUUULDLLULUUDDULUUDLRDRUDLURDULUDDRDRDRDDURDLURLULRUURDUDULDDLDDRUULLRDRLRRUURRDDRDUDDLRRLLDRDLUUDRRDDDUUUDLRRLDDDUDRURRDDUULUDLLLRUDDRULRLLLRDLUDUUUUURLRRUDUDDDDLRLLULLUDRDURDDULULRDRDLUDDRLURRLRRULRL;"
			+ "LDUURLLULRUURRDLDRUULRDRDDDRULDLURDDRURULLRUURRLRRLDRURRDRLUDRUUUULLDRLURDRLRUDDRDDDUURRDRRURULLLDRDRDLDUURLDRUULLDRDDRRDRDUUDLURUDDLLUUDDULDDULRDDUUDDDLRLLLULLDLUDRRLDUUDRUUDUDUURULDRRLRRDLRLURDRURURRDURDURRUDLRURURUUDURURUDRURULLLLLUDRUDUDULRLLLRDRLLRLRLRRDULRUUULURLRRLDRRRDRULRUDUURRRRULDDLRULDRRRDLDRLUDLLUDDRURLURURRLRUDLRLLRDLLDRDDLDUDRDLDDRULDDULUDDLLDURDULLDURRURRULLDRLUURURLLUDDRLRRUUDULRRLLRUDRDUURLDDLLURRDLRUURLLDRDLRUULUDURRDULUULDDLUUUDDLRRDRDUDLRUULDDDLDDRUDDD;"
			+ "DRRDRRURURUDDDRULRUDLDLDULRLDURURUUURURLURURDDDDRULUDLDDRDDUDULRUUULRDUDULURLRULRDDLDUDLDLULRULDRRLUDLLLLURUDUDLLDLDRLRUUULRDDLUURDRRDLUDUDRULRRDDRRLDUDLLDLURLRDLRUUDLDULURDDUUDDLRDLUURLDLRLRDLLRUDRDUURDDLDDLURRDDRDRURULURRLRLDURLRRUUUDDUUDRDRULRDLURLDDDRURUDRULDURUUUUDULURUDDDDUURULULDRURRDRDURUUURURLLDRDLDLRDDULDRLLDUDUDDLRLLRLRUUDLUDDULRLDLLRLUUDLLLUUDULRDULDLRRLDDDDUDDRRRDDRDDUDRLLLDLLDLLRDLDRDLUDRRRLDDRLUDLRLDRUURUDURDLRDDULRLDUUUDRLLDRLDLLDLDRRRLLULLUDDDLRUDULDDDLDRRLLRDDLDUULRDLRRLRLLRUUULLRDUDLRURRRUULLULLLRRURLRDULLLRLDUUUDDRLRLUURRLUUUDURLRDURRDUDDUDDRDDRUD;";

	public static void main(String... args) {
		String[] instruction = input.split(";");
		for (int i = 0; i < instruction.length; i++) {
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
					default : System.out.println("Error " + ip);
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
		case A:
			ret = _A;
			break;
		case B:
			ret = _B;
			break;
		case C:
			ret = _C;
			break;
		case D:
			ret = _D;
			break;
		}
		return ret;
	}
}

enum Digits {
	One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), A(10), B(11), C(12), D(13);

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