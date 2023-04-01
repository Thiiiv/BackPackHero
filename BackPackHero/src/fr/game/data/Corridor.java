package fr.game.data;

public class Corridor implements Room {

	@Override
	public String getName() {
		return "corridor";
	}

	@Override
	public int display() {
		return 0;
	}
}
