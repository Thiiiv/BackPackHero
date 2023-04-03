package fr.game.data.item;

public interface Item {
	public String getName();
	public String getRarity();
	public int height();
	public int width();
	public String isApplyTo(String item); //add Item in argument
	public String isApplyTo2(String character); //add Character in argument
	public boolean isSelected();
	public String itemImage();
	public boolean equals(Object o);
	public int hashCode();
	

}
