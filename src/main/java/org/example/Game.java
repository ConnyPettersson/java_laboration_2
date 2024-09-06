package org.example;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player player = new Player("Conan", 1, 1, 100, 10);
        Monster monster = new Monster("Zylox", 5, 5, 300, 50);
        Monster monster2 = new Monster("Xiltor", 10, 12, 250, 40);

        Item item = new Item(1,2);
        Treasure treasure = new Treasure(item, 100);
        Upgrade upgrade = new Upgrade(item, "Long Sword");

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
            int dx = 0, dy = 0;
            switch (move.toUpperCase()) {
                case "W": dy = -1; break;
                case "S": dy = 1; break;
                case "A": dx = -1; break;
                case "D": dx = 1; break;
                default: System.out.println("Invalid key"); continue;
            }
            player.move(dx, dy, maze);
        }
    }
}
