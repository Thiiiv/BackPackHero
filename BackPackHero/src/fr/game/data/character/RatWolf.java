package fr.game.data.character;

public class RatWolf implements Monster{
	
	private final String name = "ratWolf";
	private int attackPoint=9;
	private int defensePoint=0;
	private int health = 45;
	private int maxHealth = 45;
	private boolean isSelected = false;
	private String state = "standby";
	
	
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
		state = "attack";
		hero.getDamage(attackPoint);
	}
	
	public int preventAttack(){
		state = "standby";
		return attackPoint;
	}
	
	public void getDamage(int damage) {
		if (this.defensePoint <= 0) {
			state = "beAttacked";
			if (health < damage) {
				health = 0;
			}
			else {
				health -= damage;
			}
		}
		else {
			state = "defense";
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
		state = "defense";
		this.defensePoint += 16;
	}
	
	@Override
	public void resetDefense() {
		state = "standby";
		this.defensePoint = 0;
		
	}
	
	@Override
	public String whichState() {
		return state;
	}


}