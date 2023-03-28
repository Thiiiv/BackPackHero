package fr.uge.data;

import java.util.List;
import java.util.Objects;

public class MagicWand implements Weapon{
	private final String getName;
	private final String getRarity;
	private int getAttackPoint;
	private int getEnergyPoint;
	private int getManaPoint;
	
	
	public MagicWand(String getName,String getRarity,int getAttackPoint,int getEnergyPoint,int getManaPoint){
		this.getName=Objects.requireNonNull(getName,"Give a name for the RangedWeapon");
		this.getRarity=Objects.requireNonNull(getRarity,"Give a rarity for the RangedWeapon");
		this.getAttackPoint=getAttackPoint;
		this.getEnergyPoint=getAttackPoint;
		this.getManaPoint=getManaPoint;
		
		var rarity = List.of("Common", "Uncommon","Rare","Lengendary");
		
		if (rarity.contains(getRarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (getEnergyPoint>0) {
			throw new IllegalArgumentException("getEnergyPoint cannot be superior to 0");
		}
		
		if (getAttackPoint<0) {
			throw new IllegalArgumentException("your weapon make negative damage");
		}
		if (getManaPoint<1) {
			throw new IllegalArgumentException("getManaPoint cannot be inferior to 1");
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
	
	public int getManaPoint() {
		return getManaPoint;
	}
	
	
	
	@Override
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(getName+" is "+getRarity);
		builder.append(". The weapon make "+ getAttackPoint +" damage and "+getManaPoint+" manapoint ");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
}
