package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class MeleeWeapon implements Weapon{
	private final String getName = "Wooden Sword";
	private final String getRarity;
	private int getAttackPoint;
	private int getEnergyPoint;
	
	
	public MeleeWeapon(String getRarity,int getAttackPoint,int getEnergyPoint){
		//this.getName=Objects.requireNonNull("Wooden Sword","Give a name for the meleeWeapon");
		this.getRarity=Objects.requireNonNull(getRarity,"Give a rarity for the meleeWeapon");
		this.getAttackPoint=getAttackPoint;
		this.getEnergyPoint=getEnergyPoint;
		
		var rarity = List.of("Common", "Uncommon","Rare","Lengendary");
		
		if (rarity.contains(getRarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (getEnergyPoint<0) {
			throw new IllegalArgumentException("getEnergyPoint cannot be negative");
		}
		
		if (getAttackPoint<0) {
			throw new IllegalArgumentException("your weapon make negative damage");
		}
		
	}
	
	
	public String getName(){
		return getName;
	}
	
	public String getRarity() {
		return getRarity;
	}
	
	public int getEnergyPoint() {
		return getEnergyPoint;
	}
	
	public int getAttackPoint() {
		return getAttackPoint;
	}
	
	
	@Override
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(getName+" is "+getRarity);
		builder.append(". The weapon make "+ getAttackPoint +" damage and "+getEnergyPoint+" energycost ");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}


}
