package fr.game.data;

import java.util.ArrayList;

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
	
	public ArrayList<Room> getAllRooms() {
		var list = new ArrayList<Room>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 11; j++) {
				list.add(floor[i][j]);
			}
		}
		return list;
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
	    
	    // Ajouter les différents types de pièces à la matrice
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            if (floor[i][j] instanceof Corridor) {
	                matrice[i][j] = '■';
	                if (((Corridor)floor[i][j]).isThereMonster()) {
	                    matrice[i][j] = Character.forDigit(((Corridor)floor[i][j]).getMonsters().size(), 10);
	                }
	            } else if (floor[i][j] instanceof Treasure) {
	                matrice[i][j] = 'T';
	            } else if (floor[i][j] instanceof Healer) {
	                matrice[i][j] = 'H';
	            } else if (floor[i][j] instanceof ExitDoor) {
	                matrice[i][j] = 'E';
	            } else if (floor[i][j] instanceof Merchant) {
	                matrice[i][j] = 'M';
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
