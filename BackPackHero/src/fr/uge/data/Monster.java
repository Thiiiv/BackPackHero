package fr.enzo.character;

public class Monster implements Character{

	private String name;
	private int attackPoint;
	private int defensePoint;
	private int health;
	
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
	
	public void attack(String character) { // add Character argument
		
	}
	
	@Override
	public int preventAttack(){
		return 	0;
	}

	@Override
	public boolean beAttacked(String monster) {
		return false;
	}

	@Override
	public void getDamage(int damage) {
		this.health -= damage;
	}
}
