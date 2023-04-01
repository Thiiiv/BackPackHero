package fr.game.data;

import java.util.ArrayList;
import java.util.List;
import fr.game.data.item.Item;

public class Treasure implements Room {

	@Override
	public String getName() {
		return "treasure";
	}
	
	public List<Item> generateItems() {
		ArrayList<Item> list = new ArrayList<>();
		for (var i = 0; i < 11; i++) {
			list.add(null);
		}
		return list;
	}
	
}
