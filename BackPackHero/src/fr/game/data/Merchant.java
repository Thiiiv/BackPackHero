package fr.game.data;

import java.util.ArrayList;

import fr.game.data.item.Item;

public class Merchant implements Room {
	@Override
	public String getName() {
		return "merchant";
	}
	
	public ArrayList<Item> generateItems() {
		return new ArrayList<Item>();
	}

}
