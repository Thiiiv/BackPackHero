package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class Chestplate implements Armor{
	private final String getName;
	private final String getRarity;
	private int getDefensePoint;
	
	public Chestplate(String getName,String getRarity,int getDefensePoint) {
		this.getName=Objects.requireNonNull(getName,"Give a name for the ChestPlate");
		this.getRarity=Objects.requireNonNull(getRarity,"Give a rarity for the ChestPlate");
		this.getDefensePoint=getDefensePoint;
	
	
		var rarity = List.of("Common", "Uncommon","Rare","Lengendary");
	
		if (rarity.contains(getRarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (getDefensePoint<0) {
			throw new IllegalArgumentException("Your " + getName + "has negative defense point.");
		}
		
		
			
	}
	
	
	public String getName(){
		return getName;
	}
	
	public String getRarity() {
		return getRarity;
	}
	
	public int getDefensePoint() {
		return getDefensePoint;
	}
	
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(getName+" is "+getRarity);
		builder.append(". The chestplate has "+ getDefensePoint +" defense point .");
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
	
	
}
