package org.example;

public class Maze {
    private char[][] grid;

    public Maze(char[][] grid) {
        this.grid = grid;
    }

    public void displayMaze(Player player) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == player.getY() && j == player.getX()) {
                    System.out.print('P' + " ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
