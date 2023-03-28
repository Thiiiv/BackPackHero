package fr.uge.data;

public interface Character {
	public boolean beAttacked(String monster);
	public int preventAttack();
	public void getDamage(int damage);
}