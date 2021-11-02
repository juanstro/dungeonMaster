public class Warlock extends EnemyDecorator implements Magical {
  /**
   * Makes enemy into a warlock and adds 1 hp
   */
  public Warlock(Enemy e) {
    super(e, e.getName() + " Warlock", e.getHp() + 1);
  }

  
  /**
   * Randomly select one of the three spells
   * @param e to deal a random amount of damage
   * @return string representing damage
   */
  public String attack(Entity e) {
    // Generate random attack to deal to hero
    int atk = (int) (Math.random() * 3) + 1;

    // Chooses one spell based on random attack
    if (atk == 1) {
      return super.attack(e) + String.format("\n blasts %s with a Magic Missile for %s damage.", e.getName(), magicMissile(e));
    } else if (atk == 2) {
      return super.attack(e) + String.format("\n immolates %s with a Fireball for %s damage.", e.getName(), fireball(e));
    }
    return super.attack(e) + String.format("\n Thunder Claps %s dealing %s damage.", e.getName(), thunderclap(e));

  }

  /**
   * Enemy attacks hero with a magic missile and hero's hp is decreased
   * @param e is dealt random amount of damage
   * @return string representing damage to hero
   */
  @Override
  public String magicMissile(Entity e) {
    // Generate random amount of damage to deal to hero, between (0 - 2)
    int dmg = (int) (Math.random() * 3);
    e.takeDamage(dmg);
    return String.valueOf(dmg);
  }

  /**
   * Enemy attacks hero with a fireball and hero's hp is decreased
   * @param e is dealt random amount of damage
   * @return string representing damage to hero
   */
  @Override
  public String fireball(Entity e) {
    // Generate random amount of damage to deal to hero (0 - 3)
    int dmg = (int) (Math.random() * 4);
    e.takeDamage(dmg);
    return String.valueOf(dmg);
  }

  /**
   * Enemy attacks hero with a thunderclap and hero's hp is decreased
   * @param e is dealt random amount of damage
   * @return string representing damage to hero
   */
  @Override
  public String thunderclap(Entity e) {
    // Generate random amount of damage to deal to hero (1 - 3)
    int dmg = (int) (Math.random() * 3) + 1;
    e.takeDamage(dmg);
    return String.valueOf(dmg);
  }
}