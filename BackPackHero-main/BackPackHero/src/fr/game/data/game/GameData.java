package fr.game.data.game;

import java.util.Random;

import fr.game.data.Inventory;
import fr.game.data.character.Hero;
import fr.game.data.item.Item;


public class GameData {
	private final Inventory inventaire;
	private final Hero hero;
	private final int actualStage;
	
	public GameData() {
		this.inventaire = new Inventory();
		this.hero = new Hero();
		this.actualStage = 0;
	}
	
	public void addItem(int x, int y, Item item) {
		inventaire.add(x, y, item);
	}
	
	public Inventory getInventory() {
		return inventaire;
	}
	
	
	
}