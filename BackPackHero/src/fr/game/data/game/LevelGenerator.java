package fr.game.data.game;

import fr.game.data.Floor;
import fr.game.data.Room;
import fr.game.data.Wall;
import fr.game.data.Corridor;
import fr.game.data.Treasure;
import fr.game.data.Healer;
import fr.game.data.Merchant;
import fr.game.data.ExitDoor;

import java.util.Random;

public class LevelGenerator {
    private static final int CORRIDOR_CHANCE = 70; // 70% chance to be a corridor
    private static final int TREASURE_CHANCE = 10;
    private static final int HEALER_CHANCE = 5;
    private static final int MERCHANT_CHANCE = 5;

    public static Floor generateFloor(int width, int height) {
        Floor floor = new Floor(width, height);
        Random random = new Random();

        // Fill with walls initially
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                floor.add(x, y, new Wall());
            }
        }

        // Simple random walk to create paths
        int startX = random.nextInt(width);
        int startY = 0;
        floor.add(startX, startY, new Corridor(0)); // Starting room

        int currentX = startX;
        int currentY = startY;

        int steps = (width * height) / 2; // Number of steps for the walk

        for (int i = 0; i < steps; i++) {
            int direction = random.nextInt(4);
            switch (direction) {
                case 0: // North
                    if (currentY > 0) currentY--;
                    break;
                case 1: // East
                    if (currentX < width - 1) currentX++;
                    break;
                case 2: // South
                    if (currentY < height - 1) currentY++;
                    break;
                case 3: // West
                    if (currentX > 0) currentX--;
                    break;
            }

            if (floor.getRoom(currentY, currentX) instanceof Wall) {
                int roomType = random.nextInt(100);
                if (roomType < CORRIDOR_CHANCE) {
                    floor.add(currentY, currentX, new Corridor(0));
                } else if (roomType < CORRIDOR_CHANCE + TREASURE_CHANCE) {
                    floor.add(currentY, currentX, new Treasure());
                } else if (roomType < CORRIDOR_CHANCE + TREASURE_CHANCE + HEALER_CHANCE) {
                    floor.add(currentY, currentX, new Healer());
                } else if (roomType < CORRIDOR_CHANCE + TREASURE_CHANCE + HEALER_CHANCE + MERCHANT_CHANCE) {
                    floor.add(currentY, currentX, new Merchant());
                } else {
                    floor.add(currentY, currentX, new Corridor(0)); // Default to corridor
                }
            }
        }

        // Place the exit door at the bottom
        int exitX = random.nextInt(width);
        floor.add(exitX, height - 1, new ExitDoor());

        // Ensure the starting room is a corridor
        floor.add(startX, startY, new Corridor(0));
        floor.getRoom(startY, startX).setHeroHere(true);

        return floor;
    }
}
