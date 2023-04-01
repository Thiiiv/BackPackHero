package fr.game.data.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

import fr.game.data.ImageLoader;
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
		graphics.fill(new Rectangle2D.Float(0, 0, width, height));
		graphics.setColor(Color.RED);
		graphics.fill(new RoundRectangle2D.Float((width/2)-525, (height/2)-100, 500, 200, 50, 50));
		graphics.fill(new RoundRectangle2D.Float((width/2)+25, (height/2)-100, 500, 200, 50, 50));
		graphics.setColor(Color.BLACK);
		Font font = new Font("Arial", Font.PLAIN, 40); // Crée une police Arial, de style normal et de taille 12
		graphics.setFont(font);
		graphics.drawString("Jouer", (width/2)-315, (height/2));
		graphics.drawString("Quitter", (width/2)+215, (height/2));
	}
	
	public void drawRoom() {
		
	}
	
	public void drawFloor() {
		
	}
	
	public void drawInventory(Graphics2D graphics, int height, int width, GameData data) {
		var inventaire = data.getInventory();
		var list = inventaire.showContains();
		var image = loader.blank(); 
		this.drawImage(graphics, image, 960-width/4, 0, width/4, height/4, data);
		}
	
	public void draw(Graphics2D graphics, ApplicationContext context, GameData data) {
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		drawMenu(graphics, (int) width, (int) height, data);
	}
	
	public static void draw(ApplicationContext context, GameData data, GameView view) {
		context.renderFrame(graphics -> view.draw(graphics, context, data));
	}
	
	
}