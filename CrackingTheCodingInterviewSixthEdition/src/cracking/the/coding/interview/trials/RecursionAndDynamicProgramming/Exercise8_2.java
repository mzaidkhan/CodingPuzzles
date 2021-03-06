package cracking.the.coding.interview.trials.RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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
	private Stack<Point> path2;

	private int originRow, originCol;
	private HashMap<Point, Boolean> map;

	public Exercise8_2() {
		row = 10;
		col = 10;
		maze = Helper.randomBooleanMatrix(row, col, 80);
		path = new ArrayList<Point>();
		path2 = new Stack<Point>();
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

	public void clearCaches() {
		this.path.clear();
		this.path2.clear();
		this.map.clear();
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

	public boolean atDestination(int row, int col) {
		return (getDestinationRow() == row && getDestinationCol() == col);
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

	public boolean pathFinderTopDown(int row, int col) {
		if (row > getDestinationCol() || col > getDestinationRow() || !maze[row][col]) {
			return false;
		}

		Point currPos = new Point(row, col);
		if (map.containsKey(currPos)) {
			return map.get(currPos);
		}

		boolean success = false;
		if (atDestination(row, col) || pathFinderTopDown(row + 1, col) || pathFinderTopDown(row, col + 1)) {
			path2.push(currPos);
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

		pathFinder.findPathMemoization(pathFinder.getDestinationRow(), pathFinder.getDestinationCol());
		System.out.println("\nResult of O((row*col)) solution: ");
		if (pathFinder.path.size() > 0) {
			for (Point point : pathFinder.path) {
				System.out.println(point);
			}
			pathFinder.printMaze();
		} else {
			System.out.println("No solution.");
		}
		pathFinder.clearCaches();

		pathFinder.pathFinderTopDown(pathFinder.originRow, pathFinder.originCol);
		System.out.println("\nResult of Top Down solution solution: ");
		if (pathFinder.path2.size() > 0) {
			while (!pathFinder.path2.isEmpty()) {
				System.out.println(pathFinder.path2.pop());
			}
			pathFinder.printMaze();
		} else {
			System.out.println("No solution.");
		}
	}
}
