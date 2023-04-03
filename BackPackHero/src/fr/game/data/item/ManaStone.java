package fr.game.data.item;

import java.util.Objects;

public class ManaStone implements Item {
	
	private int number = 0;
	private String rarity;
	private String name;

	public ManaStone (String name ,int number, String rarity) {
		this.number = number;
		this.rarity = rarity;
		this.name = name;
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
	public int getSize() {
		return 1;
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
}
