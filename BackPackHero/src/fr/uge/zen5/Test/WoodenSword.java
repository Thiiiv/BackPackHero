package fr.uge.zen5.Test;

public class WoodenSword implements MeleeWeapons {
	public WoodenSword() {
	}
	@Override
	public String name() {
		return "Wooden Sword";
	}
	@Override
	public int rarity() {
		return 1;
	}
	@Override
	public boolean actualState() {
		return true;
	}
	@Override
	public int damagePoint() {
		return 7;
	}
	@Override
	public int requiredEnergyPoints() {
		return 1;
	}
}
