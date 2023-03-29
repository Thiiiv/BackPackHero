package fr.game.data;

public class Hero implements Character {

	public static int maxHealth = 40;
	private final String name = "hero";
	private int defensePoint;
	public static int health = 40;
	private Weapon equipedWeapon = null;
	private int attackPoint = 0;
	
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
	
	public void attack(Monster monster) { // add Monster argument
		
	}
	
	@Override
	public boolean beAttacked(Monster monster) { // add Monster argument
		return true;
	}

	@Override
	public int preventAttack() {
		return 0;
	}

	@Override
	public void getDamage(int damage) {
		if (health < damage) {
			health = 0;
		}
		else {
			health -= damage;
		}
	}
	
	public void equip(Weapon weapon) {
		equipedWeapon = weapon;
		attackPoint = equipedWeapon.getAttackPoint();
	}
}
