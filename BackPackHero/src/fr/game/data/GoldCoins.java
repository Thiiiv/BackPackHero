package fr.game.data;

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
}
