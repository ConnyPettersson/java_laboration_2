package org.example;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public void displayMaze(Player player, List<Monster> monsters, List<Treasure> treasures, List<Upgrade> upgrades) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                boolean printed = false;

                if (i == player.getY() && j == player.getX()) {
                    System.out.print('C' + " ");
                    printed = true;
                }

                final int row = i;
                final int col = j;
                int finalI = i;
                int finalJ = j;

                if (!printed && monsters.stream().anyMatch(monster -> monster.getY() == finalI && monster.getX() == finalJ)) {
                    System.out.print('M' + " ");
                    printed = true;
                }

                if (!printed && treasures.stream().anyMatch(treasure -> treasure.item().position().getY() == row && treasure.item().position().getX() == col)) {
                    System.out.print('T' + " ");
                    printed = true;
                }

                if (!printed && upgrades.stream().anyMatch(upgrade -> upgrade.item().position().getY() == row && upgrade.item().position().getX() == col)) {
                    System.out.print('U' + " ");
                    printed = true;
                }

                if (!printed) {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


}
