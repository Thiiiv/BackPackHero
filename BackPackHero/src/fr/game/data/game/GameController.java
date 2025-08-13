package fr.game.data.game;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import fr.game.data.Coordonnees;
import fr.game.data.Corridor;
import fr.game.data.Room;
import fr.game.data.character.Monster;
import fr.game.data.item.Item;
import fr.game.data.item.MeleeWeapon;
import fr.game.data.item.RangedWeapon;
import fr.game.data.item.Shield;
import fr.game.data.item.Weapon;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;


/**
 * Controls the game flow and handles user input.
 */
public class GameController {

	public GameController() {
	}
	
	/**
     * Plays the game background music.
     */
	private static void playMusic() {
		try {

			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("data/music.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			// Récupérer le contrôle du volume de la musique
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// Diminution du volume de la musique de fond de 25 décibels
			volumeControl.setValue(volumeControl.getValue() - 20f);
			var mute = false;
			if (mute) {
				volumeControl.setValue(volumeControl.getMinimum());
			}
			// Jouer la musique en boucle

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * The main game loop that processes user input and controls the game flow.
     *
     * @param context The application context.
     * @param data    The game data.
     * @param view    The game view.
     * @return true to continue the game loop, false to exit the game.
     */
	private static boolean gameLoop(ApplicationContext context, GameData data, GameView view) {
		var event = context.pollOrWaitEvent(1000000);
		if (event == null) {
			return true;
		}
		var action = event.getAction();
		if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.Q) {
			return false;
		}
		return true;

	}
	
	/**
     * Collects the names of image files in a directory.
     *
     * @param dir The directory to search for image files.
     * @return A list of image file names in the directory.
     * @throws IOException if an I/O error occurs while reading the directory.
     */
	private static List<String> collectImages(String dir) throws IOException {
		var list = new ArrayList<String>();
		var input = Files.newDirectoryStream(Path.of(dir));
		for (var entry : input) {
			list.add(entry.getFileName().toString());
		}
		return list;
	}
	
	private static void backpackHero(ApplicationContext context) throws IOException, InterruptedException {
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		var margin = 0;
		var images = new ImageLoader("data", collectImages("data"));
		var data = new GameData();
		data.addItem(0, 0, new MeleeWeapon("Common", 7, 1));
		data.addItem(0, 1, new RangedWeapon("Common", 7, 1));
		data.addItem(1, 2, new Shield("Common", 1, 10));
		var view = GameView.initGameGraphics(margin, margin, (int) Math.min(width, height) - 2 * margin, data, images);
		GameView.draw(context, data, view);
		var dimMapButton = view.getMapButtonsize();
		float[] floorCoordonnees = null;

		final DraggedItemWrapper draggedItemWrapper = new DraggedItemWrapper();
		Coordonnees originalItemPosition = null;

		while (true) {
			var event = context.pollOrWaitEvent((long) Math.pow(10, 8));

			if (event == null) {
				continue;
			}
			var action = event.getAction();

			if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.Q) {
				context.exit(0);
				return;
			}

			if (data.getMenuState()) {
				if (action == Action.POINTER_DOWN) {
					var location = event.getLocation();
					if (location != null) {
						var posMenuButton = view.getMenuButtonPosition(height, width);
						var dimPlay = view.getPlayButtonsize();
						if (data.clickOnMenuButton(location.x, location.y, posMenuButton[0], posMenuButton[1],
								dimPlay[0], dimPlay[1])) {
							data.setMenuState(false);
							GameView.draw(context, data, view);
							playMusic();
						} else {
							posMenuButton = view.getExitButtonPosition(height, width);
							if (data.clickOnMenuButton(location.x, location.y, posMenuButton[0], posMenuButton[1],
									dimPlay[0], dimPlay[1])) {
								context.exit(0);
							}
						}
					}
				}
				continue; // Skip the rest of the loop if in menu state
			}


			switch (action) {
				case POINTER_DOWN: {
					var location = event.getLocation();
					if (location != null) {
						var clickedItemInfo = data.clickOnItem(location.x, location.y);
						if (clickedItemInfo != null) {
							Item clickedItem = (Item) clickedItemInfo.values().toArray()[0];
							if (data.isItemInInventory(clickedItem)) {
								draggedItemWrapper.item = clickedItem;
								originalItemPosition = data.getInventory().get(clickedItem);
								data.getInventory().removeItemFromInventory(draggedItemWrapper.item);
								// We don't remove from objectPositions immediately, to avoid flickering
							}
						}
					}
				}
				break;

				case POINTER_MOVE: {
					if (draggedItemWrapper.item != null) {
						var location = event.getLocation();
						context.renderFrame(graphics -> {
							view.draw(graphics, context, data);
							view.drawDraggedItem(graphics, draggedItemWrapper.item, (float)location.getX(), (float)location.getY());
						});
					}
				}
				break;

				case POINTER_UP: {
					if (draggedItemWrapper.item != null) {
						var location = event.getLocation();
						Coordonnees dropPosition = view.getInventoryCell((float)location.getX(), (float)location.getY(), width, height);

						if (dropPosition != null && data.getInventory().isSpaceAvailable(dropPosition.y(), dropPosition.x(), draggedItemWrapper.item)) {
							data.getInventory().add(dropPosition.y(), dropPosition.x(), draggedItemWrapper.item);
						} else {
							// Return to original position if drop is invalid
							data.getInventory().add(originalItemPosition.y(), originalItemPosition.x(), draggedItemWrapper.item);
						}

						draggedItemWrapper.item = null;
						originalItemPosition = null;
						GameView.draw(context, data, view); // Redraw the final state
					}
				}
				break;
			default:
				break;
			}


			if (action == Action.POINTER_DOWN) {
				var location = event.getLocation();
				if (location != null && draggedItemWrapper.item == null) {
					Room CurentRoom=data.getCurrentRoom();
                    if (CurentRoom != null && CurentRoom.getName().equals("healer")) {
                    	if(data.isClickedInRoom(location.x, location.y, height, width)) {
                            view.drawMenuHealer(context,width,height, data);
                            while(true){
                                var healerEvent = context.pollOrWaitEvent((long) Math.pow(10, 8));
                                if (healerEvent == null) {
                                    continue;
                                }
                                var healerAction = healerEvent.getAction();
                                if (healerAction == Action.POINTER_DOWN) {
                                    var healerLocation = healerEvent.getLocation();
                                    if (healerLocation != null) {
					var clickOnMenuHealer = data.clickOnMenuHealer(healerLocation.x, healerLocation.y, width, height);
                                        if(clickOnMenuHealer != null){
                                            view.drawCurrentRoom(context, (int) height, (int) width, data);
                                            break;
                                        }
                                    }
                                }
                            }
                    	}
                    	else {
                    		System.out.println("On clique à l'extérieur");
                    	}
                    }
					var detectButton = data.clickOnButton(location.x, location.y, (int) (width - dimMapButton[0]),
							dimMapButton[1], dimMapButton[0], dimMapButton[1] * 2);
					// System.out.println(detectButton);
					if (detectButton != null) {
						switch (detectButton) {
						case "mapButton":
							floorCoordonnees = view.getMapCoords((int) height, (int) width);
							break;
						case "inventoryButton":
							GameView.draw(context, data, view);
							break;
						}
						data.changeButtonsState();
					}
					// System.out.println("floorCoordonnees : " + floorCoordonnees);
					if (floorCoordonnees != null) {
						// System.out.println(floorCoordonnees[0] + " " + floorCoordonnees[1] + " " +
						// (int) floorCoordonnees[2] + " " + (int) floorCoordonnees[3]);
						var detectRoom = data.clickOnMap(location.x, location.y, floorCoordonnees[0],
								floorCoordonnees[1], (int) floorCoordonnees[2], (int) floorCoordonnees[3]);
						// System.out.println("detectRoom : " + detectRoom);
						if (detectRoom != null) {
							// System.out.println("detectRoom : " + detectRoom);
							view.goToRoom(context, (int) height, (int) width, data, detectRoom);
							if (data.getCurrentRoom().getName().equals("corridor")) {
								var room = (Corridor) data.getCurrentRoom();
								if (room.isThereMonster() && !room.areMonstersDead()) {
									CombatController combat = new CombatController(context, data, view, width, height);
									try {
										combat.startCombat(room);
									} catch (InterruptedException e) {
										Thread.currentThread().interrupt();
										e.printStackTrace();
									}
								}
							}
						}
					}
					/*
					=> Cette partie du code a été désactivé pour le motif suivant : Code imcomplet
					
					var isItemHere = data.clickOnItem(location.x, location.y);
					if (isItemHere != null) {
						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("Les items présents dans l'interface AVANT le déplacement : "
								+ data.getObjectsPosition());
						System.out.println(
								"----------------------------------------------------------------------------------------");
						Item clickedItem = (Item) isItemHere.values().toArray()[0];
						Coordonnees clickedItemPos = (Coordonnees) isItemHere.keySet().toArray()[0];
						// System.out.println("Le joueur a cliqué sur l'item : \n" + clickedItem);
						view.drawItemSelector(context, clickedItemPos, clickedItem, data);
						data.removeObjectPosition(clickedItem, clickedItemPos.x1(), clickedItemPos.y1(),
								clickedItemPos.x2(), clickedItemPos.y2());
						boolean isItemSelected = true;
						while (isItemSelected) {
							event = context.pollOrWaitEvent((long) Math.pow(10, 8));
							action = event.getAction();
							if (action == Action.POINTER_DOWN) {
								location = event.getLocation();
								if (location != null) {
									// data.addObjectPosition(clickedItem, clickedItemPos.x1(), clickedItemPos.y1(),
									// clickedItemPos.x2(), clickedItemPos.y2());
									view.drawItem(context, location.x, location.y, clickedItem, data);
									isItemSelected = false;

								}
							}
						}

					}*/
				}
			}
			if ((action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) && event.getKey() == KeyboardKey.Q) {
				context.exit(0);
			}
			// System.out.println("MapButtonState : " + data.getMapState());
			// System.out.println("InventoryButtonState : " +
			// data.getInventoryState());
		}
	}
	
	/**
	 * The entry point of the game application.
	 *
	 * @param args The command-line arguments.
	 */
	public static void main(String[] args) {
		Application.run(Color.WHITE, t -> {
			try {
				backpackHero(t);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}