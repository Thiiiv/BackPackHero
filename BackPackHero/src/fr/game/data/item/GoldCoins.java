package fr.game.data.item;

import java.util.Objects;

public class GoldCoins implements Item {
	
	private int amount = 0;
	private String rarity;
	private final String name = "goldcoins";

	public GoldCoins (int amount, String rarity) {
		this.amount = amount;
		this.rarity = rarity;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getRarity() {
		return rarity;
	}
	
	public int getAmount() {
		return amount;
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
		return false;
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
		return 1;
	}
	
	@Override
	public String itemImage() {
		return "goldcoins.png";
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Item item
				&& item.getName().equals(name) && item.getRarity() == rarity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(amount, rarity);
	}
}