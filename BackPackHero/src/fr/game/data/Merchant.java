package fr.game.data;

import java.util.ArrayList;

import fr.game.data.item.Item;

public class Merchant implements RoomMatrice {
	@Override
	public String getName() {
		return "merchant";
	}

	@Override
	public int display() {
		return 0;
	}
	public ArrayList<Item> generateItems() {
		return new ArrayList<Item>();
	}

}
