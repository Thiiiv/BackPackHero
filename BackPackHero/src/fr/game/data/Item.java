package fr.game.data;

public interface Item {
	public String getName();
	public int height();
	public int width();
	public String isApplyTo(String item); //add Item in argument
	public String isApplyTo2(String character); //add Character in argument
	public boolean isSelected();

}
