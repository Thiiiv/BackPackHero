package fr.game.data.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import fr.game.data.Corridor;
import fr.game.data.ExitDoor;
import fr.game.data.Healer;
import fr.game.data.Inventory;
import fr.game.data.Merchant;
import fr.game.data.Treasure;
import fr.game.data.Wall;
import fr.game.data.item.MeleeWeapon;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

public class GameController {

	private static final int MAX_ROWS = 0;
	private static final int MAX_COLS = 0;

	public GameController() {
	}

	private static void playMusic() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("data/music.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean gameLoop(ApplicationContext context, GameData data, GameView view) {
		var event = context.pollOrWaitEvent(10);
		if (event == null) {
			return true;
		}
		var action = event.getAction();
		if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.Q) {
			return false;
		}
		return true;

	}

	private static void checkRange(double min, double value, double max) {
		if (value < min || value > max) {
			throw new IllegalArgumentException("Invalid coordinate: " + value);
		}
	}

	private static List<String> collectImages(String dir) throws IOException {
		var list = new ArrayList<String>();
		var input = Files.newDirectoryStream(Path.of(dir));
		for (var entry : input) {
			list.add(entry.getFileName().toString());
		}
		return list;
	}

	private static void backpackHero(ApplicationContext context) throws IOException {
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		var margin = 0;
		var images = new ImageLoader("data", collectImages("data"));
		var data = new GameData();
		var view = GameView.initGameGraphics(margin, margin, (int) Math.min(width, height) - 2 * margin, data, images);
		data.addItem(0, 0, new MeleeWeapon("Common", 10, 2));
		data.getHero().getDamage(10);
		GameView.draw(context, data, view);
		var dimMapButton = view.getMapButtonsize();
		while (true) {
			var event = context.pollOrWaitEvent(10);
			if (!gameLoop(context, data, view)) {
				// System.out.println("Thank you for quitting!");
				context.exit(0);
			}
			if (event == null) {
				continue;
			}
			var action = event.getAction();
			if (action != Action.KEY_PRESSED) {
				var location = event.getLocation();
				/*
				 * System.out.println(data.clickOnButton(location.x, location.y, (int) (width -
				 * dimMapButton[0]), 0, dimMapButton[0], dimMapButton[1]));
				 * System.out.println("x = " + location.x + " y = " + location.y +
				 * " buttonX1 = " + (width - dimMapButton[0]) + " buttonY1 = " + 0 +
				 * " buttonX2 = " + (width - dimMapButton[0]) + dimMapButton[0] + " buttonY2 = "
				 * + dimMapButton[1]);
				 */
				if (location != null) {
					if (data.getMenuState()) {
						var posMenuButton = view.getMenuButtonPosition(height, width);
						var dimPlay = view.getPlayButtonsize();
						// System.out.println("positionPlay : " + positionPlay[0] + ", " +
						// positionPlay[1] + " dimPlay : " + dimPlay[0] + ", " + dimPlay[1]);
						if (data.clickOnMenuButton(location.x, location.y, posMenuButton[0], posMenuButton[1],
								dimPlay[0], dimPlay[1])) {
							data.setMenuState(false);
							GameView.draw(context, data, view);
							playMusic();
						} else {
							posMenuButton = view.getExitButtonPosition(height, width);
							if (data.clickOnMenuButton(location.x, location.y, posMenuButton[0], posMenuButton[1],
									dimPlay[0], dimPlay[1])) {
								context.exit(0);
							}
						}
					}
					var detectButton = data.clickOnButton(location.x, location.y, (int) (width - dimMapButton[0]),
							dimMapButton[1], dimMapButton[0], dimMapButton[1] * 2);
					// System.out.println(detectButton);
					if (detectButton != null) {
						switch (detectButton) {
						case "mapButton":
							view.drawMap(context, (int) height, (int) width, data);
							view.drawInventoryButton(context, (int) height, (int) width, data);
							break;
						case "inventoryButton":
							GameView.draw(context, data, view);
						}
					}
				}
			}
			// System.out.println("MapButtonState : " + data.getMapButtonState());
			// System.out.println("InventoryButtonState : " +
			// data.getInventoryButtonState());
		}
	}

	public static void main(String[] args) {
		Application.run(Color.WHITE, t -> {
			try {
				backpackHero(t);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	/*public void moveHero(int row, int col) {
	    if (row < 0 || row >= MAX_ROWS || col < 0 || col >= MAX_COLS) {
	        System.out.println("Vous ne pouvez pas vous déplacer en dehors du donjon !");
	        return;
	    }

	    Wall[][] floor;
		if (floor[row][col] instanceof Wall) {
	        System.out.println("Vous ne pouvez pas traverser un mur !");
	        return;
	    }

	    if (floor[row][col] instanceof Corridor) {
	        Corridor corridor = (Corridor) floor[row][col];
	        if (corridor.isThereMonster()) {
	            System.out.println("Vous avez rencontré des monstres !");
	            // ici, vous pouvez ajouter le code pour le combat contre les monstres
	            return;
	        }
	    } else if (floor[row][col] instanceof Treasure) {
	        Treasure treasure = (Treasure) floor[row][col];
	        System.out.println("Vous avez trouvé un trésor !");
	        // ici, vous pouvez ajouter le code pour ouvrir le trésor
	    } else if (floor[row][col] instanceof Healer) {
	        Healer healer = (Healer) floor[row][col];
	        System.out.println("Vous êtes tombé sur un guérisseur !");
	        // ici, vous pouvez ajouter le code pour obtenir des soins
	    } else if (floor[row][col] instanceof ExitDoor) {
	        System.out.println("Vous avez atteint la porte de sortie !");
	        // ici, vous pouvez ajouter le code pour passer au niveau suivant
	    } else if (floor[row][col] instanceof Merchant) {
	        Merchant merchant = (Merchant) floor[row][col];
	        System.out.println("Vous êtes tombé sur un marchand !");
	        // ici, vous pouvez ajouter le code pour acheter des objets
	    }

	    // déplacer le héros
	    floor[heroRow][heroCol].removeCharacter(hero);
	    floor[row][col].addCharacter(hero);
	    heroRow = row;
	    heroCol = col;
	    eachFloor();
	}*/
}
