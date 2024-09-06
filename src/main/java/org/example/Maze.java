package org.example;

public class Maze {
    char[][] grid;

    public Maze(char[][] grid) {
        this.grid = grid;
    }

    public boolean isValid(int x, int y) {
        return y >= 0 && y < grid.length &&
               x >= 0 && x < grid[y].length &&
               grid[y][x] != '#';
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
