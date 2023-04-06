package fr.game.test;
import fr.game.data.*;
import fr.game.data.character.Hero;
import fr.game.data.character.Monster;
import fr.game.data.character.RatWolf;
import fr.game.data.item.MeleeWeapon;
import fr.game.data.item.RangedWeapon;
import fr.game.data.item.Shield;

public class GameTest {
	
	public static void main(String[] args) {
		
		var hero = new Hero();
		// On affiche la vie du héros
		System.out.println("Au commencement de la partie, " + hero.healthText() + "\nC'est le maximum.");
		// Le héros prend 10 points de dégât
		hero.getDamage(10);
		// On affiche de nouveau la vie du héros
		System.out.println(hero.healthText());
		// Le héros récupère 20 points de vie
		Hero.heal(20);
		// On affiche de nouveau la vie du héros
		System.out.println(hero.healthText());
		// On créer un nouveau healer
		var healer = new Healer();
		// Boucle for avec GoldCoins
		for (var i = 0; i < 11; i++) {
			// La vie max du héro est augmentée de 5 HP à chaque itération de la boucle
			healer.improveHealth();
		}
		
		// Le héros récupère 10 points de vie
		Hero.heal(10);
		// On affiche de nouveau la vie du héros
		System.out.println(hero.healthText());
		// On créer le catalogue du healer
		
		System.out.println("\nVoici les prix que propose le " + healer.getName() + " : ");
		var healerCatalog = healer.getCatalog();
		// On affiche le catalogue que propose le healer
		System.out.println(healerCatalog);
		
		// On créer un nouveau monstre
		var monster = new RatWolf();
		// Le monstre attaque le héros
		monster.attack(hero);
		// On affiche la vie du héros après l'attaque subie
		System.out.println(hero.healthText());
		// On affiche la proposition du healer pour 0 pièce
		System.out.println(healerCatalog.get(0));
		// Le héros reçoit 10 points de dégat
		hero.getDamage(10);
		// On affiche la vie du héros après qu'il ait reçu ses points de dégât
		System.out.println(hero.healthText());
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
		
		
		
		//On créer un nouveau héros
		var zero=new Hero();
		//on affiche ensuite sa santé
		System.out.println(zero.health());
		
		//Création de plusieurs équipements
		var sword=new MeleeWeapon("Common", 10, 11);
		var shield=new Shield("Bouclier","Common",0,1, 5);
		var bow=new RangedWeapon("Arc","Rare", 10, 1);
		var sword2=new MeleeWeapon("Common", 10, 11);
		//On affiche tout ces équipements
		System.out.println(sword);
		System.out.println(shield);
		System.out.println(bow);
		
		//Objet avec une rareté impossible est crée
		//var baton=new MagicWand("Magic", "Epic", 2, 1, 2);
		
		zero.equip(sword);
		zero.equip(shield);
		zero.equip(bow);
		zero.equip(sword2);
		System.out.println(hero.attackPoint());
	
	}

}