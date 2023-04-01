package fr.game.data;

import java.util.ArrayList;
import java.util.List;

public class Treasure implements RoomMatrice {

	@Override
	public String getName() {
		return "treasure";
	}

	@Override
	public int display() {
		return 0;
	}
	
	public List<Item> generateItems() {
		ArrayList<Item> list = new ArrayList<>();
		for (var i = 0; i < 11; i++) {
			list.add(null);
		}
		return list;
	}
	
}
