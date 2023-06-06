package fr.game.data.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
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

/**
 * The GameView class represents the graphical view of the game.
 */
public record GameView(GameData data, ImageLoader loader) {
	
	/**
     * Initializes the game graphics.
     *
     * @param xOrigin The x-coordinate origin of the game graphics.
     * @param yOrigin The y-coordinate origin of the game graphics.
     * @param length  The length of the game graphics.
     * @param data    The game data.
     * @param loader  The image loader.
     * @return The initialized GameView.
     * @throws NullPointerException if data or loader is null.
     */
	public static GameView initGameGraphics(int xOrigin, int yOrigin, int length, GameData data, ImageLoader loader) {
		Objects.requireNonNull(data);
		Objects.requireNonNull(loader);
		return new GameView(data, loader);
	}
	
	/**
	 * Draws an image on the graphics with the specified dimensions and position.
	 *
	 * @param graphics The graphics object to draw on.
	 * @param image    The image to draw.
	 * @param x        The x-coordinate position.
	 * @param y        The y-coordinate position.
	 * @param dimX     The width dimension.
	 * @param dimY     The height dimension.
	 */
	private void drawImage(Graphics2D graphics, BufferedImage image, float x, float y, float dimX, float dimY) {
		var width = image.getWidth();
		var height = image.getHeight();
		var scale = Math.min(dimX / width, dimY / height);
		var transform = new AffineTransform(scale, 0, 0, scale, x + (dimX - scale * width) / 2,
				y + (dimY - scale * height) / 2);
		graphics.drawImage(image, transform, null);
	}
	
	/**
	 * Draws an image on the graphics with the specified dimensions and position, and updates the object position in the game data.
	 *
	 * @param graphics The graphics object to draw on.
	 * @param image    The image to draw.
	 * @param x        The x-coordinate position.
	 * @param y        The y-coordinate position.
	 * @param dimX     The width dimension.
	 * @param dimY     The height dimension.
	 * @param data     The game data.
	 * @param item     The item to update the position for.
	 */
	private void drawImage(Graphics2D graphics, BufferedImage image, float x, float y, float dimX, float dimY, GameData data, Item item) {
		var width = image.getWidth();
		var height = image.getHeight();
		var scale = Math.min(dimX / width, dimY / height);
		var transform = new AffineTransform(scale, 0, 0, scale, x + (dimX - scale * width) / 2,
				y + (dimY - scale * height) / 2);
		graphics.drawImage(image, transform, null);
		data.addObjectPosition(item, x, y, x + dimX, y + dimY);
	}
	
	/**
	 * Draws the menu on the graphics with the specified dimensions and game data.
	 *
	 * @param graphics The graphics object to draw on.
	 * @param width    The width of the menu.
	 * @param height   The height of the menu.
	 * @param data     The game data.
	 */
	public void drawMenu(Graphics2D graphics, int width, int height, GameData data) {
		drawImage(graphics, loader.image("main.png"), 0,0, height, width);
		graphics.setColor(new Color(170, 127, 95, 255));
		graphics.fill(new RoundRectangle2D.Float((height / 2) - 525, (width - 250), 500, 100, 30, 30));
		graphics.fill(new RoundRectangle2D.Float((height / 2) + 25, (width - 250), 500, 100, 30, 30));
		graphics.setStroke(new BasicStroke(4.0f));
		graphics.setColor(Color.BLACK);
		graphics.draw(new RoundRectangle2D.Float((height / 2) - 525, (width - 250), 500, 100, 30, 30));
		graphics.draw(new RoundRectangle2D.Float((height / 2) + 25, (width - 250), 500, 100, 30, 30));
		graphics.setColor(Color.WHITE);
		Font font = new Font("Lucida Sans", Font.BOLD, 25);
		graphics.setFont(font);
		graphics.drawString("Jouer", (height / 2) - 315, (width - 190));
		graphics.drawString("Quitter", (height / 2) + 240, (width - 190));
	}
	
	/**
	 * Draws a room on the map with the specified dimensions, position, and room data.
	 *
	 * @param graphics The graphics object to draw on.
	 * @param dimX     The width dimension of the room.
	 * @param dimY     The height dimension of the room.
	 * @param posX     The x-coordinate position of the room.
	 * @param posY     The y-coordinate position of the room.
	 * @param room     The room data to draw.
	 */
	public void drawRoomOnMap(Graphics2D graphics, float dimX, float dimY, float posX, float posY, Room room) {
		graphics.setColor(new Color(76, 47, 24, 255));
		graphics.setStroke(new BasicStroke(2.0f));
		graphics.draw(new RoundRectangle2D.Float(posX, posY, dimY, dimX, 20, 20));
		graphics.setColor(new Color(170, 127, 95, 255));
		graphics.fill(new RoundRectangle2D.Float(posX, posY, dimY - 1, dimX - 1, 20, 20));
	}
	
	/**
	 * Draws an item on the graphics with the specified position, dimensions, item data, and game data.
	 *
	 * @param graphics The graphics object to draw on.
	 * @param posX     The x-coordinate position of the item.
	 * @param posY     The y-coordinate position of the item.
	 * @param dimx     The width dimension of the item.
	 * @param dimy     The height dimension of the item.
	 * @param item     The item data to draw.
	 * @param data     The game data.
	 */
	public void drawItem(Graphics2D graphics, float posX, float posY, float dimx, float dimy, Item item, GameData data) {
		if (item != null) {
			var image = loader.image(item.itemImage());
			var sizeItem = item.getSize();
			// System.out.println(item.itemImage());
			drawImage(graphics, image, posX, posY, dimx * sizeItem[0].length, dimy * sizeItem.length, data, item);
			//System.out.println("La position des objets : " + data.getObjectsPosition());
		}
	}
	
	/**
	 * Draws an item on the graphics with the specified position, item data, and game data using the application context.
	 *
	 * @param context The application context.
	 * @param posX    The x-coordinate position of the item.
	 * @param posY    The y-coordinate position of the item.
	 * @param item    The item data to draw.
	 * @param data    The game data.
	 */
	public void drawItem(ApplicationContext context, float posX, float posY, Item item, GameData data) {
		context.renderFrame(graphics -> {
			if (item != null) {
				var image = loader.image(item.itemImage());
				var cellSize = loader.image("cell.png");
				var sizeItem = item.getSize();
				// System.out.println(item.itemImage());
				draw(graphics, context, data);
				drawImage(graphics, image, posX, posY, cellSize.getWidth() * sizeItem[0].length, cellSize.getHeight() * sizeItem.length, data, item);
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("Les items présents dans l'interface APRES le déplacement : " + data.getObjectsPosition());
				System.out.println("----------------------------------------------------------------------------------------");
				//System.out.println("La position des objets : " + data.getObjectsPosition());
			}
		});
	}
	
	
	/**
	 * Draws the map button on the application interface with the specified application context, height, width, and game data.
	 *
	 * @param context The application context.
	 * @param height  The height of the interface.
	 * @param width   The width of the interface.
	 * @param data    The game data.
	 */
	public void drawMapButton(ApplicationContext context, int height, int width, GameData data) {
		context.renderFrame(graphics -> {
			var map = loader.image("mapButton.png");
			drawImage(graphics, map, width - map.getWidth(), map.getHeight(), map.getWidth(), map.getHeight());
		});
	}

	/**
	 * Draws the inventory button on the application interface with the specified application context, height, width, and game data.
	 *
	 * @param context The application context.
	 * @param height  The height of the interface.
	 * @param width   The width of the interface.
	 * @param data    The game data.
	 */
	public void drawInventoryButton(ApplicationContext context, int height, int width, GameData data) {
		context.renderFrame(graphics -> {
			var map = loader.image("inventoryButton.png");
			drawImage(graphics, map, (width - getMapButtonsize()[0]), (float) (getMapButtonsize()[1]),
					getMapButtonsize()[0], getMapButtonsize()[1]);
		});
	}
	
	/**
	 * Retrieves the dimensions of the map button.
	 *
	 * @return An array of integers representing the width and height of the map button.
	 */
	public int[] getMapButtonsize() {
		int[] dimensions = new int[2];
		var map = loader.image("mapButton.png");
		dimensions[0] = map.getWidth();
		dimensions[1] = map.getHeight();
		return dimensions;
	}
	
	/**
	 * Retrieves the dimensions of the inventory button.
	 *
	 * @return An array of integers representing the width and height of the inventory button.
	 */
	public int[] getInventoryButtonsize() {
		int[] dimensions = new int[2];
		var map = loader.image("inventoryButton.png");
		dimensions[0] = map.getWidth();
		dimensions[1] = map.getHeight();
		return dimensions;
	}
	
	/**
	 * Retrieves the dimensions of the play button.
	 *
	 * @return An array of integers representing the width and height of the play button.
	 */
	public int[] getPlayButtonsize() {
		int[] dimensions = new int[2];
		dimensions[0] = 500;
		dimensions[1] = 200;
		return dimensions;
	}
	
	/**
	 * Retrieves the position of the play button on the interface based on the specified width and height.
	 *
	 * @param width  The width of the interface.
	 * @param height The height of the interface.
	 * @return An array of integers representing the x and y coordinates of the exit button.
	 */
	public int[] getMenuButtonPosition(float width, float height) {
		int[] dimensions = new int[2];
		dimensions[0] = (int) (height / 2) - 525;
		dimensions[1] = (int) (width - 250);
		return dimensions;
	}
	
	/**
	 * Retrieves the position of the exit button on the interface based on the specified width and height.
	 *
	 * @param width  The width of the interface.
	 * @param height The height of the interface.
	 * @return An array of integers representing the x and y coordinates of the exit button.
	 */
	public int[] getExitButtonPosition(float width, float height) {
		int[] dimensions = new int[2];
		dimensions[0] = (int) (height / 2) + 25;
		dimensions[1] = (int) (width - 250);
		return dimensions;
	}
	
	/**
	 * Draws the map on the application interface with the specified application context, height, width, and game data.
	 *
	 * @param context The application context.
	 * @param height  The height of the interface.
	 * @param width   The width of the interface.
	 * @param data    The game data.
	 * @return An array of floats representing the x and y coordinates, and dimensions (dimX and dimY) of the drawn map.
	 */
	public float[] drawMap(ApplicationContext context, int height, int width, GameData data) {
		if (data.getMapState()) {
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
				posX *= 2;
				posY = ((height / 2) / 2) - (float) (dimY * 1.5);
				// System.out.println("posX : " + posX + " posY : " + posY);
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
								drawImage(graphics, loader.image("heroIcon.png"), posX + 10 + dimX * i,
										posY + 10 + dimY * j, dimX / 2, dimY / 2);
							}
						}
					}
				}
				coordonnees[0] = posX;
				coordonnees[1] = posY;
				coordonnees[2] = dimX;
				coordonnees[3] = dimY;
			});

			return coordonnees;
		}
		return null;
	}
	
	/**
	 * Draws the corridor on the application interface with the specified application context, height, width, game data, and corridor data.
	 *
	 * @param context   The application context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 * @param corridor  The corridor data.
	 */
	public void drawCorridor(ApplicationContext context, int height, int width, GameData data, Corridor corridor) {
		context.renderFrame(graphics -> {
			draw(graphics, context, data);
		});
		if (corridor.isThereMonster()) {
			switch (corridor.getMonsters().size()) {
			case 1:
				drawMonster(context, (int) height, (int) width, data, corridor.getMonsters().get(0));
				break;
			case 2:
				drawMonster(context, (int) height, (int) width, data, corridor.getMonsters().get(0), corridor.getMonsters().get(1));
				break;
			}
		}

	}
	
	/**
	 * Draws the exit door on the application interface with the specified application context, height, width, game data, and exit door data.
	 *
	 * @param context   The application context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 * @param exitDoor  The exit door data.
	 */
	public void drawExitDoor(ApplicationContext context, int height, int width, GameData data, ExitDoor exitDoor) {

	}

	/**
	 * Draws the healer on the application interface with the specified application context, height, width, game data, and healer data.
	 *
	 * @param context   The application context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 * @param healer    The healer data.
	 */
	public void drawHealer(ApplicationContext context, int height, int width, GameData data, Healer healer) {
		context.renderFrame(graphics -> {
			draw(graphics, context, data);
			var image = loader.image(healer.getImage());
			float dimx = (float) (loader.image("hero-4.png").getWidth() * 1.5);
			float dimy = (float) (loader.image("hero-4.png").getHeight() * 1.5);
			float posX = (float) (width - (80 + dimx));
			float posY = height - loader.image("hero-4.png").getHeight() * 2;
			drawImage(graphics, image, posX, posY, dimx, dimy);
		});
	}
	
	/**
	 * Draws the merchant on the application interface with the specified application context, height, width, game data, and merchant data.
	 *
	 * @param context   The application context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 * @param merchant  The merchant data.
	 */
	public void drawMerchant(ApplicationContext context, int height, int width, GameData data, Merchant merchant) {
		context.renderFrame(graphics -> {
			draw(graphics, context, data);
			var image = loader.image(merchant.getImage());
			float dimx = (float) (loader.image("hero-4.png").getWidth() * 1.5);
			float dimy = (float) (loader.image("hero-4.png").getHeight() * 1.5);
			float posX = (float) (width - (80 + dimx));
			float posY = height - loader.image("hero-4.png").getHeight() * 2;
			drawImage(graphics, image, posX, posY, dimx, dimy);
		});
	}
	
	/**
	 * Draws the treasure on the application interface with the specified application context, height, width, game data, and treasure data.
	 *
	 * @param context   The application context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 * @param treasure  The treasure data.
	 */
	public void drawTreasure(ApplicationContext context, int height, int width, GameData data, Treasure treasure) {

	}
	
	
	/**
	 * Navigates to a specific room on the application interface with the specified application context, height, width, game data, and room coordinates.
	 *
	 * @param context           The application context.
	 * @param height            The height of the interface.
	 * @param width             The width of the interface.
	 * @param data              The game data.
	 * @param roomCoordonnees   The coordinates of the room to navigate to.
	 */
	public void goToRoom(ApplicationContext context, int height, int width, GameData data,
			Coordonnees roomCoordonnees) {
		var room = data.getFloor().getRoom(roomCoordonnees.x(), roomCoordonnees.y());
		System.out.println("les coordonnees envoyés dans setCurrentRoom : x = " + roomCoordonnees.x() + " y = " +roomCoordonnees.y());
		data.setCurrentRoom(roomCoordonnees.y(), roomCoordonnees.x());
		data.setInventoryState(true);
		data.setMapState(false);
		switch (room.getName()) {
		case "corridor":
			drawCorridor(context, height, width, data, (Corridor) room);
			break;
		case "treasure":
			drawTreasure(context, height, width, data, (Treasure) room);
			break;
		case "healer":
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

	/**
	 * Draws the background image on the graphics context with the specified dimensions and game data.
	 *
	 * @param graphics  The graphics context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 */
	public void drawBackground(Graphics2D graphics, int height, int width, GameData data) {
		var image = loader.image("background5.jpg");
		drawImage(graphics, image, 0, 0, width, height);
	}

	/**
	 * Draws the hero on the graphics context with the specified dimensions and game data.
	 *
	 * @param graphics  The graphics context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 */
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
		image = loader.image("energy.png");
		drawImage(graphics, image, posX-(image.getWidth()/4), posY-(image.getHeight()/2), image.getWidth(), image.getHeight());
		var heroEnergy = data.getHero().getEnergyPoint();
		graphics.drawString(data.getHero().getEnergyPoint() + "", posX+(image.getWidth()/5), posY+(image.getHeight()/7));
		if (data.getHero().defensePoint() > 0) {
			image = loader.image("protection.png");
			drawImage(graphics, image, (float) healthBar.getX() - (image.getWidth()/4), (float) healthBar.getY() - (image.getHeight()/4), image.getWidth()/1.5f, image.getHeight()/1.5f);
			graphics.setFont(font);
			graphics.setColor(Color.WHITE);
			graphics.drawString(data.getHero().defensePoint() + "", (float) healthBar.getX(), (float) healthBar.getY() + 20);
		}
	}
	
	/**
	 * Draws the monsters on the application interface with the specified application context, height, width, game data, and monster data.
	 *
	 * @param context   The application context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 * @param monster   The monster data.
	 */
	public void drawMonster(ApplicationContext context, int height, int width, GameData data, Monster... monster) {
		context.renderFrame(graphics -> {
			var length = 1;
			/*if (additionalMonster == null) {
				length = 1;
			}*/
			var list = List.of(monster);
			for (var i = 0; i < monster.length; i++) {
				if (list.get(i).health() > 0) {
					//System.out.println("list.get(i).getCharacterImage() : " + list.get(i).getCharacterImage());
					var image = loader.image(list.get(i).getCharacterImage());
					if (image != null) {
						float dimx = (float) (loader.image("hero-4.png").getWidth() * 1.5);
						float dimy = (float) (loader.image("hero-4.png").getHeight() * 1.5);
						float posX = (float) (width - (80 + dimx * (i + 1) + i*20));
						float posY = height - loader.image("hero-4.png").getHeight() * 2;
						//System.out.println("posX : " + posX + " posY : " + posY);
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
				}
			}
		});

	}
	
	/**
	 * Draws the inventory on the graphics context with the specified dimensions and game data.
	 *
	 * @param graphics  The graphics context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 */
	public void drawInventory(Graphics2D graphics, int height, int width, GameData data) {
		var inventaire = data.getInventory();
		var list = inventaire.showContains();
		// System.out.println(inventaire);
		var cell = loader.image("cell.png");
		var backpack = loader.image("backpack.gif");
		float dimx = (float) (backpack.getWidth()*0.95);
		float dimy = (float) (backpack.getHeight()*0.95);
		float posX = (float) ((width / 2) - 0.5 * dimx);
		float posY = (float) (0 - backpack.getHeight() / 10);
		drawImage(graphics, backpack, posX, posY, dimx, dimy);
		dimx =(float) (cell.getWidth());
		posX = (int) ((width / 2) - 2.5 * dimx);
		posY = dimy / 6;
		dimy = (float) (cell.getHeight());
		//System.out.println(data.getInventory());
		for (var i = 0; i < 5; i++) {
			for (var j = 0; j < 3; j++) {
				drawImage(graphics, cell, posX + dimx * i, posY + dimy * j, dimx, dimy);
				//System.out.println("Objet a l'emplacement [" + j + ", " + i + "] de l'inventaire : " + inventaire.getFromXY(j, i));
				
			}
		}
		for (var i : list) {
			//drawImage(graphics, i.itemImage(), posX + dimx * i, posY + dimy * j, dimx, dimy);
			drawItem(graphics, posX + dimx * inventaire.get(i).x(), posY + dimy * inventaire.get(i).y(), dimx, dimy, i, data);
		}
	}
	
	/**
	 * Draws a green rectangle on the selected item at the specified position with the specified item and game data.
	 *
	 * @param context   The application context.
	 * @param position  The position of the item selector.
	 * @param item      The selected item.
	 * @param data      The game data.
	 */
	public void drawItemSelector(ApplicationContext context, Coordonnees position, Item item, GameData data) {
		context.renderFrame(graphics -> {
			graphics.setColor(Color.GREEN);
			graphics.draw(new Rectangle2D.Float(position.x1(), position.y1(), position.x2() - position.x1(), position.y2() - position.y1()));
		});
	}
	
	/*public void drawCombatMenu(ApplicationContext context, int height, int width, GameData data) {
		context.renderFrame(graphics -> {
			graphics.
		});
		
	}*/
	
	/**
	 * Draws the current room on the application interface based on the room type in the game data.
	 *
	 * @param context   The application context.
	 * @param height    The height of the interface.
	 * @param width     The width of the interface.
	 * @param data      The game data.
	 */
	public void drawCurrentRoom(ApplicationContext context, int height, int width, GameData data) {
		System.out.println("La salle actuelle : " + data.getCurrentRoom().getName());
		switch(data.getCurrentRoom().getName()) {
		case "corridor":
			drawCorridor(context, height, width, data, (Corridor) data.getCurrentRoom());
			break;
		case "treasure":
			drawTreasure(context, height, width, data, (Treasure) data.getCurrentRoom());
			break;
		case "healer":
			drawHealer(context, height, width, data, (Healer) data.getCurrentRoom());
			break;
		case "merchant":
			drawMerchant(context, height, width, data, (Merchant) data.getCurrentRoom());
			break;
		case "exitdoor":
			drawExitDoor(context, height, width, data, (ExitDoor) data.getCurrentRoom());
			break;
		}
	}
	
	/**
	 * Draws the healer menu on the application interface with the specified application context, width, height, and game data.
	 *
	 * @param context   The application context.
	 * @param width     The width of the interface.
	 * @param height    The height of the interface.
	 * @param data      The game data.
	 */
	public void drawMenuHealer(ApplicationContext context, float width, float height, GameData data) {
        context.renderFrame(graphics -> {
        graphics.setColor(Color.DARK_GRAY);
        graphics.fill(new Rectangle2D.Float(width / 4, height / 4, (2 * width) / 4, (2 * height) / 4));
        graphics.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.PLAIN, 30);
        graphics.setFont(font);
        graphics.drawString("Bonjour jeune aventurier", (width / 3), (height / 3));

        // Bouton "Heal 25 PV pour 5 coins"
        graphics.setColor(Color.GRAY);
        graphics.fill(new RoundRectangle2D.Float((width / 2) - 200, (height / 2) - 100, 400, 200, 50, 50));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Heal 25 PV pour 5 coins", (width / 2) - 190, (height / 2));

        // Bouton "Non merci"
        graphics.setColor(Color.GRAY);
        graphics.fill(new RoundRectangle2D.Float((width / 2) - 200, (height / 2) + 120, 400, 100, 50, 50));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Non merci", (width / 2) - 90, (height / 2) + 180);
        });
    }
	
	/**
	 * Draws the game elements on the graphics context with the specified application context, game data, and view.
	 *
	 * @param graphics  The graphics context.
	 * @param context   The application context.
	 * @param data      The game data.
	 */
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
			//drawItem(graphics, 500f, 500f, (float) (loader.image("cell.png").getWidth()), (float) (loader.image("cell.png").getHeight()), new MeleeWeapon("Common", 10, 5), data);
			if (data.getInventoryState()) {
				drawMapButton(context, (int) width, (int) height, data);
			}
			else {
				drawInventoryButton(context, (int) width, (int) height, data);
			}
			
		}

	}
	
	/**
	 * Draws the game view on the application interface using the specified application context, game data, and view.
	 *
	 * @param context   The application context.
	 * @param data      The game data.
	 * @param view      The game view.
	 */
	public static void draw(ApplicationContext context, GameData data, GameView view) {
		context.renderFrame(graphics -> view.draw(graphics, context, data));
	}

}