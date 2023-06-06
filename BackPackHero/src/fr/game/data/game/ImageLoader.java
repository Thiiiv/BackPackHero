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
	 * Utility class for loading and storing images from a specified directory.
	 */
	private final HashMap<String,BufferedImage> images;

	
	/**
     * Constructs an ImageLoader object with the specified directory and image filenames.
     *
     * @param dir  The directory path where the images are located.
     * @param pics The list of image filenames to load.
     * @throws NullPointerException if the list of image filenames is null.
     */
	public ImageLoader(String dir, List<String> pics) {
		Objects.requireNonNull(pics);

		images = new HashMap<String, BufferedImage>();
		for (var i = 0; i < pics.size(); i++) {
			setImage(pics.get(i), dir, pics.get(i));
		}
	}
	
	 /**
     * Sets the image with the specified name by loading it from the directory path and image path.
     *
     * @param imageName The name of the image.
     * @param dirPath   The directory path where the image is located.
     * @param imagePath The path of the image file.
     * @throws NullPointerException if the directory path or image path is null.
     * @throws RuntimeException     if an I/O error occurs while reading the image file.
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
     * Returns the "blank" image.
     *
     * @return The "blank" image.
     */
	public BufferedImage blank() {
		return images.get("blank");
	}
	
	

    /**
     * Returns the image with the specified name.
     *
     * @param imageName The name of the image.
     * @return The image with the specified name.
     */
	public BufferedImage image(String imageName) {
		return images.get(imageName);
	}

	/**
	 * Returns the number of images loaded in the ImageLoader.
	 *
	 * @return The number of images loaded.
	 */
	public int size() {
		return images.size();
	}
}