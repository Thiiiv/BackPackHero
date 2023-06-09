package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class Dart implements Weapon{
	private final String name = "dart";
	private final String rarity;
	private int attackPoint;
	private int energyPoint;
	private int usage;
	private final int[][] size = new int[2][1];
	
	public Dart(String rarity,int attackPoint,int energyPoint, int usage) {
		this.rarity=Objects.requireNonNull(rarity,"Give a rarity for the dart");
		this.attackPoint=attackPoint;
		this.energyPoint=energyPoint;
		this.usage=usage;
		
		var list = List.of("Common", "Uncommon","Rare","Lengendary");
		
		if (list.contains(rarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (energyPoint<0 || energyPoint>0) {
			throw new IllegalArgumentException("getEnergyPoint have to be 0");
		}
		
		if (attackPoint<0) {
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
		return item;
	}

	@Override
	public String ApplyToCharacter(String character) {
		return character;
	}

	@Override
	public int getPrice() {
		return 1;
	}

	@Override
	public int getSellPrice() {
		return 1;
	}

	@Override
	public int[][] getSize() {
		return size;
	}

	@Override
	public boolean isWeapon() {
		return true;
	}
}