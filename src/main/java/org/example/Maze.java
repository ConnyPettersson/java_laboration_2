package org.example;

public class Maze {
    private char[][] grid;

    public Maze(char[][] grid) {
        this.grid = grid;
    }

    public void displayMaze() {
        for(char[] row :  grid) {
            for(char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
