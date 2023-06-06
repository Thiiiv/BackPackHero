package fr.game.data.item;

import java.util.List;
import java.util.Objects;

public class ManaStone implements Item {
	
	private int number = 0;
	private String rarity;
	private String name="manastone";
	private final int[][] size = new int[2][1];

	public ManaStone (int number, String rarity) {
		this.number = number;
		this.rarity = Objects.requireNonNull(rarity,"Give a rarity for the manastone");
		
		var list = List.of("Common", "Uncommon","Rare","Lengendary");
        
		if (list.contains(rarity)==false) {
			throw new IllegalArgumentException("This Rarity don't exist");
	    }
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getRarity() {
		return rarity;
	}

	public int quantity() {
		return number;
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
	public boolean isSelected() {
		return true;
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
	public String itemImage() {
		return "manastone.png";
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Item item
				&& item.getName().equals(name) && item.getRarity() == rarity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, rarity, number);
	}
	
	@Override
	public boolean isWeapon() {
		return false;
	}
}