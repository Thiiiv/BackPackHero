package fr.game.data;

import java.util.Objects;

public class ExitDoor implements Room {
	private final String name = "exitdoor";
	private int state = 0;
	private final String roomIcon = "exitdoor.png";
	private boolean isHeroHere = false;
	
	public ExitDoor() {}
	
	@Override
	public String getName() {
		return name;
	}	
	public void close() {
		state = 0;
	}
	
	public void open() {
		state = 1;
	}
	
	public int state() {
		return state;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Room room &&
				room.getName().equals(name);
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, state, roomIcon);
	}
	
	@Override
	public boolean isHeroHere() {
		return isHeroHere;
	}
	
	@Override
	public void setHeroHere(boolean hereOrNot) {
		isHeroHere = hereOrNot;
	}

	@Override
	public String getRoomIcon() {
		return roomIcon;
	}
	
}
