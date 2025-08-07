package fr.game.data.game;

import java.time.Duration;

import fr.game.data.Corridor;
import fr.game.data.character.Monster;
import fr.game.data.item.Item;
import fr.game.data.item.Weapon;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

public class CombatController {
    private final ApplicationContext context;
    private final GameData data;
    private final GameView view;
    private final float width;
    private final float height;

    public CombatController(ApplicationContext context, GameData data, GameView view, float width, float height) {
        this.context = context;
        this.data = data;
        this.view = view;
        this.width = width;
        this.height = height;
    }

    public void startCombat(Corridor room) throws InterruptedException {
        if (room.isThereMonster() && !room.areMonstersDead()) {
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
            var tour = "hero";
            var isAttack1 = true;
            var isAttack2 = true;
            var count = 0;
            Weapon clickedWeapon;
            while (room.areMonstersDead() == false) {
                if (count == 0) {
                    if (monster1.preventAction().equals("attack")) {
                        isAttack1 = true;
                    }
                    else {
                        isAttack1 = false;
                    }
                    if (monster2 != null) {
                        if (monster2.preventAction().equals("attack")) {
                            isAttack2 = true;
                        }
                        else {
                            isAttack2 = false;
                        }
                    }
                    view.drawCurrentRoom(context, (int) height, (int) width, data);
                    count = 1;
                }
                var event = context.pollOrWaitEvent((long) Math.pow(10, 8));
                if(event == null) continue;

                var action = event.getAction();
                if (action == Action.POINTER_DOWN) {
                    var location = event.getLocation();
                    if (location != null) {
                        var isItemHere = data.clickOnItem((float)location.getX(),(float) location.getY());
                        if (isItemHere != null) {
                            Item clickedItem = (Item) isItemHere.values().toArray()[0];
                            if (data.isItemInInventory(clickedItem)) {
                                if (clickedItem.isWeapon()) {
                                    clickedWeapon = (Weapon) clickedItem;
                                    data.getHero().equip(clickedWeapon);
                                    if (monster1.health() <= 0) {
                                        monster1.setSelected(false);
                                        if (monster2 != null) {
                                            monster2.setSelected(true);
                                        }
                                    }
                                    if (tour.equals("hero")) {
                                        if (!clickedWeapon.getName().equals("shield")) {
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
                                        else if (clickedWeapon.getName().equals("shield")) {
                                            data.getHero().defend();
                                        }
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
                if (tour.equals("monster")) {
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
                if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.Q) {
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
