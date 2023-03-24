package essai;

import java.util.Objects;

public class Chestplate implements Armor{
	
	private final String name;
	private final int rarity;
	private int armorpoint;

	public Chestplate(String name,int rarity,int armorpoint) {
		this.name=Objects.requireNonNull(name,"Give a name for the Chestplate");
		this.rarity=rarity;
		this.armorpoint=armorpoint;
		
		if (rarity<1||rarity>4) {
			throw new IllegalArgumentException("Level of rarity not beteween 1 and 4");
		}

		if (armorpoint<0) {
			throw new IllegalArgumentException("Armorpoint cannot be negative");
		}
	}
	
	public String name(){
		return name;
	}
	
	public int rarity() {
		return rarity;
	}
	
	public int armorpoint() {
		return armorpoint;
	}	

}
