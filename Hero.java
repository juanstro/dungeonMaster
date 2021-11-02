import java.awt.Point;

public class Hero extends Entity implements Magical {
	private Map map;
	private int level;
	private Point loc;
	private int gold;
	private int key;

	/**
	 * Constructs hero's name, hp, and a new Map at the start of level 1
	 * @param name of hero
	 */
	public Hero(String name) {
		super(name, 25);
		gold = 0;
		level = 0;
		map = Map.getInstance();
		// Level up to 1, loads the map at level 1, finds the start of the map.
		levelUp();
	}

	public int getLevel() {
		return level;
	}

	/**
	 * Gives hero a level-up. Also loads the map based on hero level and finds starting location point.
	 */
	public void levelUp() {
		level += 1;
		if (level % 3 == 1) {
			map.loadMap(1);
		} else if (level % 3 == 2) {
			map.loadMap(2);
		} else if (level % 3 == 0) {
			map.loadMap(3);
		}
		loc = map.findStart();
	}

	/**
	 * Moves hero North, reveals character at new location, and removes it
	 * @return character at new location
	 */
	public char goNorth() {
		// Checks if new location is within bounds
		if (loc.getY() != 0) { // if (loc.getY() < 0)
			loc.y -= 1; // loc.translate(0, 1);
			map.reveal(loc);
			return map.getCharAtLoc(loc);
		}
		return 'x';
	}

	/**
	 * Moves hero South, reveals character at new location, and removes it
	 * @return character at new location
	 */
	public char goSouth() {
		// Checks if new location is within bounds
		if (loc.getY() != 4) { // if (loc.getY() > -4)
			loc.y += 1; // loc.translate(0, -1);
			map.reveal(loc);
			return map.getCharAtLoc(loc);
		}
		return 'x';
	}

	/**
	 * Moves hero East, reveals character at new location, and removes it
	 * @return character at new location
	 */
	public char goEast() {
		// Checks if new location is within bounds
		if (loc.getX() != 4) { // (loc.getX() < 4)
			loc.x += 1; // loc.translate(1, 0);
			map.reveal(loc);
			return map.getCharAtLoc(loc);
		}
		return 'x';
	}

	/**
	 * Moves hero West, reveals character at new location, and removes it
	 * @return character at new location
	 */
	public char goWest() {
		// Checks if new location is within bounds
		if (loc.getX() != 0) { // (loc.getX() > 0)
			loc.x -= 1; // loc.translate(-1, 0);
			map.reveal(loc);
			return map.getCharAtLoc(loc);
		}
		return 'x';
	}

	/**
	 * Hero physically attacks enemy and enemy's hp is decreased by a random amount
	 * @param e is dealt damage
	 * @return string representing damage to enemy
	 */
	@Override
	public String attack(Entity e) {
		// Generate random amount of damage to deal to entity, between (1 - 3)
		int dmg = (int) (Math.random() * 3) + 1;
		e.takeDamage(dmg);
		return String.valueOf(dmg) + " damage.";
	}

	/**
	 * Hero attacks enemy with a magic missle and enemy's hp is decreased
	 * @param e is dealt randam amount of damage
	 * @return string representing damage to enemy
	 */
	@Override
	public String magicMissile(Entity e) {
		// Generates random amount of damage to deal to entity. (1 - 2)
		int dmg = (int) (Math.random() * 2) + 1;
		e.takeDamage(dmg);
		return String.valueOf(dmg) + " damage.";
	}

	/**
	 * Hero attacks enemy with a fireball and enemy's hp is decreased
	 * @param e is dealt random amount of damage
	 * @return string representing damage to enemy
	 */
	@Override
	public String fireball(Entity e) {
		// Generates random amount of damage to deal to entity. (2 - 5)
		int dmg = (int) (Math.random() * 4) + 2;
		e.takeDamage(dmg);
		return String.valueOf(dmg) + " damage.";
	}

	/**
	 * Hero attacks enemy with a thunderclap and enemy's hp is decreased
	 * @param e is dealt random amount of damage
	 * @return string representing damage to enemy
	 */
	@Override
	public String thunderclap(Entity e) {
		// Generates random amount of damage to deal to entity. (1 - 3)
		int dmg = (int) (Math.random() * 3) + 1;
		e.takeDamage(dmg);
		return String.valueOf(dmg) + " damage.";
	}

	/**
	 * Displays hero's name, hp, level, and map
	 * @return string with hero's name, hp, level, and map
	 */
	@Override
	public String toString() {
		return super.toString() + "\nLevel: " + level + "\nKeys: " + key + "\nGold: " + gold + "\n" + map.mapToString(loc);
	}

	/**
	 * Retrieves hero's amount of gold
	 * @return gold of hero
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Collects gold from enemy
	 */
	public void collectGold(int g) {
		gold += g;
	}

	/**
	 * Spends gold at store
	 */
	public void spendGold(int g) {
		gold -= g;
	}

	/**
	 * Determines if hero has a key
	 */
	public boolean hasKey() {
		if (key == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Collects key
	 */
	public void pickUpKey() {
		key += 1;
	}

	/**
	 * Determines if key has been used
	 */
	public boolean useKey() {
		if (hasKey()) {
			key -= 1;
			return true;
		}
		// false if key wasn't used
		return false;
	}

  /**
   * Retrieves hero's location
   * @return location of hero
   */
	public Point getLoc() {
		return loc;
	}
}