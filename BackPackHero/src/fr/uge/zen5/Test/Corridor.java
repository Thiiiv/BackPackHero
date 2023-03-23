package fr.uge.zen5.Test;

public class Corridor implements StageElement {
	private final int type;
	public Corridor() {
		this.type = 0;
	}
	@Override
	public int typeOfZone() {
		return 0;
	}
}
