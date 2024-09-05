package org.example;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player player = new Player("Conan", 1, 3, 100, 10);
        Monster monster = new Monster("Zylox", 5, 5, 300, 50);
        Treasure treasure = new Treasure(1, 2, 100);

        Maze maze = new Maze(new char[][]{
                {'#', '#', '#', '#', '#'},
                {'#', ' ', ' ', 'T', '#'},
                {'#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#'}
        });



        System.out.println("Welcome to the Maze of Death!");
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
            player.moveAndCheckMonster(dx, dy, monster);
        }
    }
}
