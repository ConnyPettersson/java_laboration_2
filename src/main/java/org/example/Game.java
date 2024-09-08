package org.example;
import java.util.*;

public class Game {
    private static final int WIDTH = 13;
    private static final int HEIGHT = 11;
    private static final List<Monster> monsters = new ArrayList<>();
    private static final List<Treasure> treasures = new ArrayList<>();
    private static final List<Upgrade> upgrades = new ArrayList<>();
    private static final Random random = new Random();
    private static boolean treasureFound = false;
    private static boolean gameRunning = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Position playerPosition = new Position(1, 1);
        Player player = new Player("Conan", playerPosition, 100, 10);

        Maze maze = new Maze(new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', 'E', '#'},
                {'#', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#', ' ', ' ', '#'},
                {'#', ' ', '#', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', '#'},
                {'#', ' ', '#', ' ', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        });


        generateObjects(maze);

        System.out.println();
        System.out.println("Welcome to the Maze of Death!");
        System.out.println("You are Conny the Destroyer, a fearless warrior (displayed as 'C' on the map).");
        System.out.println("Your mission: Retrieve the hidden treasure stolen by the monsters and escape through the Exit 'E'.");
        System.out.println();
        System.out.println("Beware! The maze is filled with deadly dangers:");
        System.out.println("- The monsters Xiltor and Zylox lurk in the shadows, ready to strike when you least expect it.");
        System.out.println("- The treasure is well-hidden, and you must find it before you can escape.");
        System.out.println("- Upgrades are scattered throughout the maze, including weapons and potions to aid your quest.");
        System.out.println();
        System.out.println("Note: All monsters, treasures, and upgrades are invisible until you discover them.");
        System.out.println("Move wisely, stay alert, and good luck on your journey!");
        System.out.println();

        while (gameRunning) {
            maze.displayMaze(player);
            System.out.println(player);
            System.out.println("Enter your move with keys (WASD): ");
            String move = sc.nextLine();
            handlePlayerMove(move, player, maze);
        }
    }

    private static void generateObjects(Maze maze) {
        monsters.clear();
        treasures.clear();
        upgrades.clear();

        Position monsterPosition1 = getRandomPosition(maze);
        Position monsterPosition2 = getRandomPosition(maze);
        Position treasurePosition = getRandomPosition(maze);
        Position upgradePosition = getRandomPosition(maze);

        monsters.add(new Monster("Zylox", monsterPosition1, 300));
        monsters.add(new Monster("Xiltor", monsterPosition2, 250));
        treasures.add(new Treasure(new Item(treasurePosition), 1000));
        upgrades.add(new Upgrade(new Item(upgradePosition), "Long Sword"));

        for (int i = 0; i < 3; i++) {
            Position healthPotionPosition = getRandomPosition(maze);
            upgrades.add(new Upgrade(new Item(healthPotionPosition), "Health Potion"));
        }
    }

    private static Position getRandomPosition(Maze maze) {
        int newX, newY;
        Position newPosition;
        do {
            newX = random.nextInt(WIDTH);
            newY = random.nextInt(HEIGHT);
            newPosition = new Position(newX, newY);
        } while (!maze.isValid(newPosition));

        return newPosition;
    }


    private static void handlePlayerMove(String move, Player player, Maze maze) {
        int dx = 0, dy = 0;
        switch (move.toUpperCase()) {
            case "W":
                dy = -1;
                break;
            case "S":
                dy = 1;
                break;
            case "A":
                dx = -1;
                break;
            case "D":
                dx = 1;
                break;
            default:
                System.out.println("Invalid key");
                return;
        }

        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        Position newPosition = new Position(newX, newY);

        if (maze.isValid(newPosition)) {
            player.move(dx, dy, maze);
        } else {
            System.out.println("Move blocked by a wall.");
        }
        checkEncounters(player);
    }

    private static void checkEncounters(Player player) {

        if (player.getX() == 11 && player.getY() == 1) {
            if (treasureFound) {
                System.out.println("You have reached the exit with the treasure! You have won the game!");
                System.exit(0);
                gameRunning = false;
            } else {
                System.out.println("You cannot exit yet! You still need to find the treasure.");
                return;
            }
        }

        Iterator<Monster> monsterIterator = monsters.iterator();
        while (monsterIterator.hasNext()) {
            Monster monster = monsterIterator.next();
            if (player.getX() == monster.getX() && player.getY() == monster.getY()) {
                System.out.println("You have encountered the Monster " + monster.getName() + "!");

                battle(player, monster);

                if (player.getHealth() <= 0) {
                    System.out.println("You have been defeated by " + monster.getName());
                    System.out.println("Game Over!!!");
                    System.exit(0);
                }

                if (monster.getHealth() <= 0) {
                    System.out.println("You have slain " + monster.getName() + "!");
                    monsterIterator.remove();
                }
                return;
            }
        }

        Iterator<Treasure> treasureIterator = treasures.iterator();
        while (treasureIterator.hasNext()) {
            Treasure treasure = treasureIterator.next();
            Item treasureItem = treasure.item();
            Position treasurePosition = treasureItem.position();

            if (player.getX() == treasurePosition.getX() && player.getY() == treasurePosition.getY()) {
                System.out.println("You have found the stolen treasure of " + treasure.value() + " gold coins!!! ");
                System.out.println("Move fast to the exit!!!");
                treasureFound = true;
                treasureIterator.remove();
            }
        }

        Iterator<Upgrade> upgradeIterator = upgrades.iterator();
        while (upgradeIterator.hasNext()) {
            Upgrade upgrade = upgradeIterator.next();
            Item upgradeItem = upgrade.item();
            Position upgradePosition = upgradeItem.position();

            if (player.getX() == upgradePosition.getX() && player.getY() == upgradePosition.getY()) {
                if (upgrade.type().equals("Long Sword")) {
                    System.out.println("You have found a magic item: " + upgrade.type() + "!");
                    player.upgradeWeapon("Long Sword");
                    upgradeIterator.remove();
                } else if (upgrade.type().equals("Health Potion")) {
                    System.out.println("You have found a Health Potion! Your health is restored");
                    player.setHealth(100);
                    upgradeIterator.remove();
                }
            }
        }
    }

        private static void battle(Player player, Monster monster) {
        System.out.println("A fierce battle begins between " + player.getName() + " and " + monster.getName() + "!");

        while (player.getHealth() > 0 && monster.getHealth() > 0) {
            int playerDamage = player.getDamage();
            monster.setHealth(monster.getHealth() - playerDamage);
            System.out.println(player.getName() + " strikes " + monster.getName() + " for " + playerDamage + " damage!");

            if (monster.getHealth() <= 0) {
                System.out.println(monster.getName() + " has been defeated!");
                break;
            }

            int monsterDamage = 15;
            player.setHealth(player.getHealth() - monsterDamage);
            System.out.println(monster.getName() + " strikes " + player.getName() + " for " + monsterDamage + " damage!");

            if (player.getHealth() <= 0) {
                System.out.println(player.getName() + " has been slain by " + monster.getName() + "!");
                break;
            }
        }
    }
}
