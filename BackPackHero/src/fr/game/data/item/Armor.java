package fr.game.data.item;

public interface Armor extends Item{
	
	public String getName();
	public String getRarity();
	public void bindCharactert();
	public void applyeffects();
	public boolean isSelected();
	public String itemImage();
}