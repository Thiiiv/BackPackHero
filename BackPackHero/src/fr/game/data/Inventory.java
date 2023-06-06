package fr.game.data;

import java.util.ArrayList;
import java.util.List;

import fr.game.data.item.Item;

public class Inventory {
	private static final int MAX_ROWS = 3;
    private static final int MAX_COLS = 5;
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
		if (x < inventory.length && y < inventory[0].length) {
			if (inventory[x][y] == null) {
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
	
	public Coordonnees get(Item item) {
		for (var i = 0; i < inventory.length; i++) {
			for (var j = 0; j < inventory[0].length; j++) {
				if (inventory[i][j] == item) {
					return new Coordonnees(j, i);
				}
			}
		}
		return null;
	}
	
	/*public boolean addItemToInventory(Item item) {
	    int height = item.getHeight();
	    int width = item.getWidth();
	    // Vérification de la place disponible
	    for (int i = 0; i <= 3 - height; i++) {
	        for (int j = 0; j <= 4 - width; j++) {
	            boolean emptySpace = true;
	            // Vérification si toutes les cases de la taille de l'objet sont vides
	            for (int k = i; k < i + height; k++) {
	                for (int l = j; l < j + width; l++) {
	                    if (inventory[k][l] != null) {
	                        emptySpace = false;
	                        break;
	                    }
	                }
	                if (!emptySpace) {
	                    break;
	                }
	            }
	            if (emptySpace) {
	                // Ajout de l'objet
	                for (int k = i; k < i + height; k++) {
	                    for (int l = j; l < j + width; l++) {
	                        inventory[k][l] = item;
	                    }
	                }
	                System.out.println("Objet ajouté à l'inventaire !");
	                return true;
	            }
	        }
	    }
	    System.out.println("Pas assez de place dans l'inventaire.");
	    return false;
	}*/
	
	public boolean removeItemFromInventory(Item item) {
	    boolean itemFound = false;
	    // Recherche de l'objet dans l'inventaire
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 5; j++) {
	            if (inventory[i][j] == item) {
	                itemFound = true;
	                inventory[i][j] = null;
	            }
	        }
	    }
	    if (itemFound) {
	        System.out.println("Objet retiré de l'inventaire !");
	        return true;
	    } else {
	        System.out.println("Objet non trouvé dans l'inventaire.");
	        return false;
	    }
	}
	
	public boolean removeItem(int row, int col) {
	    if (row < 0 || row >= MAX_ROWS || col < 0 || col >= MAX_COLS) {
	        System.out.println("Position invalide !");
	        return false;
	    }
	    
	    Item item = inventory[row][col];
	    if (item == null) {
	        System.out.println("Il n'y a pas d'objet à cette position.");
	        return false;
	    }
	    
	    var size = item.getSize();
	    for (int r = row; r < row + size.length; r++) {
	        for (int c = col; c < col + size[0].length; c++) {
	            inventory[r][c] = null;
	        }
	    }
	    
	    System.out.println("Objet retiré : " + item.getName());
	    return true;
	}
	
	public Item getFromXY(int x, int y) {
		return this.inventory[x][y];
	}
}