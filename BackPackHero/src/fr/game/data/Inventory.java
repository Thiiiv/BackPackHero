package fr.game.data;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private final Item[][] inventory;
	
	public Inventory() {
		this.inventory = new Item[3][5];
	}
	public List<Item> showContains() {
		List<Item> list = new ArrayList<>();
		for (var ligne = 0; ligne < inventory.length; ligne++) {
			for (var colonne = 0; colonne < inventory[0].length; colonne++) {
				list.add(inventory[ligne][colonne]);
			}
		}
		return list;
	}
	
	public String add(int x, int y, Item item) {
		if (x < inventory.length && y <= inventory[0].length) {
			if (inventory[x][y] != null) {
				inventory[x][y] = item;
				return "L'item " + item + " a bien été ajouté à l'inventaire";
			}
		}
		return "L'item n'a pas pu être ajouté";
	}
	
	@Override
	public String toString() {
		List<Item> list = this.showContains();
		StringBuilder sb = new StringBuilder();
		sb.append("Contenu de l'inventaire :\n");
		for (var i : list) {
			sb.append(i + "\n");
		}
		return sb.toString();
	}
}
