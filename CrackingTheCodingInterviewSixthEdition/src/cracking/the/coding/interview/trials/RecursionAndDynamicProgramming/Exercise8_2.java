package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;

import cracking.the.coding.interview.trials.Helper.Helper;

class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}

public class Exercise8_2 {

	private int row;
	private int col;
	private boolean[][] maze;
	private ArrayList<Point> path;
	private int originRow, originCol;
	private HashMap<Point, Boolean> map;

	public Exercise8_2() {
		row = 10;
		col = 10;
		maze = Helper.randomBooleanMatrix(row, col, 80);
		path = new ArrayList<Point>();
		originRow = 0;
		originCol = 0;
		map = new HashMap<Point, Boolean>();
	}

	public void printMaze() {
		System.out.println("The solved maze....");
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (i == 0 && j == 0) {
					System.out.printf("%10s", "[Start]");
				} else if (i == getDestinationRow() && j == getDestinationCol()) {
					System.out.printf("%10s", "[End]");
				} else {
					Point currPos = new Point(i, j);
					if (map.containsKey(currPos) && map.get(currPos)) {
						System.out.printf("%10s", "±");
					} else {
						System.out.printf("%10s", maze[i][j] ? "░" : "█");
					}
				}
			}
			System.out.println();
		}
	}

	public void clearPath() {
		this.path.clear();
	}

	public int getDestinationRow() {
		return row - 1;
	}

	public int getDestinationCol() {
		return col - 1;
	}

	public boolean atOrigin(int row, int col) {
		return (originRow == row && originCol == col);
	}

	// This solution takes 0(2^(row+col)) time and space
	public boolean findPath(int row, int col) {
		if (row < originRow || col < originCol || !maze[row][col]) {
			return false;
		}

		if (atOrigin(row, col) || findPath(row - 1, col) || findPath(row, col - 1)) {
			Point currPos = new Point(row, col);
			path.add(currPos);
			return true;
		}

		return false;
	}

	// Time and space complexity is O(row*col)
	public boolean findPathMemoization(int row, int col) {
		if (row < originRow || col < originCol || !maze[row][col]) {
			return false;
		}
		Point currPos = new Point(row, col);

		if (map.containsKey(currPos)) {
			return map.get(currPos);
		}

		boolean success = false;
		if (atOrigin(row, col) || findPathMemoization(row - 1, col) || findPathMemoization(row, col - 1)) {
			path.add(currPos);
			success = true;
		}
		map.put(currPos, success);
		return success;

	}

	public static void main(String[] args) {

		Exercise8_2 pathFinder = new Exercise8_2();
		pathFinder.findPath(pathFinder.getDestinationRow(), pathFinder.getDestinationCol());
		System.out.println("Result of O(2^(row+col)) solution: ");
		if (pathFinder.path.size() > 0) {
			for (Point point : pathFinder.path) {
				System.out.println(point);
			}
		} else {
			System.out.println("No solution.");
		}

		pathFinder.clearPath();
		pathFinder.findPathMemoization(pathFinder.getDestinationRow(), pathFinder.getDestinationCol());
		System.out.println("\nResult of O((row*col)) solution: ");
		if (pathFinder.path.size() > 0) {
			for (Point point : pathFinder.path) {
				System.out.println(point);
			}
		} else {
			System.out.println("No solution.");
		}

		pathFinder.printMaze();
	}

}
