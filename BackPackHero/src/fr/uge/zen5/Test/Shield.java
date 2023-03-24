package essai;

import java.util.Objects;

public class Shield implements Weapons{
	private final String name;
	private final int rarity;
	private int energycost;
	private int damage;
	private int protection;
	private  int manacost;
	
	public Shield(String name,int rarity,int energycost,int damage,int protection,int manacost) {
		this.name=Objects.requireNonNull(name,"Give a name for the Shield");
		this.rarity=rarity;
		this.energycost=energycost;
		this.damage=damage;
		this.protection=protection;
		this.manacost=manacost;
		
		if (rarity<1||rarity>4) {
			throw new IllegalArgumentException("Level of rarity not beteween 1 and 4");
		}

		if (energycost<0) {
			throw new IllegalArgumentException("Energycost cannot be negative");
		}
		
		if (damage<0) {
			throw new IllegalArgumentException("your weapon is useless");
		}
		
		if (protection<0) {
			throw new IllegalArgumentException("your cannot have negative protection");
		}
		
		if (manacost<0) {
			throw new IllegalArgumentException("your cannot have negative manacost");
		}
	}
	
	public String name(){
		return name;
	}
	
	public int rarity() {
		return rarity;
	}
	
	public int energycost() {
		return energycost;
	}
	
	public int damage() {
		return damage;
	}
	
	public int protection() {
		return protection;
	}
	
	public int manacost() {
		return manacost;
	}
	
}
