public class Goblin extends Enemy {
  /**
   * Constructs a Goblin with 2 hp
   */
  public Goblin() {
    super("Goblin", 2);
  }

  /**
   * Attacks hero with random damage 1 - 2
   * @param e is attacked 
   * @return string representing damage to hero
   */
  @Override
  public String attack(Entity e) {
    // (1 - 2) dmg
    int dmg = (int) (Math.random() * 2) + 1;
    e.takeDamage(dmg);
    return String.format(" hits %s with a basic attack for %d damage.", e.getName(), dmg);
  }
}
