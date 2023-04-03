package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class Dart implements Weapon{
	private final String name;
	private final String rarity;
	private int attackPoint;
	private int energyPoint;
	private int usage;
	
	public Dart(String getName,String getRarity,int getAttackPoint,int getEnergyPoint, int usage) {
		this.name=Objects.requireNonNull(getName,"Give a name for the dart");
		this.rarity=Objects.requireNonNull(getRarity,"Give a rarity for the dart");
		this.attackPoint=getAttackPoint;
		this.energyPoint=getAttackPoint;
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
		builder.append(name+" is "+rarity);
		builder.append(". The dart make "+ attackPoint +" damage and"+ usage+"usage.");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
	
	@Override
	public String itemImage() {
		return "dart.png";
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Item item
				&& item.getName().equals(name) && item.getRarity() == rarity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, rarity, attackPoint, energyPoint, usage);
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
