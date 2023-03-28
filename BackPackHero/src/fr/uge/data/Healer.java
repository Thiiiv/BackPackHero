package fr.uge.data;

import java.util.HashMap;

public class Healer implements RoomMatrice {

	@Override
	public String getName() {
		return "healer";
	}

	@Override
	public int display() {
		return 0;
	}
	
	public HashMap<Integer, String> getCatalog() {
		var catalog = new HashMap<Integer, String>();
		catalog.put(0,"Nothing for me!");
		catalog.put(3,"Remove all curses");
		catalog.put(5,"Heal 25 health");
		catalog.put(8,"Gain 5 max health");
		return catalog;
	}
	
	public void heal() {
		Hero.heal(25);
	}
	
	public void improveHealth() {
		Hero.maxHealth += 5;
	}

}
