package fr.game.data;

import java.util.ArrayList;
import java.util.List;

public class Floor {
	private final Room[][] floor;
	
	public Floor() {
		this.floor = new Room[5][11];
	}
	
	public void add(int x, int y, Room room) {
		floor[x][y] = room;
	}
	
	public Coordonnees getRoomPosition(Room room) {
		if (room == null) {
			return null;
		}
		for (var i = 0; i < floor.length; i++) {
			for (var j = 0; j < floor[0].length; j++) {
				if (floor[i][j] != null) {
					if (floor[i][j].equals(room)) {
						return new Coordonnees(j, i);
					}
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
	
	public List<Room> findShortestPath(Floor floor, int startX, int startY, int targetX, int targetY) {
	    int rows = 5;
	    int cols = 11;

	    int[][] distances = new int[rows][cols];
	    boolean[][] visited = new boolean[rows][cols];

	    // Initialisation des distances
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            distances[i][j] = Integer.MAX_VALUE;
	        }
	    }
	    distances[startY][startX] = 0;

	    // File d'attente pour le parcours en largeur
	    List<Room> queue = new ArrayList<>();
	    queue.add(floor.getRoom(startX, startY));

	    // Recherche du chemin le plus court
	    while (!queue.isEmpty()) {
	        Room currentRoom = queue.remove(0);
	        int currentX = floor.getRoomPosition(currentRoom).x();
	        int currentY = floor.getRoomPosition(currentRoom).y();

	        // Arrêt lorsque la salle cible est atteinte
	        if (currentX == targetX && currentY == targetY) {
	            break;
	        }

	        // Vérification des salles adjacentes
	        for (int i = -1; i <= 1; i++) {
	            for (int j = -1; j <= 1; j++) {
	                if (Math.abs(i) + Math.abs(j) == 1) {
	                    int newX = currentX + j;
	                    int newY = currentY + i;

	                    // Vérification des limites de la matrice
	                    if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
	                        Room adjacentRoom = floor.getRoom(newX, newY);

	                        // Vérification si la salle est accessible
	                        if (adjacentRoom != null && !visited[newY][newX]) {
	                            // Mise à jour de la distance si nécessaire
	                            int newDistance = distances[currentY][currentX] + 1;
	                            if (newDistance < distances[newY][newX]) {
	                                distances[newY][newX] = newDistance;
	                                queue.add(adjacentRoom);
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        visited[currentY][currentX] = true;
	    }

	    // Reconstitution du chemin à partir des distances
	    List<Room> shortestPath = new ArrayList<>();
	    int currentX = targetX;
	    int currentY = targetY;

	    while (currentX != startX || currentY != startY) {
	        shortestPath.add(0, floor.getRoom(currentX, currentY));

	        // Recherche de la salle précédente avec la plus petite distance
	        boolean foundPreviousRoom = false; // Ajout de la variable de contrôle
	        for (int i = -1; i <= 1; i++) {
	            for (int j = -1; j <= 1; j++) {
	                if (Math.abs(i) + Math.abs(j) == 1) {
	                    int newX = currentX + j;
	                    int newY = currentY + i;

	                    // Vérification des limites de la matrice
	                    if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
	                        Room adjacentRoom = floor.getRoom(newX, newY);

	                        // Vérification si la salle est accessible
	                        if (adjacentRoom != null && distances[newY][newX] == distances[currentY][currentX] - 1) {
	                        	currentX = newX;
	                        	currentY = newY;
	                        	foundPreviousRoom = true; // Marquer la salle précédente comme trouvée
	                        	break;
	                        }
	                    }
	                }
	                if (foundPreviousRoom) { // Sortir de la boucle externe si la salle précédente est trouvée
	                    break;
	                }
	            }
	        }

	        visited[currentY][currentX] = true;
	    }

	    shortestPath.add(0, floor.getRoom(startX, startY));

	    return shortestPath;
	}
}
