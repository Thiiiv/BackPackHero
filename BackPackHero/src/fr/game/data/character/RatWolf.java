package fr.game.data.character;

public class RatWolf implements Monster{
	
	private final String name = "Rat-loup(s)";
	private int attackPoint=9;
	private int defensePoint=16;
	private int health = 45;
	private int maxHealth = 45;
	private boolean isSelected = false;
	
	
	public RatWolf() {
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
		if (this.health - damage < 0) {
			this.health = 0;
		}
		else {
			this.health -= damage;
		}
		 System.out.println("Le " + name + " a encaissé " + damage + " points de dégât.");
	}

	@Override
	public boolean beAttacked(Character character) {
		return true;
	}

	@Override
	public String getCharacterImage() {
		return "smallRatWolf.png";
	}

	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public void setSelected(boolean isOrNot) {
		isSelected = isOrNot;
	}


}