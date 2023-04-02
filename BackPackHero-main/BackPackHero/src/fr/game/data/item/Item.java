package fr.game.data.item;

public interface Item {
	public String getName();
	public int getHeight();
	public int getWidth();
	public String ApplyToItem(String item); //add Item in argument
	public String ApplyToCharacter(String character); //add Character in argument
	public boolean isSelected();
	public int getPrice();
	public int getSellPrice();
	public int getSize();

}
