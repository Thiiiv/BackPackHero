package fr.game.data.character;

public interface Monster extends Character{

	
	
	public String getName();
	public int attackPoint();
	public int defensePoint();
	public int health();
	public void attack(Hero hero); // add Character argument
	public int preventAttack();
	
}