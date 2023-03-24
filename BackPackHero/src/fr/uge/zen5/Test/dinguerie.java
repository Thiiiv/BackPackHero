package essai;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;

public class dinguerie {

	private static void checkRange(double min, double value, double max) {
		if (value < min || value > max) {
			throw new IllegalArgumentException("Invalid coordinate: " + value);
		}
	}
	
	public static void main(String[] args) {
		Application.run(Color.ORANGE, context -> {
			var counter = 0;
			var screenInfo = context.getScreenInfo();
			var width = screenInfo.getWidth();
			var height = screenInfo.getHeight();
			System.out.println("size of the screen (" + width + " x " + height + ")");

			context.renderFrame(graphics -> {
				graphics.setColor(Color.GREEN);
				graphics.fill(new Rectangle2D.Float(0, 0, width, height));
				graphics.setColor(Color.MAGENTA);
				graphics.fill(new Ellipse2D.Float(width/2, height/2, 40, 40));
			});

			
		});
	
}
	
}

