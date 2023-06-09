package fr.game.data;

import java.util.HashMap;
import java.util.Objects;

import fr.game.data.character.Hero;

public class Healer implements Room {
	private final String name = "healer";
	private final HashMap<Integer, String> catalog;
	private final String roomIcon = "healerIcon.png";
	private final String healerImg = "healer.png";
	private boolean isHeroHere = false;

	public Healer() {
		this.catalog = new HashMap<Integer, String>();
		catalog.put(0,"Nothing for me!\n");
		catalog.put(3,"Remove all curses\n");
		catalog.put(5,"Heal 25 health\n");
		catalog.put(8,"Gain 5 max health");
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public HashMap<Integer, String> getCatalog() {
		return catalog;
	}
	
	public void heal(Hero hero) {
		hero.heal(25);
	}
	
	public void improveHealth(Hero hero) {
		hero.improveHealth();
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
	
	public String getImage() {
		return healerImg;
	}

}
