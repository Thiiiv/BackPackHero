package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class Chestplate implements Armor{
	private final String name;
	private final String rarity;
	private int defensePoint;
	
	public Chestplate(String getName,String getRarity,int getDefensePoint) {
		this.name=Objects.requireNonNull(getName,"Give a name for the ChestPlate");
		this.rarity=Objects.requireNonNull(getRarity,"Give a rarity for the ChestPlate");
		this.defensePoint=getDefensePoint;
	
		var rarity = List.of("Common", "Uncommon","Rare","Lengendary");
	
		if (rarity.contains(getRarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (getDefensePoint<0) {
			throw new IllegalArgumentException("Your " + getName + "has negative defense point.");
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
		return null;
	}

	@Override
	public String ApplyToCharacter(String character) {
		return null;
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public int getSellPrice() {
		return 0;
	}

	@Override
	public int getSize() {
		return 0;
	}
}