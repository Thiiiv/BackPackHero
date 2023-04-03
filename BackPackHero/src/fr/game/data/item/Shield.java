package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class Shield implements Weapon{

	private final String name;
	private final String rarity;
	private int attackPoint;
	private int energyPoint;
	private int protectionPoint;
	
	
	public Shield(String getName,String getRarity,int getAttackPoint,int getEnergyPoint,int getProtectionPoint){
		this.name=Objects.requireNonNull(getName,"Give a name for the RangedWeapon");
		this.rarity=Objects.requireNonNull(getRarity,"Give a rarity for the RangedWeapon");
		this.attackPoint=getAttackPoint;
		this.energyPoint=getAttackPoint;
		this.protectionPoint=getProtectionPoint;
		
		var rarity = List.of("Common", "Uncommon","Rare","Lengendary");
		
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
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public String getRarity() {
		return rarity;
	}
	
	public int getEnergyPoint() {
		return energyPoint;
	}
	
	public int getAttackPoint() {
		return attackPoint;
	}
	
	public int getProtectionPoint() {
		return protectionPoint;
	}
	
	
	@Override
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(name+" is "+rarity);
		builder.append(". The weapon has "+ protectionPoint +" protection point and "+energyPoint+" energycost ");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
	
	@Override
	public String itemImage() {
		return "shield.png";
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Item item
				&& item.getName().equals(name) && item.getRarity() == rarity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, rarity, attackPoint, energyPoint, protectionPoint);
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
		return 3;
	}

	@Override
	public int getSellPrice() {
		return 2;
	}

	@Override
	public int getSize() {
		return 3;
	}
}