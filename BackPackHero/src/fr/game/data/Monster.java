package fr.game.data;

public class Monster implements Character{

	private final String name = "monster";
	private int attackPoint;
	private int defensePoint;
	private int health = 20;
	
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
	
	public void attack(Hero hero) { // add Character argument
		hero.getDamage(attackPoint);
	}
	
	@Override
	public int preventAttack(){
		return 	0;
	}

	@Override
	public boolean beAttacked(Monster monster) {
		return false;
	}

	@Override
	public void getDamage(int damage) {
		this.health -= damage;
	}
}
