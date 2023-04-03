package fr.game.data;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import fr.game.data.character.Monster;

public class Corridor implements Room {
	private final String name = "corridor";
	private final String roomImage = "corridor.png";
	private final ArrayList<Monster> list;
	private boolean isHeroHere = false;
	private String roomIcon;

	public Corridor() {
		this.list = new ArrayList<Monster>();
		this.roomIcon = "corridor.png";
		Random monsterRandom = new Random();
		int nbMonster = monsterRandom.nextInt(3);
		switch (nbMonster) {
		case 0 :
			break;
		case 1:
			this.roomIcon = "monster.png";
			list.add(new Monster());
			break;
		case 2:
			this.roomIcon = "monster.png";
			list.add(new Monster());
			list.add(new Monster());
			break;
		}
	}

	@Override
	public String getName() {
		return "corridor";
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Room room && room.getName().equals(name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, roomImage);
	}

	public boolean isThereMonster() {
		return (!list.isEmpty());
	}
	
	public ArrayList<Monster> getMonsters() {
		return list;
	}
	
	@Override
	public String getRoomIcon() {
		return roomIcon;
	}
	
	@Override
	public boolean isHeroHere() {
		return isHeroHere;
	}
	
	@Override
	public void setHeroHere(boolean hereOrNot) {
		isHeroHere = hereOrNot;
	}
}
