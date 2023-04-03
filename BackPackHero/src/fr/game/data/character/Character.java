package fr.game.data.character;

public interface Character {
	// public boolean beAttacked();
	// public int preventAttack();
	public void getDamage(int damage);
	public boolean beAttacked(Character character);
}