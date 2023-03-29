package fr.game.data;

public interface Character {
	public boolean beAttacked(Monster monster);
	public int preventAttack();
	public void getDamage(int damage);
}