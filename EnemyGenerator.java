public class EnemyGenerator {
	/**
	 * Randomly generates a base enemy at the level of the hero
	 * @param level determines number of times enemy will be decorated
	 * @return e that is either a warlock or a regular enemy
	 */
	public Enemy generateEnemy(int level) {
		// Generate random value to choose which base enemy to use
		Enemy e;
		int randEnemy = (int) (Math.random() * 4) + 1;
		if (randEnemy == 1) {
			e = new Troll();
		} else if (randEnemy == 2) {
			e = new Orc();
		} else if (randEnemy == 3) {
			e = new Froglok();
		} else {
			e = new Goblin();
		}
		// Generate value 1 or 2 to determine if enemy is Warlock or Warrior
		int randType = (int) (Math.random() * 2) + 1;

		// Decorate the generated enemy with Warlock or Warrior (randType)
		// Only decorate if hero's current level is > 1.
		// Decorate with the same decoration for every level the hero is above 1.
		for (int i = 1; i < level; i++) {
			if (randType == 1) {
				e = new Warrior(e);
			} else {
				e = new Warlock(e);
			}
		}
		return e;
	}
}
