public class Warrior extends EnemyDecorator {
  /**
   * Makes enemy into a warrior and adds 2 hp
   */
  public Warrior(Enemy e) {
    super(e, e.getName() + " Warrior", e.getHp() + 2);
  }

  /**
   * Attacks hero with random damage 1 - 3
   * @param e is attacked 
   * @return string representing spell and damage to hero
   */
  @Override
  public String attack(Entity e) {
    // Randomizes amount of damage (1 - 3)
    super.attack(e);
    int dmg = (int) (Math.random() * 3) + 1;
    e.takeDamage(dmg);
    return super.attack(e) + String.format("\n attacks %s for %d damage.", e.getName(), dmg);
  }
}