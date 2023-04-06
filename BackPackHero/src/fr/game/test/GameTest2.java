package fr.game.test;

import fr.game.data.*;

public class GameTest2 {

	public static void main(String[] args) {

		Floor floor = new Floor();
		
		floor.add(2, 0, new Corridor(0));
		floor.add(2, 1, new Corridor(0));
		floor.add(2, 2, new Corridor(1));
		floor.add(0, 2, new Corridor(0));
		floor.add(1, 2, new Corridor(0));
		floor.add(3, 2, new Corridor(0));
		floor.add(4, 2, new Corridor(0));
		floor.add(0, 3, new Treasure());
		floor.add(2, 3, new Corridor(0));
		floor.add(4, 3, new Healer());
		floor.add(2, 4, new Corridor(0));
		floor.add(2, 5, new Corridor(0));
		floor.add(3, 5, new Corridor(1));
		floor.add(4, 5, new Corridor(0));
		floor.add(0, 6, new Treasure());
		floor.add(1, 6, new Corridor(0));
		floor.add(4, 6, new Corridor(0));
		floor.add(1, 7, new Corridor(0));
		floor.add(2, 7, new Corridor(0));
		floor.add(4, 7, new Corridor(0));
		floor.add(0, 8, new ExitDoor());
		floor.add(2, 8, new Corridor(0));
		floor.add(3, 8, new Corridor(0));
		floor.add(4, 8, new Corridor(0));
		floor.add(0, 9, new Corridor(0));
		floor.add(2, 9, new Corridor(0));
		floor.add(4, 9, new Corridor(0));
		floor.add(0, 10, new Corridor(0));
		floor.add(1, 10, new Corridor(0));
		floor.add(2, 10, new Corridor(1));
		floor.add(4, 10, new Merchant());
		floor.getRoom(2, 0).setHeroHere(true);
        
        floor.eachFloor();
	}

}