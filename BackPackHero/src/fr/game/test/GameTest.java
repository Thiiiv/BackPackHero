package fr.game.test;
import fr.game.data.Healer;
import fr.game.data.Hero;
import fr.game.data.Monster;
import fr.game.data.*;

public class GameTest {
	public static void main(String[] args) {
		var hero = new Hero();
		// On affiche la vie du héros
		System.out.println(hero.health());
		// Le héros prend 10 points de dégât
		hero.getDamage(10);
		// On affiche de nouveau la vie du héros
		System.out.println(hero.health());
		// Le héros récupère 100 points de vie
		Hero.heal(100);
		// On affiche de nouveau la vie du héros
		System.out.println(hero.health());
		// On créer un nouveau healer
		var healer = new Healer();
		// Boucle for avec GoldCoins
		for (var i = 0; i < 11; i++) {
			// La vie max du héro est augmentée de 25 HP à chaque itération de la boucle
			healer.improveHealth();
		}
		// Le héros récupère 10 points de vie
		Hero.heal(10);
		// On créer le catalogue du healer
		var healerCatalog = healer.getCatalog();
		// On affiche le catalogue que propose le healer
		System.out.println(healerCatalog);
		// On affiche de nouveau la vie du héros
		System.out.println(hero.health());
		// On créer un nouveau monstre
		var monster = new Monster();
		// Le monstre attaque le héros
		monster.attack(hero);
		// On affiche la vie du héros après l'attaque subie
		System.out.println(hero.health());
		// On affiche la proposition du healer pour 0 pièce
		System.out.println(healerCatalog.get(0));
		// Le héros reçoit 10 points d'attaque
		hero.getDamage(10);
		// On affiche la vie du héros après qu'il ait reçu ses points de dégât
		System.out.println(hero.health());
		// Le héros s'équipe d'une nouvelle arme
		hero.equip(new MeleeWeapon("Common", 10, 2));
		// On affiche les points d'attaque du héros
		System.out.println(hero.attackPoint());
		// Le monstre reçoit le nombre de dégâts relatif aux points d'attaque du héros
		monster.getDamage(hero.attackPoint());
		// On affiche ensuite la vie du monstre
		System.out.println(monster.health());
		// On créer un nouveau marchant
		var marchant = new Merchant();
		// On affiche le nom du marchant
		System.out.println(marchant.getName());
		// Le héros s'équipe d'une nouvelle arme de mêlée
		hero.equip(new MeleeWeapon("Uncommon", 50, 2));
		// On affiche le nombre de points d'attaque du héros
		System.out.println(hero.attackPoint());
		
	}

}
