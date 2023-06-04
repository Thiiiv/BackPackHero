package fr.game.data;

public record Coordonnees(float x1, float y1, float x2, float y2) {
	
	public Coordonnees(int x, int y) {
		this(x, y, 0, 0);
	}
	public Coordonnees {
		if (x1 < 0 || x2 < 0) {
			throw new IllegalArgumentException("le champs x ne peut pas être null");
		}
		if (y1 < 0 || y2 < 0) {
			throw new IllegalArgumentException("le champs y ne peut pas être null");
		}
	}
	public int x() {
		return (int) x1;
	}
	
	public int y() {
		return (int) y1;
	}
	
	public boolean isPointInside(float x, float y) {
		if (x > x1 && x < x2 && y < y2 && y > y1) {
			return true;
		}
		return false;
	}
}
