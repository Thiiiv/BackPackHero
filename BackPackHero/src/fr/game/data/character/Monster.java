package fr.game.data.character;

import fr.game.data.item.Shield;

public interface Monster extends Character{
	public String getName();
	public int attackPoint();
	public int health();
	public void attack(Hero hero); // add Character argument
	public String preventAction();
	public String getCharacterImage();
	public int maxHealth();
	public boolean isSelected();
	public void setSelected(boolean isOrNot);
	public int getDefenseGain();
	
}