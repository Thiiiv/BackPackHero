package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class Arrow implements Weapon{
	
	private final String name = "arrow";
	private final String rarity;
	private int attackPoint;
	private int usage;
	private int energyPoint = 0;
	private final int[][] size = new int[1][2];
	
	public Arrow(String rarity,int attackPoint) {
		this.rarity=Objects.requireNonNull(rarity,"Give a rarity for the Arrow");
		this.attackPoint=attackPoint;
		
		var list = List.of("Common", "Uncommon","Rare","Lengendary");
		
		if (list.contains(rarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
		}
		
		if (attackPoint<0) {
			throw new IllegalArgumentException("your Arrow make negative damage");
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
	
	
	
	public String toString() {
		var builder=new StringBuilder();
		builder.append("------\n");
		builder.append(name+" is "+ rarity);
		builder.append(". The Arrow make "+ attackPoint +" damage and"+ usage+"usage.");
		builder.append("\n------\n");
		
		return builder.toString();
	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
	
	@Override
	public String itemImage() {
		return "arrow.png";
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
		return false;
	}
	

}