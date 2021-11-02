public class Troll extends Enemy {
  /**
   * Constructs a Troll with 5 hp
   */
  public Troll() {
    super("Troll", 5);
  }

  /**
   * Attacks hero with random damage 0 - 5
   * @param e is attacked 
   * @return string representing damage to hero
   */
  @Override
  public String attack(Entity e) {
    // (0 - 5) dmg
    int dmg = (int) (Math.random() * 6);
    e.takeDamage(dmg);
    return String.format(" hits %s with a basic attack for %d damage.", e.getName(), dmg);
  }
}
