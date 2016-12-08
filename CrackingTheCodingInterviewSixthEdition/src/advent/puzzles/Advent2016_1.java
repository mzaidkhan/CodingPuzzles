package advent.puzzles;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Advent2016_1 {

	enum Direction {
		NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

		int dx, dy;
		Direction left, right;

		static {
			NORTH.left = WEST;
			NORTH.right = EAST;
			EAST.left = NORTH;
			EAST.right = SOUTH;
			SOUTH.left = EAST;
			SOUTH.right = WEST;
			WEST.left = SOUTH;
			WEST.right = NORTH;
		}

		Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}

	static int taxiCabDistanceFormula(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static void main(String[] args) {

		Set<Point> visited = new HashSet<>();
		int x = 0;
		int y = 0;
		visited.add(new Point(x, y));
		Direction current = Direction.NORTH;
		String input = "R1, R3, L2, L5, L2, L1, R3, L4, R2, L2, L4, R2, L1, R1, L2, R3, L1, L4, R2, L5, R3, R4, L1, R2, L1, R3, L4, R5, L4, L5, R5, L3, R2, L3, L3, R1, R3, L4, R2, R5, L4, R1, L1, L1, R5, L2, R1, L2, R188, L5, L3, R5, R1, L2, L4, R3, R5, L3, R3, R45, L4, R4, R72, R2, R3, L1, R1, L1, L1, R192, L1, L1, L1, L4, R1, L2, L5, L3, R5, L3, R3, L4, L3, R1, R4, L2, R2, R3, L5, R3, L1, R1, R4, L2, L3, R1, R3, L4, L3, L4, L2, L2, R1, R3, L5, L1, R4, R2, L4, L1, R3, R3, R1, L5, L2, R4, R4, R2, R1, R5, R5, L4, L1, R5, R3, R4, R5, R3, L1, L2, L4, R1, R4, R5, L2, L3, R4, L4, R2, L2, L4, L2, R5, R1, R4, R3, R5, L4, L4, L5, L5, R3, R4, L1, L3, R2, L2, R1, L3, L5, R5, R5, R3, L4, L2, R4, R5, R1, R4, L3";
		String[] instruction = input.split(",");

		for (String each : instruction) {
			String temp = each.trim();
			Boolean right = temp.startsWith("R");
			int dist = Integer.parseInt(temp.substring(1));

			if (right) {
				current = current.right;
			} else {
				current = current.left;
			}

			for (int i = 0; i < dist; i++) {
				x += current.dx;
				y += current.dy;
				Point p = new Point(x, y);
				if (visited.contains(p)) {
					System.out.println("(X, Y) = (" + x + "," + y+ ") Visited: " + taxiCabDistanceFormula(0, 0, x, y));
				} else {
					visited.add(p);
				}
			}
		}

		System.out.println("Final destination distance: " + taxiCabDistanceFormula(0, 0, x, y));
	}
}