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

    public void displayMaze(Player player, List<Monster> monsters, List<Treasure> treasures, List<Upgrade> upgrades) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                AtomicBoolean printed = new AtomicBoolean(false);

                if (i == player.getY() && j == player.getX()) {
                    System.out.print('C' + " ");
                    printed.set(true);
                }

                final int row = i;
                final int col = j;

                if (!printed.get()) {
                    monsters.stream()
                            .filter(monster -> monster.getY() == row && monster.getX() == col)
                            .findFirst()
                            .ifPresent(monster -> {
                                System.out.print(monster.getSymbol() + " ");
                                printed.set(true);
                            });
                }

                if (!printed.get()) {
                    treasures.stream()
                            .filter(treasure -> treasure.item().position().getY() == row && treasure.item().position().getX() == col)
                            .findFirst()
                            .ifPresent(treasure -> {
                                System.out.print('T' + " ");
                                printed.set(true);
                            });
                }

                if (!printed.get()) {
                    upgrades.stream()
                            .filter(upgrade -> upgrade.item().position().getY() == row && upgrade.item().position().getX() == col)
                            .findFirst()
                            .ifPresent(upgrade -> {
                                System.out.print('U' + " ");
                                printed.set(true);
                            });
                }

                if (!printed.get()) {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
