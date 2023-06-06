package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class Chestplate implements Armor{
	private final String name="Chestplate";
	private final String rarity;
	private int defensePoint;
	private final int[][] size = new int[2][1];
	
	public Chestplate(String rarity,int getDefensePoint) {
		this.rarity=Objects.requireNonNull(rarity,"Give a rarity for the ChestPlate");
		this.defensePoint=getDefensePoint;
	
		var list = List.of("Common", "Uncommon","Rare","Lengendary");
        
		if (list.contains(rarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
	    }
		
		if (getDefensePoint<0) {
			throw new IllegalArgumentException("Your " + name + "has negative defense point.");
		}	
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public String getRarity() {
		return rarity;
	}
	
	public int getDefensePoint() {
		return defensePoint;
	}
	
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(name+" is "+ rarity);
		builder.append(". The chestplate has "+ defensePoint +" defense point .");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}


	@Override
	public void bindCharactert() {
		
	}


	@Override
	public void applyeffects() {
		
	}
	
	@Override
	public String itemImage() {
		return "chestplate.png";
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Item item
				&& item.getName().equals(name) && item.getRarity() == rarity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, rarity, defensePoint);
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public String ApplyToItem(String item) {
		return item;
	}

	@Override
	public String ApplyToCharacter(String character) {
		return character;
	}

	@Override
	public int getPrice() {
		return 8;
	}

	@Override
	public int getSellPrice() {
		return 6;
	}

	@Override
	public int[][] getSize() {
		return size;
	}

	@Override
	public boolean isWeapon() {
		return false;
	}
}