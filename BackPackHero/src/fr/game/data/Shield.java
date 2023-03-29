package fr.game.data;

import java.util.List;
import java.util.Objects;

public class Shield implements Weapon{

	private final String getName;
	private final String getRarity;
	private int getAttackPoint;
	private int getEnergyPoint;
	private int getProtectionPoint;
	
	
	public Shield(String getName,String getRarity,int getAttackPoint,int getEnergyPoint,int getProtectionPoint){
		this.getName=Objects.requireNonNull(getName,"Give a name for the RangedWeapon");
		this.getRarity=Objects.requireNonNull(getRarity,"Give a rarity for the RangedWeapon");
		this.getAttackPoint=getAttackPoint;
		this.getEnergyPoint=getEnergyPoint;
		this.getProtectionPoint=getProtectionPoint;
		
		var rarity = List.of("Common", "Uncommon","Rare","Legendary");
		
		if (rarity.contains(getRarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (getEnergyPoint<0) {
			throw new IllegalArgumentException("getEnergyPoint cannot be negative");
		}
		
		if (getAttackPoint>0) {
			throw new IllegalArgumentException("your shield has attack point");
		}
		
		if (getProtectionPoint<=0) {
			throw new IllegalArgumentException("getProtectionPoint cannot be equal to zero or negative");
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
	
	public int getProtectionPoint() {
		return getProtectionPoint;
	}
	
	
	@Override
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(getName+" is "+getRarity);
		builder.append(". The weapon has "+ getProtectionPoint +" protection point and "+getEnergyPoint+" energycost ");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
	
}
