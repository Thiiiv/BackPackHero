package fr.game.data.item;

public interface Weapon extends Item{
	
	public String getName();
	public String getRarity();
	public int getAttackPoint();
	public int getEnergyPoint();
	public boolean isSelected();
	public String itemImage();
	public boolean equals(Object o);
	public int hashCode();
	

}

