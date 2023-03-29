package fr.game.data;

public class ExitDoor implements RoomMatrice {
	private int state = 0;
	
	@Override
	public String getName() {
		return "exitdoor";
	}

	@Override
	public int display() {
		return 0;
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
	
}
