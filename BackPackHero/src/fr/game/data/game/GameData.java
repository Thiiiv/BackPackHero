package fr.game.data.game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.game.data.*;
import fr.game.data.character.*;
import fr.game.data.item.Item;
import fr.game.data.item.MeleeWeapon;
import fr.game.data.Coordonnees;


public class GameData {
	private final Inventory inventaire;
	private final Hero hero;
	private final Floor actualStage;
	private boolean mapButtonState = false;
	private boolean inventoryButtonState = false;
	private boolean menuState = true;
	private final Floor stage1;
	private final Floor stage2;
	private final Floor stage3;
	private final HashMap<Coordonnees, Item> objectPositions;
	
	public GameData() {
		this.inventaire = new Inventory();
		this.hero = new Hero();
		this.stage1 = new Floor();
		this.stage2 = new Floor();
		this.stage3 = new Floor();
		this.actualStage = stage1;
		this.objectPositions = new HashMap<Coordonnees, Item>();
		stage1.add(2, 0, new Corridor(0));
		stage1.add(2, 1, new Corridor(0));
		stage1.add(2, 2, new Corridor(2));
		stage1.add(0, 2, new Corridor(0));
		stage1.add(1, 2, new Corridor(0));
		stage1.add(3, 2, new Corridor(0));
		stage1.add(4, 2, new Corridor(0));
		stage1.add(0, 3, new Treasure());
		stage1.add(2, 3, new Corridor(0));
		stage1.add(4, 3, new Healer());
		stage1.add(2, 4, new Corridor(0));
		stage1.add(2, 5, new Corridor(0));
		stage1.add(3, 5, new Corridor(1));
		stage1.add(4, 5, new Corridor(0));
		stage1.add(0, 6, new Treasure());
		stage1.add(1, 6, new Corridor(0));
		stage1.add(4, 6, new Corridor(0));
		stage1.add(1, 7, new Corridor(0));
		stage1.add(2, 7, new Corridor(0));
		stage1.add(4, 7, new Corridor(0));
		stage1.add(0, 8, new ExitDoor());
		stage1.add(2, 8, new Corridor(0));
		stage1.add(3, 8, new Corridor(0));
		stage1.add(4, 8, new Corridor(0));
		stage1.add(0, 9, new Corridor(0));
		stage1.add(2, 9, new Corridor(0));
		stage1.add(4, 9, new Corridor(0));
		stage1.add(0, 10, new Corridor(0));
		stage1.add(1, 10, new Corridor(0));
		stage1.add(2, 10, new Corridor(1));
		stage1.add(4, 10, new Merchant());
		stage1.getRoom(2, 0).setHeroHere(true);
		/*for (var i : stage1.findShortestPath(actualStage, 0, 2, 3, 4)) {
			System.out.println(i.getName());
		}*/
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
		this.inventaire.add(x, y, item);
	}
	
	public Floor getFloor() {
		return stage1;
	}
	
	public Inventory getInventory() {
		return this.inventaire;
	}
	
	public String clickOnButton(float x, float y, int buttonX, int buttonY, int dimX, int dimY) {
		if (mapButtonState) {
			if (x >= buttonX && x <= buttonX+dimX && y >= buttonY && y <= buttonY+dimY) {
				return "mapButton";
			}
		}
		else if (inventoryButtonState) {
			if (x >= buttonX && x <= buttonX+dimX && y >= buttonY && y <= buttonY+dimY) {
				return "inventoryButton";
			}
		}
		return null;
	}
	
	public boolean clickOnMenuButton(float x, float y, int buttonX, int buttonY, int dimX, int dimY) {
		return (x >= buttonX && x <= buttonX+dimX && y >= buttonY && y <= buttonY+dimY);
	}
	
	public Coordonnees clickOnMap(float x, float y, float floorX, float floorY, int roomX, int roomY) {
		if (x >= floorX && x <= floorX+roomX*11 && y >= floorY && y <= floorY+roomY*5) {
			for (var i = 0; i < 5; i++) {
				for (var j = 0; j < 11; j++) {
					if (stage1.getRoom(i, j) != null) {
						System.out.println("Dans clickOnMap : roomX = " + roomX + " roomY = " + roomY);
						System.out.println("j : " + j + " i : " + i);
						System.out.println("roomX*j : " + roomX*j + " roomY*i : " + roomY*i);
						System.out.println("roomX*j+1 : " + roomX*j+1 + " roomY*i+1 : " + roomY*i+1);
						if (x >= floorX+roomX*j && x <= floorX+roomX*(j+1) && y >= floorY+roomY*i && y <= floorY+roomY*(i+1)) {
							return new Coordonnees(i, j);
						}
					}
				}
			}
		}
		return null;
		
	}
	
	public HashMap<Coordonnees, Item> getObjectsPosition() {
		return objectPositions;
	}
	
	public void addObjectPosition(Item item, float x, float y, float x2, float y2) {
		if (!objectPositions.containsKey(new Coordonnees(x, y, x2, y2))) {
			objectPositions.put(new Coordonnees(x, y, x2, y2), item);
		}
	}
	
	public String removeObjectPosition(Item item, float x, float y, float x2, float y2) {
		if (objectPositions.containsKey(new Coordonnees(x, y, x2, y2))) {
			if (objectPositions.get(new Coordonnees(x, y, x2, y2)).equals(item)) {
				objectPositions.remove(new Coordonnees(x, y, x2, y2));
				return "La position de l'objet a été retiré sans problème";
			}
			else {
				return "La position indiquée ne correspond pas à l'objet donnée en paramètre";
			}
		}
		return "Il y a eu un problème";
	}
	
	public Room getCurrentRoom() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 11; j++) {
				if (actualStage.getRoom(i, j) != null) {
					if (actualStage.getRoom(i, j).isHeroHere()) {
						return actualStage.getRoom(i, j);
					}
				}
			}
		}
		return null;
	}
	
	public boolean setCurrentRoom(int x, int y) {
		if (actualStage.getRoom(y, x) == null) {
			return false;
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 11; j++) {
				if (actualStage.getRoom(i, j) != null) {
					if (actualStage.getRoom(i, j).isHeroHere()) {
						actualStage.getRoom(i, j).setHeroHere(false);
					}
				}
			}
		}
		if (actualStage.getRoom(y, x) != null) {
			actualStage.getRoom(y, x).setHeroHere(true);
		}
		return true;
	}
	
	public HashMap<Coordonnees, Item> clickOnItem(float x, float y) {
		for (var i : objectPositions.keySet()) {
			if (i.isPointInside(x, y)) {
				return new HashMap<Coordonnees, Item>(Map.of(i, objectPositions.get(i)));
			}
		}
		return null;
	}
	
	public boolean isItemInInventory(Item item) {
		if (inventaire.get(item) != null) {
			return true;
		}
		return false;
	}
	
	public boolean isClickedInRoom(float clickX, float clickY,float height,float width) {
        var loader = new ImageLoader("data", List.of(Path.of("hero-4.png").toString()));
        float dimX = (float) (loader.image("hero-4.png").getWidth() * 1.5);
        float dimY = (float) (loader.image("hero-4.png").getHeight() * 1.5);
        float posX = (float) (width - (80 + dimX));
        float posY = height - loader.image("hero-4.png").getHeight() * 2;
        System.out.println("clickX : " + clickX + " clickY : " + clickY);
        System.out.println("dimX : " + dimX + " dimY : " + dimY + " posX : " + posX + " posY : " + posY);
        
        if (clickX >= posX && clickX <= posX + dimX &&
            clickY >= posY && clickY <= posY + dimY) {
            return true;
        }
        return false; 
    }
	
	public String clickOnMenuHealer(float clickX, float clickY, float width, float height) {
        // Vérification pour le bouton "Heal 10 PV pour 5 coins"
        float healButtonX = (width / 2) - 200;
        float healButtonY = (height / 2) - 100;
        float healButtonWidth = 400;
        float healButtonHeight = 200;

        if (clickX >= healButtonX && clickX <= healButtonX + healButtonWidth &&
            clickY >= healButtonY && clickY <= healButtonY + healButtonHeight) {
            //verification de coin à faire
            this.hero.heal(25);
            return "heal"; 
        }

        // Vérification pour le bouton "Non merci"
        float noThanksButtonX = (width / 2) - 200;
        float noThanksButtonY = (height / 2) + 120;
        float noThanksButtonWidth = 400;
        float noThanksButtonHeight = 100;
        
        System.out.println("\nclickX : " + clickX + " clickY : " + clickY);
        System.out.println("Position du bouton : " + "x = " + noThanksButtonX + " y = " + noThanksButtonY + " x2 = " + (noThanksButtonX + noThanksButtonWidth) + " y2 = " + (noThanksButtonY + noThanksButtonHeight));

        if (clickX >= noThanksButtonX && clickX <= noThanksButtonX + noThanksButtonWidth &&
            clickY >= noThanksButtonY && clickY <= noThanksButtonY + noThanksButtonHeight) {
            return "no-heal"; 
        }

        return "null"; 
    }
}