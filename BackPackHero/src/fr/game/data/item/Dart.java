package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class Dart implements Weapon{
	private final String getName;
	private final String getRarity;
	private int getAttackPoint;
	private int getEnergyPoint;
	private int usage;
	
	public Dart(String getName,String getRarity,int getAttackPoint,int getEnergyPoint, int usage) {
		this.getName=Objects.requireNonNull(getName,"Give a name for the dart");
		this.getRarity=Objects.requireNonNull(getRarity,"Give a rarity for the dart");
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
			throw new IllegalArgumentException("your dart make negative damage");
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
	
	public void isdestroyed() {
		
	}
	
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(getName+" is "+getRarity);
		builder.append(". The dart make "+ getAttackPoint +" damage and"+ usage+"usage.");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
	
	
	

}
