package fr.uge.data;

import java.util.List;
import java.util.Objects;

public class Chestplate {
	private final String getName;
	private final String getRarity;
	private int getDefensePoint;
	
	public Chestplate(String getName,String getRarity,int getDefensePoint) {
		this.getName=Objects.requireNonNull(getName,"Give a name for the Arrow");
		this.getRarity=Objects.requireNonNull(getRarity,"Give a rarity for the Arrow");
		this.getDefensePoint=getDefensePoint;
	
	
		var rarity = List.of("Common", "Uncommon","Rare","Lengendary");
	
		if (rarity.contains(getRarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (getDefensePoint<0) {
			throw new IllegalArgumentException("your Arrow make negative damage");
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
	
	public boolean isSelected() {
		return false;
	}
	
	
}
