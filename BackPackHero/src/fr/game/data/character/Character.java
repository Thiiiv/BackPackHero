package fr.game.data.character;

import fr.game.data.item.Shield;

public interface Character {
	// public boolean beAttacked();
	// public int preventAttack();
	public void getDamage(int damage);
	public int defensePoint();
	public String getCharacterImage();
	public boolean isAlive();
	public void defend();
	public void resetDefense();
	public String whichState();
	public void setState(String state);
}