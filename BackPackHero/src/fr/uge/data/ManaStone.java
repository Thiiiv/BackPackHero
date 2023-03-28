package fr.uge.data;

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

	public int Number() {
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
	public String IsApplyTo(String item) {
		return null;
	}

	@Override
	public String IsApplyTo2(String character) {
		return null;
	}
	
	@Override
	public boolean isSelected() {
		return true;
	}
}
