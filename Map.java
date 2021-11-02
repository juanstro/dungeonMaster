import java.io.*;
import java.util.Scanner;
import java.awt.Point;

public class Map {
	private char[][] map;
	private boolean[][] revealed;
	// Singeton instance
	private static Map instance = null;

	/**
	 * A 5x5 character array that represents a dungeon maze A 5x5 boolean array that
	 * determines if the room has already been visited
	 */
	public Map() {
		map = new char[5][5];
		revealed = new boolean[5][5];
	}

  /**
  * Gets instance of the Singleton Map. Ensures there is only one instance of Map.
  * @return map instance
  */
	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}

	/**
	 * Reads in the map from the file and stores it in the character array
	 * @param mapNum to identify which map to read in
	 */
	public void loadMap(int mapNum) {
		// Reads in the map from file and checks for errors
		try {
			Scanner read = new Scanner(new File("Map" + mapNum + ".txt"));
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					map[i][j] = read.next().charAt(0); // String to char
					revealed[i][j] = false; // filling revealed array with false
				}
			}
			read.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found");
		}
	}

	/**
	 * Displays hero's current position, revealed rooms, and x's for unrevealed rooms
	 * @param p for current position of hero
	 * @return string of map with hero's current position, revealed rooms, and x's for unrevealed rooms
	 */

	public String mapToString(Point p) {
		String out = "";

		// iterating the map
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// if the current node is revealed
				if (this.revealed[i][j]) {
					// if the current node is where the hero is
					if (i == p.y && j == p.x) {
						out += ("* ");

					} else {
						out += (String.valueOf(this.map[i][j]) + " ");
					}

				} else {
					out += ("x ");
				}
			}
			if (i != 4) {
			out += "\n";
			}
		}

		return out;
	}

	/**
	 * Finds starting position of map
	 * @return point representing the starting position
	 */

	public Point findStart() {
		int x = 0;
		int y = 0;

		// loops through each row of map then determines if it contains the char
		for (char[] i : map) {
			if (new String(i).contains("s")) {
				x = new String(i).indexOf("s");
				revealed[y][x] = true;
				break;
			} else {
				y += 1;
			}
		}
		return new Point(x, y);
	}

	/**
	 * Retrieves the character at current point
	 * @param p contains x and y coordinates of current point
	 * @return character at current point
	 */
	public char getCharAtLoc(Point p) {
		return map[p.y][p.x];
	}

  /**
   * Replaces character at location
   * @param p is current location of hero 
   */

	public void removeCharAtLoc(Point p) {
		if (map[p.y][p.x] != 's' && map[p.y][p.x] != 'f') {
			// avoids removing 's' so store is accessible
			map[p.y][p.x] = 'n';
		}
	}

	/**
	 * Sets current point on revealed array as true
	 * @param p contains x and y cooredinate of current point
	 */
	public void reveal(Point p) {
		revealed[p.y][p.x] = true;
	}
}
