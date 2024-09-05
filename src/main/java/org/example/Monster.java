package org.example;

public class Monster implements Movable {
    private String name;
    private int x;
    private int y;
    private int health;
    private int strength;

    public Monster(String name, int x, int y, int health, int strength) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = health;
        this.strength = strength;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String  toString() {
        return String.format("Monster %s at (%d, %d) with health %d and strength %d", name, x, y, health, strength);
    }

    @Override
    public void move(int dx, int dy, Maze maze) {
        int newX = x + dx;
        int newY = y + dy;
        if (newY >= 0 && newY < maze.grid.length && newX >= 0 && newX < maze.grid[newY].length && maze.grid[newY][newX] != '#') {
            x = newX;
            y = newY;
        } else {
            System.out.println("Move blocked: " + name + " cannot move through walls.");
        }
    }
}