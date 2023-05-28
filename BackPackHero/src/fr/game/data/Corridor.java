package fr.game.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.game.data.character.Monster;
import fr.game.data.character.RatWolf;
import fr.game.data.character.SmallRatWolf;

public class Corridor implements Room {
	private final String name = "corridor";
	private final String roomImage = "corridor.png";
	private final ArrayList<Monster> list;
	private boolean isHeroHere = false;
	private String roomIcon;
	private final List<String> possibleMonsters = List.of("RatWolf", "SmallRatWolf");

	public Corridor(int nbMonster) {
		this.list = new ArrayList<Monster>();
		this.roomIcon = "corridor.png";
		var choiceMonster = new Random();
		var Monster = choiceMonster.nextInt(2);
		switch (nbMonster) {
		case 0 :
			break;
		case 1:
			this.roomIcon = "monster.png";
			if (Monster == 0) {
				list.add(new RatWolf());
			}
			else {
				list.add(new SmallRatWolf());
			}
			break;
		case 2:
			this.roomIcon = "monster.png";
			for (var i = 0; i < 2; i++) {
				if (Monster == 0) {
					list.add(new RatWolf());
				}
				else {
					list.add(new SmallRatWolf());
				}
				Monster = choiceMonster.nextInt(2);
			}
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
