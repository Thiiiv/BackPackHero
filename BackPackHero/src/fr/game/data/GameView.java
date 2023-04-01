package fr.game.data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;
import fr.umlv.zen5.ApplicationContext;

public record GameView(GameData data, ImageLoader loader) {
	public static GameView initGameGraphics(int xOrigin, int yOrigin, int length, GameData data,
			ImageLoader loader) {
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
		graphics.fill(new RoundRectangle2D.Float((height/2)-525, (width/2)-100, 500, 200, 50, 50));
		graphics.fill(new RoundRectangle2D.Float((height/2)+25, (width/2)-100, 500, 200, 50, 50));
		graphics.setColor(Color.BLACK);
		Font font = new Font("Arial", Font.PLAIN, 40); // Crée une police Arial, de style normal et de taille 12
		graphics.setFont(font);
		graphics.drawString("Jouer", (height/2)-315, (width/2));
		graphics.drawString("Quitter", (height/2)+215, (width/2));
	}
	
	public void drawRoom() {
		
	}
	
	public void drawFloor() {
		
	}
	
	public void drawMap(Graphics2D graphics, int height, int width, GameData data) {
		var map = loader.image("mapButton.png");
		drawImage(graphics, map, width-map.getWidth(), map.getHeight(), map.getWidth(), map.getHeight());
	}
	
	public void drawBackground(Graphics2D graphics, int height, int width, GameData data) {
		var image = loader.image("background5.jpg");
		drawImage(graphics, image, 0, 0, width, height);
	}
	
	public void drawHero(Graphics2D graphics, int height, int width, GameData data) {
		var image = loader.image("hero-4.png");
		float dimx = (float) (image.getWidth()*1.5);
		float dimy = (float) (image.getHeight()*1.5);
		float posX = 25;
		float posY = height-image.getHeight()*2;
		drawImage(graphics, image, posX, posY, dimx, dimy);
	}
	
	public void drawInventory(Graphics2D graphics, int height, int width, GameData data) {
		var inventaire = data.getInventory();
		var list = inventaire.showContains();
		var cell = loader.image("cell.png");
		var backpack = loader.image("backpack.gif");
		float dimx = (float) (backpack.getWidth());
		float dimy = (float) (backpack.getHeight());
		float posX = (int) ((width/2)-0.5*dimx);
		float posY = (float) (0-backpack.getHeight()/10);
		drawImage(graphics, backpack, posX, posY, dimx, dimy);
		dimx = cell.getWidth()/2;
		posX = (int) ((width/2)-2.5*dimx);
		posY = dimy/4;
		dimy = cell.getHeight()/2;
		for (var i = 0; i < 5; i++) {
			for (var j = 0; j < 3; j++) {
				drawImage(graphics, cell, posX+dimx*i, posY+dimy*j, dimx, dimy);
			}
		}
		
		
	}
	
	public void draw(Graphics2D graphics, ApplicationContext context, GameData data) {
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getHeight();
		var height = screenInfo.getWidth();
		//drawMenu(graphics, (int) width, (int) height, data);
		drawBackground(graphics, (int) width, (int) height, data);
		drawInventory(graphics, (int) width, (int) height, data);
		drawHero(graphics, (int) width, (int) height, data);
		drawMap(graphics, (int) width, (int) height, data);
	}
	
	public static void draw(ApplicationContext context, GameData data, GameView view) {
		context.renderFrame(graphics -> view.draw(graphics, context, data));
	}
	
	
}