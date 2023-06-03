package fr.game.data.item;

public interface Item {
	
	public String getName();
	public String getRarity();
	public int getHeight();
	public int getWidth();
	public String ApplyToItem(String item);
	public String ApplyToCharacter(String character);
	public boolean isSelected();
	public int getPrice();
	public int getSellPrice();
	public int[][] getSize();
	public String itemImage();
	public boolean equals(Object o);
	public int hashCode();
	
}