package fr.game.data.character;

public class RatWolf implements Monster{
	
	private final String name = "Rat-loup(s)";
	private int attackPoint=9;
	private int defensePoint=16;
	private int health ;
	private int maxHealth = 45;
	
	
	public RatWolf(int health) {
		this.health=health;
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
	
	public int health() {
		return health;
	}
	
	public int maxHealth() {
		return maxHealth;
	}
	
	public void attack(Hero hero) { // add Character argument
		hero.getDamage(attackPoint);
	}
	
	public int preventAttack(){
		return attackPoint;
	}
	
	public void getDamage(int damage) {
		this.health -= damage;
		 System.out.println("Le " + name + " a encaissé " + damage + " points de dégât.");
	}

	@Override
	public boolean beAttacked(Character character) {
		return true;
	}


}