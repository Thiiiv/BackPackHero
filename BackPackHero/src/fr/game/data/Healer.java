package fr.game.data;

import java.util.HashMap;

import fr.game.data.character.Hero;

public class Healer implements Room {

	@Override
	public String getName() {
		return "guérisseur";
	}

	@Override
	public int display() {
		return 0;
	}
	
	public HashMap<Integer, String> getCatalog() {
		var catalog = new HashMap<Integer, String>();
		catalog.put(0,"Nothing for me!\n");
		catalog.put(3,"Remove all curses\n");
		catalog.put(5,"Heal 25 health\n");
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
