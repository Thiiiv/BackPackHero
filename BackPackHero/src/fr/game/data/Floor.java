package fr.game.data;

public class Floor {
	private final Room[][] floor;
	
	public Floor() {
		this.floor = new Room[5][11];
	}
	
	public void add(int x, int y, Room room) {
		floor[x][y] = room;
	}
	
	public Coordonnees getRoomPosition(Room room) {
		for (var i = 0; i < floor.length; i++) {
			for (var j = 0; j < floor[0].length; j++) {
				if (floor[i][j].equals(room)) {
					return new Coordonnees(j, i);
				}
			}
		}
		return null;
	}
	
	public Room getRoom(int x, int y) {
		return floor[x][y];
	}
	
	public void eachFloor() {
	    int rows = 5;
	    int cols = 11;

	    // Créer une matrice vide de 5x11
	    char[][] matrice = new char[rows][cols];

	    // Initialiser chaque cellule de la matrice avec un espace vide
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            matrice[i][j] = ' ';
	        }
	    }

	    // Mettre à jour la matrice avec les salles
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            if (floor[i][j] == null) {
	                // La cellule est vide, ne rien faire
	            } else if (floor[i][j] instanceof Room) {
	                // La cellule contient une instance de Room, mettre à jour avec un symbole approprié
	                matrice[i][j] = '■';
	            }
	        }
	    }

	    // Afficher la matrice dans la console
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            System.out.print("|" + matrice[i][j]);
	        }
	        System.out.println("|");
	        for (int j = 0; j < cols; j++) {
	            System.out.print("+-");
	        }
	        System.out.println("+");
	    }
	}
}
