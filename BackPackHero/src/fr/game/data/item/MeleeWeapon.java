package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class MeleeWeapon implements Weapon, Item{
	private final String name = "Wooden Sword";
	private final String rarity;
	private int attackPoint;
	private int energyPoint;
	
	
	public MeleeWeapon(String getRarity,int getAttackPoint,int getEnergyPoint){
		//this.getName=Objects.requireNonNull("Wooden Sword","Give a name for the meleeWeapon");
		this.rarity=Objects.requireNonNull(getRarity,"Give a rarity for the meleeWeapon");
		this.attackPoint=getAttackPoint;
		this.energyPoint=getEnergyPoint;
		
		var rarity = List.of("Common", "Uncommon","Rare","Lengendary");
		
		if (rarity.contains(getRarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (getEnergyPoint<0) {
			throw new IllegalArgumentException("EnergyPoint cannot be negative");
		}
		
		if (getAttackPoint<0) {
			throw new IllegalArgumentException("your weapon make negative damage");
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
	
	
	@Override
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(name+" is "+rarity);
		builder.append(". The weapon make "+ attackPoint +" damage and "+ energyPoint+" energycost ");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
	
	@Override
	public String itemImage() {
		return "Wooden_sword.png";
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Item item
				&& item.getName().equals(name) && item.getRarity() == rarity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, rarity, attackPoint, energyPoint);
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String isApplyTo(String item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String isApplyTo2(String character) {
		// TODO Auto-generated method stub
		return null;
	}


}
