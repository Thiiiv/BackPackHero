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
	
	@Override
	public String getRarity() {
		return rarity;
	}

	public int qnuatity() {
		return number;
	}
	
	@Override
	public int height() {
		return 0;
	}

	@Override
	public int width() {
		return 0;
	}

	@Override
	public String isApplyTo(String item) {
		return null;
	}

	@Override
	public String isApplyTo2(String character) {
		return null;
	}
	
	@Override
	public boolean isSelected() {
		return true;
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
