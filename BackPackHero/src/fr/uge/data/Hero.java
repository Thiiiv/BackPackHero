package fr.uge.data;

public class Hero implements Character {

	public static int maxHealth = 40;
	private String name;
	private int attackPoint;
	private int defensePoint;
	public static int health = 40;
	
	public String getName() {
		return name;
	}

	public int attackPoint() {
		return attackPoint;
	}
	
	public int defensePoint() {
		return defensePoint;
	}
	
	public int health() {
		return health;
	}
	
	public static void heal(int healAmount) {
		if ((health + healAmount) > maxHealth) {
			health = maxHealth;
		}
		else {
			health += healAmount;
		}
	}
	
	public void attack(String monster) { // add Monster argument
		
	}
	
	@Override
	public boolean beAttacked(String monster) { //add Monster argument
		return true;
	}

	@Override
	public int preventAttack() {
		return 0;
	}

	@Override
	public void getDamage(int damage) {
		health -= damage;
	}
}
