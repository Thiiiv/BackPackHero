package fr.uge.zen5.Test;

import java.util.ArrayList;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.TextArea;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Stages {
	private final int[][] matrice;
	public Stages(int colonne, int ligne) {
		this.matrice = new int[ligne][colonne];
	}
	public void add(StageElement object, int ligne, int colonne) {
		matrice[ligne][colonne] = object.typeOfZone();
	}
	public void displayStages(ApplicationContext context) {
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		var size = 500;
		int position[] = {(int) width-250, 0};
		context.renderFrame(graphics -> {
			graphics.setColor(Color.WHITE);
			graphics.fill(new Rectangle2D.Float(position[0], position[1], size, size));
			var longueur = 20;
			var largeur = 20;
			position[0] += 5;
			position[1] += 5;
			graphics.setColor(Color.BLACK);
			for (var ligne = 0; ligne < matrice.length; ligne++) {
				for (var colonne = 0; colonne < matrice[0].length; colonne++) {
					if (matrice[ligne][colonne] == 1) {
						graphics.setColor(Color.ORANGE);
						graphics.fill(new Rectangle2D.Float(position[0], position[1], longueur, largeur));
					}
					else {
						graphics.setColor(Color.RED);
						graphics.fill(new Rectangle2D.Float(position[0], position[1], longueur, largeur));
					}
					position[0] += 25;
				}
			}
		});
	}
}
