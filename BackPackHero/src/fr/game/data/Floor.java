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
}
