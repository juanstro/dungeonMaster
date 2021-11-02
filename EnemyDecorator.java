public abstract class EnemyDecorator extends Enemy {
  private Enemy enemy;

  /**
   * Turns enemy into either a warrior or warlock
   * @param e becomes warrior or warlock 
   * @param n adds warrior or warlock to name 
   * @param h adds hp
   */
  public EnemyDecorator(Enemy e, String n, int h) {
    super(n, h);
    enemy = e;
  }

  /**
   * Attacks hero
   * @param e hero is attacked by enemy 
   * @return string representing damage to hero
   */
  @Override
  public String attack(Entity e) {
    return enemy.attack(e);
  }
}
