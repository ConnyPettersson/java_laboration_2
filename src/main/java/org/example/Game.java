package org.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
public class Game {
    private static final int WIDTH = 13;
    private static final int HEIGHT = 11;
    private static List<Monster> monsters = new ArrayList<>();
    private static List<Treasure> treasures = new ArrayList<>();
    private static List<Upgrade> upgrades = new ArrayList<>();
    private static Random random = new Random();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Position playerPosition = new Position(1, 1);
        Player player = new Player("Conan", playerPosition, 100, 10);

        Maze maze = new Maze(new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', 'E', '#'},
                {'#', ' ', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', ' ', '#'},
                {'#', ' ', '#', '#', ' ', ' ', 'T', '#', ' ', ' ', ' ', '#', '#'},
                {'#', ' ', '#', ' ', ' ', '#', ' ', '#', '#', 'X', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', ' ', '#', ' ', '#', 'U', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', ' ', '#', 'Z', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        });

        generateObjects(maze);


        System.out.println("Welcome to the Maze of Death!");
        System.out.println("You are Conan (displayed as C on the map) armed with a Broad Sword.");
        System.out.println("Your mission is to take back the treasure that was stolen by the monsters in the Maze");
        System.out.println("Along the way you may encounter upgrades that will help you along the way.");
        System.out.println("But beware of the monsters guarding the treasure!!!");
        while (true) {

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

        monsters.add(new Monster("Zylox", getRandomPosition(maze), 300, 50));
        monsters.add(new Monster("Xiltor", getRandomPosition(maze), 250, 40));
        treasures.add(new Treasure(new Item(getRandomPosition(maze)), 100));
        upgrades.add(new Upgrade(new Item(getRandomPosition(maze)), "Long Sword"));
    }

    private static Position getRandomPosition(Maze maze) {
        int x, y;
        do {
            x = random.nextInt(WIDTH);
            y = random.nextInt(HEIGHT);
        } while (!maze.isValid(new Position(x, y)));
        return new Position(x, y);
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
        for (Monster monster : monsters) {
            if (player.getX() == monster.getX() && player.getY() == monster.getY()) {
                System.out.println("You have encountered the Monster " + monster.getName() + "!");
                // logik för strid
            }
        }

        for (Treasure treasure : treasures) {
            if (player.getX() == treasure.getItemX() && player.getY() == treasure.getItemY()) {
                System.out.println("You have found a Treasure!");
                // Additional logic for collecting the treasure
            }
        }
    }
}