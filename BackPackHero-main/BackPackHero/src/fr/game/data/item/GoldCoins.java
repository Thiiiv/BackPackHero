package fr.game.data.item;

public class GoldCoins implements Item {
	
	private int amount = 0;
	private String rarity;
	private String name;

	public GoldCoins (String name, int amount, String rarity) {
		this.amount = amount;
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
		return null;
	}

	@Override
	public String ApplyToCharacter(String character) {
		return null;
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
		return 0;
	}
}
