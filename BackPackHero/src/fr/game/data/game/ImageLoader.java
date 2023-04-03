package fr.game.data.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;


public class ImageLoader {
	/**
	 * Array in which retrieved images are stored.
	 */
	private final HashMap<String,BufferedImage> images;

	/**
	 * Creates a new ImageLoader that will retrieve images from files.
	 * 
	 * @param dir   Directory name where the files are located.
	 * @param blank File name where the image for the back of cards is located.
	 * @param pics  File names where the images for the faces of the cards are
	 *              located.
	 */
	public ImageLoader(String dir, List<String> pics) {
		Objects.requireNonNull(pics);

		images = new HashMap<String, BufferedImage>();
		for (var i = 0; i < pics.size(); i++) {
			setImage(pics.get(i), dir, pics.get(i));
		}
	}

	/**
	 * Retrieve a new image from a file, and stores it into a dedicated array.
	 * 
	 * @param position  Position (i.e., ID + 1) of the image.
	 * @param dirPath   Directory name.
	 * @param imagePath File name.
	 */
	private void setImage(String imageName, String dirPath, String imagePath) {
		Objects.requireNonNull(dirPath);
		Objects.requireNonNull(imagePath);
		var path = Path.of(dirPath, imagePath);
		try (var input = Files.newInputStream(path)) {
			images.put(imageName, ImageIO.read(input));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the image showing the back of cards.
	 * 
	 * @return Image showing the back of cards.
	 */
	public BufferedImage blank() {
		return images.get("blank");
	}

	/**
	 * Gets the image showing the face of a card.
	 * 
	 * @param i ID of the card.
	 * @return Image of the face of the card.
	 */
	public BufferedImage image(String imageName) {
		return images.get(imageName);
	}

	/**
	 * Gets the number of image faces stored.
	 * 
	 * @return Number of image faces of stored.
	 */
	public int size() {
		return images.size();
	}
}