package essai;

import java.util.Objects;

public class Manastone {
	
	private final String name;
	private final int rarity;
	private int capacity;

	
	public Manastone(String name,int rarity,int capacity) {

			this.name=Objects.requireNonNull(name,"Give a name for the Manastone");
			this.rarity=rarity;
			this.capacity=capacity;
			
			if (rarity<1||rarity>4) {
				throw new IllegalArgumentException("Level of rarity not beteween 1 and 4");
			}

			if (capacity<0) {
				throw new IllegalArgumentException("capacity cannot be negative");
			}
		
	}
	
	public String name(){
		return name;
	}
	
	public int rarity() {
		return rarity;
	}
	
	public int capacity() {
		return capacity;
	}
	
	

}
