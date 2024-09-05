package org.example;

public class Game {
    public static void main(String[] args) {

        Player player = new Player("Conan", 0, 0, 100, 10);
        Monster monster = new Monster("Zylox", 5, 5, 300, 50);

        char[][] simpleMaze = {
                {'#', '#', '#', '#', '#'},
                {'#', ' ', ' ', 'T', '#'},
                {'#', ' ', '#', ' ', '#'},
                {'#', 'P', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#'}
        };
        Maze maze = new Maze(simpleMaze);

        Treasure treasure = new Treasure(1, 2, 100);

        System.out.println("Welcome to the Maze of Death!");
        maze.displayMaze();
        System.out.println(player);
    }
}