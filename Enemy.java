public abstract class Enemy extends Entity {

	/**
	 * Represents an enemy the hero will encounter
	 * @param name of enemy
	 * @param maxHp of enemy
   * @return enemy with name and max hp
	 */
	public Enemy(String name, int maxHp) {
		super(name, maxHp);
	}
}
