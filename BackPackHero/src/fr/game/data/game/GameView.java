package fr.game.data.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

import fr.game.data.Coordonnees;
import fr.game.data.Corridor;
import fr.game.data.ExitDoor;
import fr.game.data.Healer;
import fr.game.data.Inventory;
import fr.game.data.Merchant;
import fr.game.data.Room;
import fr.game.data.Treasure;
import fr.game.data.character.Monster;
import fr.game.data.item.Item;
import fr.game.data.item.MeleeWeapon;
import fr.umlv.zen5.ApplicationContext;

public record GameView(GameData data, ImageLoader loader) {
	public static GameView initGameGraphics(int xOrigin, int yOrigin, int length, GameData data, ImageLoader loader) {
		Objects.requireNonNull(data);
		Objects.requireNonNull(loader);
		return new GameView(data, loader);
	}

	private void drawImage(Graphics2D graphics, BufferedImage image, float x, float y, float dimX, float dimY) {
		var width = image.getWidth();
		var height = image.getHeight();
		var scale = Math.min(dimX / width, dimY / height);
		var transform = new AffineTransform(scale, 0, 0, scale, x + (dimX - scale * width) / 2,
				y + (dimY - scale * height) / 2);
		graphics.drawImage(image, transform, null);
	}

	public void drawMenu(Graphics2D graphics, int width, int height, GameData data) {
		graphics.setColor(Color.ORANGE);
		graphics.fill(new Rectangle2D.Float(0, 0, height, width));
		graphics.setColor(Color.RED);
		graphics.fill(new RoundRectangle2D.Float((height / 2) - 525, (width / 2) - 100, 500, 200, 50, 50));
		graphics.fill(new RoundRectangle2D.Float((height / 2) + 25, (width / 2) - 100, 500, 200, 50, 50));
		graphics.setColor(Color.BLACK);
		Font font = new Font("Arial", Font.PLAIN, 40); // Crée une police Arial, de style normal et de taille 12
		graphics.setFont(font);
		graphics.drawString("Jouer", (height / 2) - 315, (width / 2));
		graphics.drawString("Quitter", (height / 2) + 215, (width / 2));
	}

	public void drawRoomOnMap(Graphics2D graphics, float dimX, float dimY, float posX, float posY, Room room) {
		graphics.setColor(new Color(76, 47, 24, 255));
		graphics.setStroke(new BasicStroke(2.0f));
		graphics.draw(new RoundRectangle2D.Float(posX, posY, dimY, dimX, 20, 20));
		graphics.setColor(new Color(170, 127, 95, 255));
		graphics.fill(new RoundRectangle2D.Float(posX, posY, dimY - 1, dimX - 1, 20, 20));
	}

	public void drawItem(Graphics2D graphics, float posX, float posY, float dimx, float dimy, Item item) {
		if (item != null) {
			var image = loader.image(item.itemImage());
			// System.out.println(item.itemImage());
			drawImage(graphics, image, posX, posY, dimx, dimy * 2);
		}
	}

	public void drawMapButton(ApplicationContext context, int height, int width, GameData data) {
		context.renderFrame(graphics -> {
			var map = loader.image("mapButton.png");
			drawImage(graphics, map, width - map.getWidth(), map.getHeight(), map.getWidth(), map.getHeight());
			data.setMapButtonState(true);
		});
	}

	public void drawInventoryButton(ApplicationContext context, int height, int width, GameData data) {
		context.renderFrame(graphics -> {
			var map = loader.image("inventoryButton.png");
			drawImage(graphics, map, (width - getMapButtonsize()[0]), (float) (getMapButtonsize()[1]),
					getMapButtonsize()[0], getMapButtonsize()[1]);
			data.setMapButtonState(false);
			data.setInventoryButtonState(true);
		});
	}

	public int[] getMapButtonsize() {
		int[] dimensions = new int[2];
		var map = loader.image("mapButton.png");
		dimensions[0] = map.getWidth();
		dimensions[1] = map.getHeight();
		return dimensions;
	}

	public int[] getInventoryButtonsize() {
		int[] dimensions = new int[2];
		var map = loader.image("inventoryButton.png");
		dimensions[0] = map.getWidth();
		dimensions[1] = map.getHeight();
		return dimensions;
	}

	public int[] getPlayButtonsize() {
		int[] dimensions = new int[2];
		dimensions[0] = 500;
		dimensions[1] = 200;
		return dimensions;
	}

	public int[] getMenuButtonPosition(float width, float height) {
		int[] dimensions = new int[2];
		dimensions[0] = (int) (height / 2) - 525;
		dimensions[1] = (int) (width / 2) - 100;
		return dimensions;
	}

	public int[] getExitButtonPosition(float width, float height) {
		int[] dimensions = new int[2];
		dimensions[0] = (int) (height / 2) + 25;
		dimensions[1] = (int) (width / 2) - 100;
		return dimensions;
	}

	public float[] drawMap(ApplicationContext context, int height, int width, GameData data) {
		if (data.getMapButtonState()) {
			var coordonnees = new float[4];
			context.renderFrame(graphics -> {
				draw(graphics, context, data);
				var image = loader.image("map.png");
				float dimX = (float) (4 * width / 6);
				float dimY = (float) (height / 2);
				float posX = (float) (width / 6);
				float posY = 0;
				drawImage(graphics, image, posX, posY, dimX, dimY);
				dimX = 40;
				dimY = 40;
				posX *= 1.75;
				posY = ((height / 2) / 2) - (float) (dimY * 1.5);
				//System.out.println("posX : " + posX + " posY : " + posY);
				for (var i = 0; i < 11; i++) {
					for (var j = 0; j < 5; j++) {
						if (data.getFloor().getRoom(j, i) != null) {
							drawRoomOnMap(graphics, dimY, dimX, posX + dimX * i, posY + dimY * j,
									data.getFloor().getRoom(j, i));
							if (loader.image(data.getFloor().getRoom(j, i).getRoomIcon()) != null) {
								// System.out.println("roomIcon : " + data.getFloor().getRoom(j,
								// i).getRoomIcon());
								var roomIcon = loader.image(data.getFloor().getRoom(j, i).getRoomIcon());
								drawImage(graphics, roomIcon, posX + 10 + dimX * i, posY + 10 + dimY * j, dimX / 2,
										dimY / 2);
							}
							if (data.getFloor().getRoom(j, i).isHeroHere()) {
								drawImage(graphics, loader.image("heroIcon.png"), posX + 10 + dimX * i, posY + 10 + dimY * j, dimX / 2,
										dimY / 2);
							}
						}
					}
				}
				coordonnees[0] = posX;
				coordonnees[1] = posY;
				coordonnees[2] = dimX;
				coordonnees[3] = dimY;
			});
			data.setMapButtonState(false);
			data.setInventoryButtonState(true);

			return coordonnees;
		}
		return null;
	}

	public void drawCorridor(ApplicationContext context, int height, int width, GameData data, Corridor corridor) {
		context.renderFrame(graphics -> {
			draw(graphics, context, data);
		});
		if (corridor.isThereMonster()) {
			switch (corridor.getMonsters().size()) {
			case 1:
				drawMonster(context, (int) width, (int) height, data, corridor.getMonsters().get(0), null);
				break;
			case 2:
				drawMonster(context, (int) width, (int) height, data, corridor.getMonsters().get(0), corridor.getMonsters().get(1));
				break;
			}

		}

	}

	public void drawExitDoor(ApplicationContext context, int height, int width, GameData data, ExitDoor exitDoor) {

	}

	public void drawHealer(ApplicationContext context, int height, int width, GameData data, Healer healer) {

	}

	public void drawMerchant(ApplicationContext context, int height, int width, GameData data, Merchant merchant) {

	}

	public void drawTreasure(ApplicationContext context, int height, int width, GameData data, Treasure treasure) {

	}

	public void goToRoom(ApplicationContext context, int height, int width, GameData data, Coordonnees roomCoordonnees) {
		var room = data.getFloor().getRoom(roomCoordonnees.x(), roomCoordonnees.y());
		switch (room.getName()) {
		case "corridor":
			drawCorridor(context, height, width, data, (Corridor) room);
			break;
		case "treasure":
			drawTreasure(context, height, width, data, (Treasure) room);
			break;
		case "healder":
			drawHealer(context, height, width, data, (Healer) room);
			break;
		case "merchant":
			drawMerchant(context, height, width, data, (Merchant) room);
			break;
		case "exitdoor":
			drawExitDoor(context, height, width, data, (ExitDoor) room);
			break;
		}
	}

	public void drawBackground(Graphics2D graphics, int height, int width, GameData data) {
		var image = loader.image("background5.jpg");
		drawImage(graphics, image, 0, 0, width, height);
	}

	public void drawHero(Graphics2D graphics, int height, int width, GameData data) {
		var image = loader.image(data.getHero().getCharacterImage());
		float dimx = (float) (image.getWidth() * 1.5);
		float dimy = (float) (image.getHeight() * 1.5);
		float posX = 25;
		float posY = height - image.getHeight() * 2;
		var healthBar = new RoundRectangle2D.Float(posX, posY + dimy + 5, dimx, dimx / 8, 10, 10);
		drawImage(graphics, image, posX, posY, dimx, dimy);
		graphics.setColor(Color.BLACK);
		graphics.fill(healthBar);
		graphics.setColor(new Color(168, 38, 52, 255));
		graphics.fill(new RoundRectangle2D.Float((float) healthBar.getX(), (float) healthBar.getY(),
				(float) healthBar.getWidth() * ((float) data.getHero().health() / data.getHero().maxHealth()),
				(float) healthBar.getHeight(), 10, 10));
		graphics.setColor(Color.WHITE);
		Font font = new Font("Lucida Sans", Font.BOLD, 25);
		graphics.setFont(font);
		graphics.drawString(data.getHero().health() + "/" + data.getHero().maxHealth(),
				(posX + dimx) - font.getSize() * 3, posY + dimy + dimx / 8);
	}

	public void drawMonster(ApplicationContext context, int height, int width, GameData data, Monster monster,
			Monster additionalMonster) {
		context.renderFrame(graphics -> {
			var length = 2;
			if (additionalMonster == null) {
				length = 1;
			}
			var list = List.of(monster, additionalMonster);
			for (var i = 0; i < length; i++) {
				var image = loader.image(list.get(i).getCharacterImage());
				float dimx = (float) (loader.image("hero-4.png").getWidth() * 1.5);
				float dimy = (float) (loader.image("hero-4.png").getHeight() * 1.5);
				float posX = width - (80 + dimx * i + 1);
				float posY = height - loader.image("hero-4.png").getHeight() * 2;
				var healthBar = new RoundRectangle2D.Float(posX, posY + dimy + 5, dimx, dimx / 8, 10, 10);
				drawImage(graphics, image, posX, posY, dimx, dimy);
				graphics.setColor(Color.BLACK);
				graphics.fill(healthBar);
				graphics.setColor(new Color(168, 38, 52, 255));
				graphics.fill(new RoundRectangle2D.Float((float) healthBar.getX(), (float) healthBar.getY(),
						(float) healthBar.getWidth() * ((float) list.get(i).health() / list.get(i).maxHealth()),
						(float) healthBar.getHeight(), 10, 10));
				graphics.setColor(Color.WHITE);
				Font font = new Font("Lucida Sans", Font.BOLD, 25);
				graphics.setFont(font);
				graphics.drawString(list.get(i).health() + "/" + list.get(i).maxHealth(),
						(posX + dimx) - font.getSize() * 3, posY + dimy + dimx / 8);
			}
		});

	}

	public void drawInventory(Graphics2D graphics, int height, int width, GameData data) {
		var inventaire = data.getInventory();
		var list = inventaire.showContains();
		// System.out.println(inventaire);
		var cell = loader.image("cell.png");
		var backpack = loader.image("backpack.gif");
		float dimx = (float) (backpack.getWidth());
		float dimy = (float) (backpack.getHeight());
		float posX = (float) ((width / 2) - 0.5 * dimx);
		float posY = (float) (0 - backpack.getHeight() / 10);
		drawImage(graphics, backpack, posX, posY, dimx, dimy);
		dimx = cell.getWidth() / 2;
		posX = (int) ((width / 2) - 2.5 * dimx);
		posY = dimy / 4;
		dimy = cell.getHeight() / 2;
		for (var i = 0; i < 5; i++) {
			for (var j = 0; j < 3; j++) {
				drawImage(graphics, cell, posX + dimx * i, posY + dimy * j, dimx, dimy);
			}
		}

		for (var i : list) {
			drawItem(graphics, posX + dimx * inventaire.get(i).x(), posY + dimy * inventaire.get(i).y(), dimx, dimy, i);
		}
	}

	public void draw(Graphics2D graphics, ApplicationContext context, GameData data) {
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getHeight();
		var height = screenInfo.getWidth();
		if (data.getMenuState() == true) {
			drawMenu(graphics, (int) width, (int) height, data);
		} else {
			drawBackground(graphics, (int) width, (int) height, data);
			drawInventory(graphics, (int) width, (int) height, data);
			drawHero(graphics, (int) width, (int) height, data);

			if (!data.getMapButtonState()) {
				drawMapButton(context, (int) width, (int) height, data);
			}
		}

	}

	public static void draw(ApplicationContext context, GameData data, GameView view) {
		context.renderFrame(graphics -> view.draw(graphics, context, data));
	}

}