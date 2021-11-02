
/**
 * Dungeon Master Juan Castro, Anh Huynh, Nyssa Yota Allows a user to explore a
 * dungeon maze and fight monsters that they encounter along the way.
 */

public class Main {
	public static void main(String[] args) {
		// Repeatedly prompts user to enter their name, a string value
		System.out.print("What is your name, traveler? ");
		String name = CheckInput.getString();

		// Choice field holds which direction choice the user chooses
		int choice;
		// Enemy field holds a random enemy
		Enemy e;
		// Displays x in unrevealed map locations
		char ch = 'x';
		// Initialize runaway variable
		boolean runaway = false;
		// Construct and initialize a new hero
		Hero hero = new Hero(name);
		// Get an instance of the Singleton Map
		Map map = Map.getInstance();
		// Initialize enemy generator object to use when we land on 'm'
		EnemyGenerator EG = new EnemyGenerator();
		// While the hero is alive
		while (hero.getHp() > 0) {

			// If hero does not runaway (when encountering a monster room)
			if (runaway == false) {
				System.out.println(hero.toString());
				System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
				// Prompt user to choose which direction to go. Uses the CheckInput class.
				choice = CheckInput.getIntRange(1, 5);
			}
			// If hero runs away, do not prompt user. Move character in a random direction.
			else {
				// Random int from 1 - 4 to choose a random direction to run to
				choice = (int) (Math.random() * 4) + 1;
				runaway = false;
			}

			// Moves hero position based on on user input
			switch (choice) {
			case 1:
				ch = hero.goNorth();
				break;
			case 2:
				ch = hero.goSouth();
				break;
			case 3:
				ch = hero.goEast();
				break;
			case 4:
				ch = hero.goWest();
				break;
			default:
				break;
			}
			// Quits game
			if (choice == 5) {
				break;
			}

			// Objects/actions based on what the hero encounters at their current position
			// on the map
			switch (ch) {
			case 'x':
				System.out.println("Location out of bounds.");
				break;
			case 'n':
				System.out.println("There was nothing here.");
				break;
			case 's':
				System.out.println("You're back at start. Would you like to visit the store? (Y/N)");
				String enter = CheckInput.getString();
				if (enter.equalsIgnoreCase("Y")) {
					store(hero);
				}
				break;
			case 'f':
				// Uses key to unlock finish
				if (hero.hasKey()) {
					System.out.println("You found the exit and unlock it with a key. Proceeding to the next level");
					hero.useKey(); // not sure how to use this method
					hero.levelUp();
				} else {
					System.out.println("You found the exit, but you don't have a key.");
				}

				break;
			case 'i':
				// Remove character at hero's location
				map.removeCharAtLoc(hero.getLoc());
				// Collects health potion or key
				int randItem = (int) (Math.random() * 2) + 1;
				if (randItem == 1) {
					// System.out.println("You found a Health Potion! You drink it to restore your
					// health.");
					// hero.heal(25);
					System.out.println("You found a key!");
					hero.pickUpKey();
				} else {
					// System.out.println("You found a key!");
					// hero.pickUpKey();
					System.out.println("You found a Health Potion! You drink it to restore your health.");
					hero.heal(25);
				}
				break;
			case 'm':
				e = EG.generateEnemy(hero.getLevel());
				System.out.println("You've encountered a " + e.getName());
				runaway = monsterRoom(hero, e);
				// If hero doesn't runaway, then set the 'm' space to a 'n' since it must be removed
				if (!runaway) {
					// remove character at hero's location
					map.removeCharAtLoc(hero.getLoc());
				}
				// else don't remove teh character
				break;
			}
		}
		// Hero has either died or quit
		System.out.println("Game Over");
	}

	/**
	 * Displays the enemy and then repeatedly prompts the user to fight or to run away
	 * @param h to fight against the enemy
	 * @param e to fight against the hero
	 * @return true if hero chooses to runaway; false if hero did not runaway
	 */
	public static boolean monsterRoom(Hero h, Enemy e) {
		boolean loop = true;

		// loop while hero and enemy are alive
		while (loop) {
			System.out.println(e.toString());
			// Prompts user to fight or run away
			System.out.print("1. Fight\n2. Run Away\n");
			// Use the CheckInput java class to ensure choice is either 1 or 2
			switch (CheckInput.getIntRange(1, 2)) {
			// Fight
			case 1:
				loop = fight(h, e);
				break;
			case 2:
				// Return true if hero chose to runaway
				return true;
			}
		}
		// Return false; hero did not runaway
		return false;
	}

	/**
	 * Allows user to choose to do a physical attack or a magical attack 
	 * @param h to use attacks
	 * @param e for hero to attack
	 * @return if the fight can continue
	 */
	public static boolean fight(Hero h, Enemy e) {
		// Choose Physical or Magical attack
		System.out.print("1. Physical Attack\n2. Magic Attack\n");

		// Attacks enemy with a physical attack
		// Use CheckInput to ensure choice is either 1 or 2
		switch (CheckInput.getIntRange(1, 2)) {
		case 1:
			System.out.println(h.getName() + " attacks " + e.getName() + " for " + h.attack(e));
			break;
		// Magical attack
		case 2:
			// Prompt user to choose a magical attack from a choice the menu
			// Attacks enemy with chosen Magical attack
			System.out.println(Magical.MAGIC_MENU);
			switch (CheckInput.getIntRange(1, 3)) {
			case 1:
				System.out.printf("%s fires a Magic Missile at %s and deals %s\n", h.getName(), e.getName(), h.magicMissile(e));
				break;
			case 2:
				System.out.printf("%s charges a Fireball at %s and deals %s\n",h.getName(), e.getName(), h.fireball(e));
				break;
			case 3:
				System.out.printf("%s Thunder Claps the %s incapacitating it, dealing %s\n",h.getName(), e.getName(), h.thunderclap(e));
				break;
			}
			break;
		}
		// If enemy is dead, return false, since fight cannot continue
		if (e.getHp() <= 0) {
			// Collects random amount of gold from enemy
			int g = (int) (Math.random() * 8) + 3;
			h.collectGold(g);
			System.out.printf("You have defeated %s and collected %s gold! \n", e.getName(), g);
			return false;
		}
		// Else enemy is alive, enemy attacks the hero
		else {
			System.out.println(e.getName() + e.attack(h).replaceAll("\n", "\n" + e.getName()));
		}

		// If hero is dead, return false, since the fight cannot continue
		if (h.getHp() <= 0) {
			// Do not repeat fight
			System.out.println("You were defeated by the " + e.getName() + "!");
			return false;
		}
		// Return true if both the hero and enemy are alive.
		return true;
	}

	/**
	 * Visits the store and buys items using gold
	 * @param h uses gold to buy items
	 */
	public static void store(Hero h) {
		boolean loop = true;
		while (loop) {
			System.out.printf("Gold: %d\n1. Health Potion (25 gold)\n2. Key (50 gold)\n3. Exit store\n",
					h.getGold());
			// Validate user input using CheckInput
			// Switch/case based on user input
			switch (CheckInput.getIntRange(1, 3)) {
			// Buy health potion
			case 1:
				// Checks for sufficient gold
				if (h.getGold() >= 25) {
					h.spendGold(25);
					System.out.println("You bought the Health Potion and drink it to restore your health.");
					h.heal(25);
				} else {
					System.out.println("Not enough gold.");
				}
				continue;
			case 2:
				// Checks for sufficient gold
				if (h.getGold() >= 50) {
					h.spendGold(50);
					System.out.println("You purchased a key.");
					h.pickUpKey();
				} else {
					System.out.println("Not enough gold.");
				}
				continue;
			case 3:
				loop = false;
				break;
			}
		}
	}
}