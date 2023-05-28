package fr.game.data.game;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import fr.game.data.item.MeleeWeapon;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

public class GameController {

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
		GameView.draw(context, data, view);
		var dimMapButton = view.getMapButtonsize();
		float[] floorCoordonnees = null;
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
					System.out.println("posX = " + location.x + " posY = " + location.y);
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
							floorCoordonnees = view.drawMap(context, (int) height, (int) width, data);
							view.drawInventoryButton(context, (int) height, (int) width, data);
							break;
						case "inventoryButton":
							GameView.draw(context, data, view);
						}
					}
					System.out.println("floorCoordonnees : " + floorCoordonnees);
					if (floorCoordonnees != null) {
						System.out.println(floorCoordonnees[0] + " " + floorCoordonnees[1] + " " + (int) floorCoordonnees[2] + " " + (int) floorCoordonnees[3]);
						var detectRoom = data.clickOnMap(location.x, location.y, floorCoordonnees[0], floorCoordonnees[1], (int) floorCoordonnees[2], (int) floorCoordonnees[3]);
						System.out.println("detectRoom : " + detectRoom);
						if (detectRoom != null) {
							System.out.println("detectRoom : " + detectRoom);
							view.goToRoom(context, (int) height, (int) width, data, detectRoom);
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
}
