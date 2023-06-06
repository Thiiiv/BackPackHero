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

/**
 * Represents the game data including the inventory, hero, stages, and game state.
 */
public class GameData {
	private final Inventory inventaire;
	private final Hero hero;
	private final Floor actualStage;
	private boolean mapState = false;
	private boolean inventoryState = true;
	private boolean menuState = true;
	private final Floor stage1;
	private final Floor stage2;
	private final Floor stage3;
	private final HashMap<Coordonnees, Item> objectPositions;
	
	/**
     * Constructs a new instance of GameData with default values and initializes the stages and object positions.
     */
	
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
	
	/**
     * Retrieves the hero .
     *
     * @return The hero .
     */
	
	public Hero getHero() {
		return hero;
	}
	
	/**
     * Changes the state of the map and inventory buttons.
     * If the map state is true, it sets the map state to false and the inventory state to true, and vice versa.
     */
	public void changeButtonsState() {
		if (mapState) {
			mapState = false;
			inventoryState = true;
		}
		else {
			mapState = true;
			inventoryState = false;
		}
	}
	
	/**
     * Sets the state of the map button.
     *
     * @param state The state of the map button.
     */
	public void setMapState(boolean state) {
		mapState = state;
	}
	
	/**
     * Sets the state of the menu button.
     *
     * @param state The state of the menu button.
     */
	public void setMenuState(boolean state) {
		menuState = state;
	}
	
	/**
     * Sets the state of the inventory button.
     *
     * @param state The state of the inventory button.
     */
	public void setInventoryState(boolean state) {
		inventoryState = state;
	}
	
	/**
     * Changes the state of the inventory button.
     * If the inventory state is true, it sets the inventory state to false, and vice versa.
     */
	public void changeInventoryState() {
		if (inventoryState) {
			inventoryState = false;
		}
		else {
			inventoryState = true;
		}
	}
	
	/**
     * Retrieves the state of the map button.
     *
     * @return The state of the map button.
     */
	public boolean getMapState() {
		return mapState;
	}
	
	/**
     * Retrieves the state of the menu button.
     *
     * @return The state of the menu button.
     */
	public boolean getMenuState() {
		return menuState;
	}
	
	/**
     * Retrieves the state of the inventory button.
     *
     * @return The state of the inventory button.
     */
	public boolean getInventoryState() {
		return inventoryState;
	}
	
	/**
     * Adds an item to the inventory at the specified position.
     *
     * @param x    The x-coordinate of the item in the inventory.
     * @param y    The y-coordinate of the item in the inventory.
     * @param item The item to add.
     */
	public void addItem(int x, int y, Item item) {
		this.inventaire.add(x, y, item);
	}
	
	/**
     * Retrieves the current floor.
     *
     * @return The current floor.
     */
	public Floor getFloor() {
		return stage1;
	}
	
	/**
     * Retrieves the inventory object.
     *
     * @return The inventory object.
     */
	public Inventory getInventory() {
		return this.inventaire;
	}
	
	/**
     * Checks if a button at the specified coordinates is clicked, based on the current state.
     *
     * @param x        The x-coordinate of the click.
     * @param y        The y-coordinate of the click.
     * @param buttonX  The x-coordinate of the button.
     * @param buttonY  The y-coordinate of the button.
     * @param dimX     The width of the button.
     * @param dimY     The height of the button.
     * @return The button that is clicked ("mapButton", "inventoryButton"), or null if no button is clicked.
     */
	public String clickOnButton(float x, float y, int buttonX, int buttonY, int dimX, int dimY) {
		if (mapState) {
			if (x >= buttonX && x <= buttonX+dimX && y >= buttonY && y <= buttonY+dimY) {
				return "mapButton";
			}
		}
		else if (inventoryState) {
			if (x >= buttonX && x <= buttonX+dimX && y >= buttonY && y <= buttonY+dimY) {
				return "inventoryButton";
			}
		}
		return null;
	}
	
	/**
     * Checks if the menu button at the specified coordinates is clicked.
     *
     * @param x        The x-coordinate of the click.
     * @param y        The y-coordinate of the click.
     * @param buttonX  The x-coordinate of the menu button.
     * @param buttonY  The y-coordinate of the menu button.
     * @param dimX     The width of the menu button.
     * @param dimY     The height of the menu button.
     * @return true if the menu button is clicked, false otherwise.
     */
	public boolean clickOnMenuButton(float x, float y, int buttonX, int buttonY, int dimX, int dimY) {
		return (x >= buttonX && x <= buttonX+dimX && y >= buttonY && y <= buttonY+dimY);
	}
	
	/**
     * Checks if a point on the map is clicked.
     *
     * @param x       The x-coordinate of the click.
     * @param y       The y-coordinate of the click.
     * @param floorX  The x-coordinate of the floor on the screen.
     * @param floorY  The y-coordinate of the floor on the screen.
     * @param roomX   The width of a room on the screen.
     * @param roomY   The height of a room on the screen.
     * @return The coordinates of the clicked room on the map, or null if no room is clicked.
     */
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
	
	/**
     * Retrieves the positions of objects on the map.
     *
     * @return The positions of objects on the map.
     */
	public HashMap<Coordonnees, Item> getObjectsPosition() {
		return objectPositions;
	}
	
	/**
     * Adds an object position to the map.
     *
     * @param item The item to add.
     * @param x    The x-coordinate of the object's position.
     * @param y    The y-coordinate of the object's position.
     * @param x2   The second x-coordinate of the object's position (if applicable).
     * @param y2   The second y-coordinate of the object's position (if applicable).
     */
	public void addObjectPosition(Item item, float x, float y, float x2, float y2) {
		if (!objectPositions.containsKey(new Coordonnees(x, y, x2, y2))) {
			objectPositions.put(new Coordonnees(x, y, x2, y2), item);
		}
	}
	
	/**
	    * Removes an object position from the map.
	    *
	    * @param item The item to remove.
	    * @param x    The x-coordinate of the object's position.
	    * @param y    The y-coordinate of the object's position.
	    * @param x2   The second x-coordinate of the object's position (if applicable).
	    * @param y2   The second y-coordinate of the object's position (if applicable).
	    * @return A message indicating the status of the removal operation.
	    */
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
	
	/**
     * Retrieves the current room where the hero is located.
     *
     * @return The current room where the hero is located, or null if the hero is not in any room.
     */
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
	
	/**
     * Sets the current room where the hero is located.
     *
     * @param x The x-coordinate of the room.
     * @param y The y-coordinate of the room.
     * @return true if the current room is successfully set, false otherwise.
     */
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
	
	/**
     * Checks if an item is clicked on the map.
     *
     * @param x The x-coordinate of the click.
     * @param y The y-coordinate of the click.
     * @return A HashMap containing the coordinates and item of the clicked object,
     *         or null if no item is clicked.
     */
	public HashMap<Coordonnees, Item> clickOnItem(float x, float y) {
		for (var i : objectPositions.keySet()) {
			if (i.isPointInside(x, y)) {
				return new HashMap<Coordonnees, Item>(Map.of(i, objectPositions.get(i)));
			}
		}
		return null;
	}
	
	/**
     * Checks if an item is in the inventory.
     *
     * @param item The item to check.
     * @return true if the item is in the inventory, false otherwise.
     */
	public boolean isItemInInventory(Item item) {
		if (inventaire.get(item) != null) {
			return true;
		}
		return false;
	}
	
	/**
     * Checks if the click is inside the hero's room.
     *
     * @param clickX  The x-coordinate of the click.
     * @param clickY  The y-coordinate of the click.
     * @param height  The height of the game window.
     * @param width   The width of the game window.
     * @return true if the click is inside the hero's room, false otherwise.
     */
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
	
	/**
     * Handles the click on the healer menu.
     *
     * @param clickX The x-coordinate of the click.
     * @param clickY The y-coordinate of the click.
     * @param width  The width of the game window.
     * @param height The height of the game window.
     * @return A string indicating the action performed based on the click:
     *         - "heal" if the heal button is clicked.
     *         - "no-heal" if the "No thanks" button is clicked.
     *         - "null" if no button is clicked.
     */
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