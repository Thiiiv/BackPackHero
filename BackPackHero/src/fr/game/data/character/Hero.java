package fr.game.data.character;

import fr.game.data.item.Weapon;

public class Hero implements Character {

	public static int maxHealth;
	private final String name;
	private int defensePoint;
	public static int health;
	private Weapon equipedWeapon;
	private int attackPoint;
	
	public Hero() {
		name = "héros";
		maxHealth = 40;
		health = 40;
		defensePoint = 0;
		equipedWeapon = null;
		attackPoint = 0;
	}
	
	public String getName() {
		return name;
	}

	public int attackPoint() {
		return attackPoint;
	}
	
	public int defensePoint() {
		return defensePoint;
	}
	
	public String health() {
		return "La vie du héros est de " + health + " points de vie.";
	}
	
	public static void heal(int healAmount) {
		if ((health + healAmount) > maxHealth) {
			health = maxHealth;
		}
		else {
			health += healAmount;
		}
		System.out.println("Le héros se régénère de " + healAmount + " points de vie.");
	}
	
	public void attack(Monster monster) { // add Monster argument
		System.out.println("Le " + name + "attaque le " + monster + ".");
	}
	
	public boolean beAttacked(Monster monster) { // add Monster argument
		return true;
	}

	@Override
	public void getDamage(int damage) {
		if (health < damage) {
			health = 0;
		}
		else {
			health -= damage;
		}
		System.out.println("Le " + name + " a encaissé " + damage + " points de dégât."); 
	}
	
	public void equip(Weapon weapon) {
		equipedWeapon = weapon;
		attackPoint = equipedWeapon.getAttackPoint();
	}
}
