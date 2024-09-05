package org.example;

import java.util.ArrayList;

public class Player implements Movable {
    private String name;
    private int x;
    private int y;
    private int health;
    private int strength;
    private ArrayList<Item> items = new ArrayList<>();

    public Player(String name, int x, int y, int health, int strength) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = health;
        this.strength = strength;
        this.items = new ArrayList<>();


    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public String toString() {
        return String.format("Player %s at (%d, %d) with health %d and strength %d", name, x, y, health, strength);
    }
}
