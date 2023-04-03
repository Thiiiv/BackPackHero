package fr.game.data;

public interface Room {
	
	public String getName();
	public boolean equals(Object o);
	public int hashCode();
	public boolean isHeroHere();
	public void setHeroHere(boolean hereOrNot);
	public String getRoomIcon();
	public static void onEnter() {}
}