package fr.enzo.items;

public interface Item {
	public String getName();
	public int height();
	public int width();
	public String IsApplyTo(String item); //add Item in argument
	public String IsApplyTo2(String character); //add Character in argument

}
