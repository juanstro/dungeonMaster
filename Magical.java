public interface Magical {
	// Menu for magical attacks
	public static final String MAGIC_MENU = "1. Magic Missle\n2. Fireball\n3. Thunderclap";

	// Defines magical attacks
	public String magicMissile(Entity e);
	public String fireball(Entity e);
	public String thunderclap(Entity e);
}
