package fr.game.data.game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import fr.game.data.*;
import fr.game.data.character.*;
import fr.game.data.item.Item;


public class GameData {
	private final Inventory inventaire;
	private final Hero hero;
	private final int actualStage;
	private boolean mapButtonState = false;
	private boolean inventoryButtonState = false;
	private boolean menuState = true;
	private final Floor stage1;
	private final Floor stage2;
	private final Floor stage3;
	
	public GameData() {
		this.inventaire = new Inventory();
		this.hero = new Hero();
		this.actualStage = 1;
		this.stage1 = new Floor();
		this.stage2 = new Floor();
		this.stage3 = new Floor();
		stage1.add(0, 1, new Corridor());
		
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public void changeMapButtonState() {
		if (mapButtonState) {
			mapButtonState = false;
		}
		else {
			mapButtonState = true;
		}
	}
	
	public void setMapButtonState(boolean state) {
		mapButtonState = state;
	}
	
	public void setMenuState(boolean state) {
		menuState = state;
	}
	
	public void setInventoryButtonState(boolean state) {
		inventoryButtonState = state;
	}
	
	public void changeInventoryButtonState() {
		if (inventoryButtonState) {
			inventoryButtonState = false;
		}
		else {
			inventoryButtonState = true;
		}
	}
	
	public boolean getMapButtonState() {
		return mapButtonState;
	}
	
	public boolean getMenuState() {
		return menuState;
	}
	
	public boolean getInventoryButtonState() {
		return inventoryButtonState;
	}
	
	public void addItem(int x, int y, Item item) {
		inventaire.add(x, y, item);
	}
	
	public Floor getFloor() {
		return stage1;
	}
	
	public Inventory getInventory() {
		return inventaire;
	}
	
	public String clickOnButton(float x, float y, int buttonX, int buttonY, int dimX, int dimY) {
		if (mapButtonState) {
			if (x >= buttonX && x <= buttonX+dimX && y > buttonY && y < buttonY+dimY) {
				return "mapButton";
			}
		}
		else if (inventoryButtonState) {
			if (x >= buttonX && x <= buttonX+dimX && y > buttonY && y < buttonY+dimY) {
				return "inventoryButton";
			}
		}
		return null;
	}
	
	public boolean clickOnMenuButton(float x, float y, int buttonX, int buttonY, int dimX, int dimY) {
		return (x >= buttonX && x <= buttonX+dimX && y > buttonY && y < buttonY+dimY);
	}
	
	
	
}