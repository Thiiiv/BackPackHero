package fr.uge.zen5.Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.TextArea;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

class Area {
	private Rectangle2D.Float ellipse = new Rectangle2D.Float(0, 0, 0, 0);

	void draw(ApplicationContext context, float x, float y) {
		context.renderFrame(graphics -> {
			// hide the previous rectangle
			//graphics.setColor(Color.ORANGE);


			// show a new ellipse at the position of the pointer
			graphics.setColor(Color.GREEN);
			ellipse = new Rectangle2D.Float(x - 50, y - 50, 100, 100);
			graphics.fill(ellipse);
		});
	}
}

public class Demo {

	private static void checkRange(double min, double value, double max) {
		if (value < min || value > max) {
			throw new IllegalArgumentException("Invalid coordinate: " + value);
		}
	}

	public static void main(String[] args) {
		Application.run(Color.ORANGE, context -> {
			var screenInfo = context.getScreenInfo();
			var width = screenInfo.getWidth();
			var height = screenInfo.getHeight();
			System.out.println("size of the screen (" + width + " x " + height + ")");

			context.renderFrame(graphics -> {
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
			});

			var area = new Area();
			var Arene = new Stages(2, 2);
			Arene.add(new Room(), 0, 0);
			Arene.add(new Room(), 0, 1);
			Arene.add(new Corridor(), 1, 0);
			Arene.add(new Room(), 1, 1);
			Arene.displayStages(context);
			while (true) {
				var event = context.pollOrWaitEvent(10);
				if (event == null) {
					continue;
				}
				var action = event.getAction();
				if (action == Action.KEY_PRESSED) {
					System.out.println(event.getKey());
				} else if (event.getKey() == KeyboardKey.Q) {
					context.exit(0);
					return;
				} else {
					var location = event.getLocation();
					checkRange(0, location.x, width);
					checkRange(0, location.y, height);
					if (location.y < (height/2)+100 && location.y > (height/2)-100 && location.x > (width/2)+25 && location.x < (width/2)+525) {
						context.renderFrame(graphics -> {
							graphics.setColor(Color.GREEN);
							graphics.fill(new RoundRectangle2D.Float((width/2)+25, (height/2)-100, 500, 200, 50, 50));
							graphics.setColor(Color.BLACK);
							Font font = new Font("Arial", Font.PLAIN, 40); // Crée une police Arial, de style normal et de taille 12
							graphics.setFont(font);
							graphics.drawString("Quitter", (width/2)+215, (height/2));
						});
						try {
						    Thread.sleep(500); // Pause de 1 secondes
						} catch (InterruptedException e) {
						    // Gérer les exceptions
						}
						context.exit(0);
						return;
					}
					area.draw(context, location.x, location.y);
				}

			}
		});
	}
}