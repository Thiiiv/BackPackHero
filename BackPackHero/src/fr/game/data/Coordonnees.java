package fr.game.data;

public record Coordonnees(int x, int y) {
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
}
