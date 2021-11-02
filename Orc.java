public class Orc extends Enemy {
  /**
   * Constructs an Orc with 4 hp
   */
  public Orc() {
    super("Orc", 4);
  }

  /**
   * Attacks hero with random damage between 0 - 4 damage.
   * @param e is attacked 
   * @return string representing damage to hero
   */
  @Override
  public String attack(Entity e) {
    // (0 - 4) dmg
    int dmg = (int) (Math.random() * 5);
    e.takeDamage(dmg);
    return String.format(" hits %s with a basic attack for %d damage.", e.getName(), dmg);
  }
}
