package fr.game.data.character;

public class SmallRatWolf implements Monster{
	
	private final String name = "smallRatWolf";
	private int attackPoint=9;
	private int defensePoint=0;
	private int health = 30;
	private int maxHealth = 30;
	private boolean isSelected = false;
	
	
	public SmallRatWolf() {
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
		if (this.defensePoint <= 0) {
			if (health < damage) {
				health = 0;
			}
			else {
				health -= damage;
			}
		}
		else {
			var reste = Math.abs(defensePoint - damage);
			if (health - reste < 0) {
				health = 0;
			}
			else {
				health -= reste;
			}
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
	
	@Override
	public boolean isAlive() {
		return (this.health() > 0);
	}

	@Override
	public void defend() {
		this.defensePoint += 16;
	}
	
	@Override
	public void resetDefense() {
		this.defensePoint = 0;
		
	}
}