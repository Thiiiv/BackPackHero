package fr.game.data;

public class Wall implements Room {
    
	private final String name = "wall";
	private final String roomIcon = "wall.png";
	private boolean isHeroHere = false;
    
    public boolean canEnter() {
        return false;
    }

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isHeroHere() {
		return isHeroHere;
	}

	@Override
	public void setHeroHere(boolean hereOrNot) {
		
	}

	@Override
	public String getRoomIcon() {
		return "wall.png";
	}
}