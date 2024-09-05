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

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public String  toString() {
        return String.format("Monster %s at (%s, %s)", name, x, y);
    }
}