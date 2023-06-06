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
		data.addItem(0, 2, new Shield("Common", 1, 10));
		var view = GameView.initGameGraphics(margin, margin, (int) Math.min(width, height) - 2 * margin, data, images);
		GameView.draw(context, data, view);
		var dimMapButton = view.getMapButtonsize();
		float[] floorCoordonnees = null;
		while (true) {
			var event = context.pollOrWaitEvent((long) Math.pow(10, 8));
			/*
			 * if (!gameLoop(context, data, view)) { //
			 * System.out.println("Thank you for quitting!"); context.exit(0); }
			 */
			if (event == null) {
				continue;
			}
			var action = event.getAction();
			// System.out.println("Une action a été effectué : " + action);
			if (action == Action.POINTER_DOWN) {
				var location = event.getLocation();
				/*
				 * System.out.println(data.clickOnButton(location.x, location.y, (int) (width -
				 * dimMapButton[0]), 0, dimMapButton[0], dimMapButton[1]));
				 * System.out.println("x = " + location.x + " y = " + location.y +
				 * " buttonX1 = " + (width - dimMapButton[0]) + " buttonY1 = " + 0 +
				 * " buttonX2 = " + (width - dimMapButton[0]) + dimMapButton[0] + " buttonY2 = "
				 * + dimMapButton[1]);
				 */
				if (location != null) {
					if (data.getMenuState()) {
						var posMenuButton = view.getMenuButtonPosition(height, width);
						var dimPlay = view.getPlayButtonsize();
						// System.out.println("positionPlay : " + positionPlay[0] + ", " +
						// positionPlay[1] + " dimPlay : " + dimPlay[0] + ", " + dimPlay[1]);
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
					} else {
						System.out.println("posX = " + location.x + " posY = " + location.y);
					}
					Room CurentRoom=data.getCurrentRoom();
                    if (CurentRoom.getName()=="healer") {
                    	if(data.isClickedInRoom(location.x, location.y, height, width)) {
                            System.out.println("---------------------------------------");
                            System.out.println("Healer Work");
                            System.out.println("---------------------------------------");
                            view.drawMenuHealer(context,width,height, data);
                            while(true){
                                event = context.pollOrWaitEvent((long) Math.pow(10, 8));
                                if (event == null) {
                                    continue;
                                }
                                action = event.getAction();
                                if (action == Action.POINTER_DOWN) {
                                    location = event.getLocation();
                                    if (location != null) {
                                    	System.out.println("location : " + " x = " + location.x + " y = " + location.y);
                                    	var clickOnMenuHealer = data.clickOnMenuHealer(location.x, location.y, width, height);
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
							System.out.println("Il faut dessiner le bouton map");
							System.out.println("Voici les états des boutons : InventoryButton = " + data.getInventoryState() + " MapButton = " + data.getMapState());
							floorCoordonnees = view.drawMap(context, (int) height, (int) width, data);
							view.drawInventoryButton(context, (int) height, (int) width, data);
							break;
						case "inventoryButton":
							System.out.println("Il faut dessiner le bouton inventaire");
							System.out.println("Voici les états des boutons : InventoryButton = " + data.getInventoryState() + " MapButton = " + data.getMapState());
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
							if (data.getCurrentRoom().getName() == "corridor") {
								var room = (Corridor) data.getCurrentRoom();
								if (room.isThereMonster()) {
									if (!room.areMonstersDead()) {
										var monsters = room.getMonsters();
										Monster monster1 = null;
										Monster monster2 = null;
										if (monsters.size() == 2) {
											monster1 = monsters.get(1);
											monster2 = monsters.get(0);
										}
										else {
											monster1 = monsters.get(0);
										}
										monster1.setSelected(true);
										//System.out.println("Je suis au dessus de la boucle while");
										var tour = "hero";
										var isAttack1 = true;
										var isAttack2 = true;
										var count = 0;
										Weapon clickedWeapon;
										while (room.areMonstersDead() == false) {
											if (count == 0) {
												if (monster1.preventAction() == "attack") {
													isAttack1 = true;
												}
												else {
													isAttack1 = false;
												}
												if (monster2 != null) {
													if (monster2.preventAction() == "attack") {
														isAttack2 = true;
													}
													else {
														isAttack2 = false;
													}
												}
												view.drawCurrentRoom(context, (int) height, (int) width, data);
												count = 1;
											}
											//System.out.println("Je suis dans la boucle while\nVoici l'état des monsters de la salle : " + room.areMonstersDead());
											event = context.pollOrWaitEvent((long) Math.pow(10, 8));
											action = event.getAction();
											if (action == Action.POINTER_DOWN) {
												location = event.getLocation();
												if (location != null) {
													System.out.println("Localisation du clic : " + location.x + " | " + location.y);
													var isItemHere = data.clickOnItem(location.x, location.y);
													System.out.println("isItemHere : " + isItemHere);
													if (isItemHere != null) {
														Item clickedItem = (Item) isItemHere.values().toArray()[0];
														Coordonnees clickedItemPos = (Coordonnees) isItemHere.keySet().toArray()[0];
														if (data.isItemInInventory(clickedItem)) {
															if (clickedItem.isWeapon()) {
																clickedWeapon = (Weapon) clickedItem;
																data.getHero().equip(clickedWeapon);
																System.out.println("\nJ'ai cliqué sur une Item de type Weapon\n");
																if (monster1.health() <= 0) {
																	System.out.println("-----------------------------------------\nLe premier monstre est mort\n-----------------------------------------");
																	monster1.setSelected(false);
																	if (monster2 != null) {
																		monster2.setSelected(true);
																	}
																}
																if (tour == "hero") {
																	if (clickedWeapon.getName() != "shield") {
																		if (monster1.isSelected()) {
																			data.getHero().attack(monster1);
																			if (isAttack1) {
																				monster1.setState("preventAttack");
																			}
																			else {
																				monster1.setState("preventDefense");
																			}
																		}
																		if (monster2 != null) {
																			if (monster2.isSelected()) {
																				data.getHero().attack(monster2);
																				if (isAttack2) {
																					monster2.setState("preventAttack");
																				}
																				else {
																					monster2.setState("preventDefense");
																				}
																			}
																		}
																	}
																	else if (clickedWeapon.getName() == "shield") {
																		System.out.println("\nJe défend\n");
																		data.getHero().defend();
																	}
																	System.out.println("Energie dépensé pour le monstre 1 : " + clickedWeapon.getEnergyPoint());
																	System.out.println("Les points de vies du monstre : " + monster1.health());
																	view.drawCurrentRoom(context, (int) height, (int) width, data);
																	if (room.areMonstersDead() == true) {
																		break;
																	}
																	if (data.getHero().getEnergyPoint() > 0) {
																		continue;
																	}
																}
																if (data.getHero().getEnergyPoint() == 0) {
																	tour = "monster";
																	count = 1;
																	data.getHero().resetEnergy();
																}
															}
														}
													}
												}
											}
											if (tour == "monster") {
												System.out.println("\nJe suis dans la condition de la tour des monstres\n");
												Thread.sleep(Duration.ofMillis(600));
												if (monster1.isAlive()) {
													if (isAttack1) {
														monster1.attack(data.getHero());
														count = 0;
													}
													else {
														count = 0;
														monster1.defend();
													}
													view.drawCurrentRoom(context, (int) height, (int) width, data);
												}
												if (monster2 != null && monster2.isAlive()) {
													Thread.sleep(Duration.ofMillis(600));
													if (isAttack2) {
														monster2.attack(data.getHero());
														count = 0;
													}
													else {
														count = 0;
														monster2.defend();
													}
													view.drawCurrentRoom(context, (int) height, (int) width, data);
												}
												tour = "hero";
												data.getHero().resetDefense();
											}
											if ((action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) && event.getKey() == KeyboardKey.Q) {
												context.exit(0);
											}
											if (data.getHero().health() <= 0) {
												view.drawEndMenu(context, (int) height, (int) width, data);
												Thread.sleep(Duration.ofSeconds(2));
												context.exit(0);
											}
											if (monster1.isAlive() && count == 1) {
												monster1.resetDefense();
											}
											if (monster2 != null && monster2.isAlive() && count == 1) {
												monster2.resetDefense();
											}
										}
										data.getHero().resetEnergy();
										data.getHero().resetDefense();
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