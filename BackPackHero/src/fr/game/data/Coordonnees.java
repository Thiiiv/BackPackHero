package fr.game.data;

public record Coordonnees(int x, int y) {
	
	public Coordonnees {
		if (x < 0) {
			throw new IllegalArgumentException("le champs x ne peut pas être null");
		}
		if (y < 0) {
			
		}
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
}
