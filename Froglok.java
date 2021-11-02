public class Froglok extends Enemy {
  /**
   * Constructs a Froglok with 3 hp
   */
  public Froglok() {
    super("Froglok", 3);
  }

  /**
   * Attacks hero with random damage 1 - 3
   * @param e is attacked 
   * @return string representing damage to hero
   */
  @Override
  public String attack(Entity e) {
    // (1 - 3) dmg
    int dmg = (int) (Math.random() * 3) + 1;
    e.takeDamage(dmg);
    return String.format(" hits %s with a basic attack for %d damage.", e.getName(), dmg);
  }
}
