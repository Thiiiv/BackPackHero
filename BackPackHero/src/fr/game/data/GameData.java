package fr.game.data;

import java.util.Random;


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