package fr.but.test;
import fr.uge.data.*;

public class GameTest {
	public static void main(String[] args) {
		var hero = new Hero();
		System.out.println(hero.health());
		hero.getDamage(10);
		System.out.println(hero.health());
		Hero.heal(100);
		System.out.println(hero.health());
		var healer = new Healer();
		for (var i = 0; i < 11; i++) {
			healer.improveHealth();
		}
		Hero.heal(100);
		System.out.println(healer.getCatalog());
		System.out.println(hero.health());
		var mechant = new Monster();
		mechant.attack(hero);
		System.out.println(hero.health());
		
	}

}
