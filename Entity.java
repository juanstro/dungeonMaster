public abstract class Entity {
	private String name;
	private int maxHp;
	private int hp;

	/**
	 * Constructs entity
	 * @param n   is name of entity
	 * @param mHp is full hp of entity
	 */
	public Entity(String n, int mHp) {
		name = n;
		maxHp = mHp;
		hp = mHp;
	}

	/**
	 * Retrieves name of entity
	 * @return name of entity
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves hp of entity
	 * @return hp of entity
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Retrieves max hp of entity
	 * @return max hp of entity
	 */
	public int getMaxHp() {
		return maxHp;
	}

	/**
	 * Increases hp of entity but does not go above max hp amount
	 * @param healAmt is amount by which hp of entity is increased
	 */
	public void heal(int healAmt) {
		hp += healAmt;
		if (hp > maxHp) {
			hp = maxHp;
		}
	}

	/**
	 * Decreases hp of entity but does not go below 0 hp
	 * @param dmg is amount by which hp of entity is decreased
	 */
	public void takeDamage(int dmg) {
		hp -= dmg;
	}

	/**
	 * Displays the name and hp over maxHp
	 * @return max hp of entity
	 */
	public String toString() {
		return name + "\nHP: " + hp + "/" + maxHp;
	}

	// Abstract methods are defined, but not created.
	// The subclasses Hero and Enemy override this method.
	public abstract String attack(Entity e);

}
