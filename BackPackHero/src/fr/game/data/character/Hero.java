package fr.game.data.character;

import fr.game.data.item.Item;
import fr.game.data.item.Weapon;
import fr.game.data.Inventory;

public class Hero implements Character {

	public static int maxHealth;
	private final String name;
	private int defensePoint;
	public static int health;
	private Weapon equipedWeapon;
	private int attackPoint;
	private int gold;

	
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
	
	public String healthText() {
		return "La vie du héros est de " + health + " points de vie.";
	}
	
	public String maxHealthText() {
		return "La vie du héros est passée à " + maxHealth + ".";
	}
	
	public int health() {
		return health;
	}
	
	public int maxHealth() {
		return maxHealth;
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
	
	public Item buy(Item item, Inventory inventory) {
	    if (gold >= item.getPrice()) {
	        gold -= item.getPrice();
	        System.out.println("Le héros a acheté " + item.getName() + ".");
	        return item;
	    } else {
	        System.out.println("Le héros n'a pas assez d'or pour acheter " + item.getName() + ".");
	        return null;
	    }
	}
	
	public void sell(int x, int y, Inventory inventory ,Item item) {
	    gold += item.getSellPrice();
	    inventory.removeItemFromInventory(item);
	    System.out.println("Le héros a vendu " + item.getName() + " pour " + item.getSellPrice() + " pièces d'or.");
	}

	public static void improveHealth() {
		
	}

	@Override
	public boolean beAttacked(Character character) {
		return true;
	}

	@Override
	public String getCharacterImage() {
		return "hero-4.png";
	}
}