package fr.uge.data;

import java.util.List;
import java.util.Objects;

public class Arrow implements Weapon{
	
	private final String getName;
	private final String getRarity;
	private int getAttackPoint;
	private int getEnergyPoint;
	private int usage;
	
	public Arrow(String getName,String getRarity,int getAttackPoint,int getEnergyPoint, int usage) {
		this.getName=Objects.requireNonNull(getName,"Give a name for the Arrow");
		this.getRarity=Objects.requireNonNull(getRarity,"Give a rarity for the Arrow");
		this.getAttackPoint=getAttackPoint;
		this.getEnergyPoint=getAttackPoint;
		this.usage=usage;
		
		var rarity = List.of("Common", "Uncommon","Rare","Lengendary");
		
		if (rarity.contains(getRarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (getEnergyPoint<0||getEnergyPoint>0) {
			throw new IllegalArgumentException("getEnergyPoint have to be 0");
		}
		
		if (getAttackPoint<0) {
			throw new IllegalArgumentException("your Arrow make negative damage");
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
	
	public int usage(){
		return usage;
	}
	
	public void isused() {
		usage=usage-1;
	}
	
	
	
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(getName+" is "+getRarity);
		builder.append(". The Arrow make "+ getAttackPoint +" damage and"+ usage+"usage.");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
	

}
