package fr.game.data.item;

import java.util.Objects;

public class GoldCoins implements Item {
	
	private int amount = 0;
	private String rarity;
	private String name = "goldcoins";

	public GoldCoins (int amount, String rarity) {
		this.amount = amount;
		this.rarity = rarity;
	}
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getRarity() {
		return rarity;
	}
	
	public int getAmount() {
		return amount;
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
		return false;
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
