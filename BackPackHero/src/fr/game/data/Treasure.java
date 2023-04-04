package fr.game.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.game.data.item.Item;

public class Treasure implements Room {
	private final String name = "treasure";
	private final ArrayList<Item> contenu;
	private final String roomIcon = "treasure.png";
	private boolean isHeroHere = false;
	
	public Treasure() {
		this.contenu = new ArrayList<Item>();
	}
	@Override
	public String getName() {
		return "treasure";
	}
	
	public List<Item> generateItems() {
		for (var i = 0; i < 11; i++) {
			contenu.add(null);
		}
		return contenu;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Room room &&
				room.getName().equals(name);
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, contenu, roomIcon);
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
