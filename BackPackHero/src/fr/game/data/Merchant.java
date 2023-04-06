package fr.game.data;

import java.util.ArrayList;
import java.util.Objects;

import fr.game.data.item.Item;

public class Merchant implements Room {
	private final String name = "merchant";
	private final ArrayList<Item> catalog;
	private final String roomIcon = "merchantIcon.png";
	private boolean isHeroHere = false;
	
	public Merchant() {
		this.catalog = new ArrayList<Item>();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public ArrayList<Item> getCatalog() {
		return catalog;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Room room &&
				room.getName().equals(name);
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, catalog, roomIcon);
	}

	@Override
	public boolean isHeroHere() {
		return isHeroHere;
	}

	@Override
	public void setHeroHere(boolean hereOrNot) {
		isHeroHere = hereOrNot;
	}

	@Override
	public String getRoomIcon() {
		return roomIcon;
	}

}
