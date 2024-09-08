package org.example;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Maze {
    char[][] grid;

    public Maze(char[][] grid) {
        this.grid = grid;
    }

    public boolean isValid(Position position) {
        return position.getY() >= 0 && position.getY() < grid.length &&
                position.getX() >= 0 && position.getX() < grid[position.getY()].length &&
               grid[position.getY()][position.getX()] != '#';
    }

    public void displayMaze(Player player) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == player.getY() && j == player.getX()) {
                    System.out.print('C' + " ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}

