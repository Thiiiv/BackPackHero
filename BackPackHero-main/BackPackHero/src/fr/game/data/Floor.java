package fr.game.data;

public class Floor {
	private final Room[][] floor;
	
	public Floor() {
		this.floor = new Room[5][11];
	}
	
	public void add(Room room, int x, int y) {
		floor[x][y] = room;
	}
}
