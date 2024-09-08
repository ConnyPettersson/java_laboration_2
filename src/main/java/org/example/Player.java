package org.example;

import java.util.ArrayList;

public class Player implements Movable {
    private String name;
    private Position position;
    private int health;
    private int strength;
    private ArrayList<Item> items = new ArrayList<>();
    private String weapon;

    public Player(String name, Position position, int health, int strength) {
        this.name = name;
        this.position = position;
        this.health = health;
        this.strength = strength;
        this.items = new ArrayList<>();
        this.weapon = "Broad Sword";

    }

    public void moveAndCheckMonster(int dx, int dy, Monster monster, Maze maze) {
        move(dx, dy, maze);
        if(position.getX() == monster.getX() && position.getY() == monster.getY()) {
            System.out.println("You have encountered the Monster " + monster.getName() + "!");
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }


    @Override
    public void move(int dx, int dy, Maze maze) {
        int newX = position.getX() + dx;
        int newY = position.getY() + dy;
        Position newPosition = new Position(newX, newY);
        if (maze.isValid(newPosition)) {
            position = newPosition;
        } else {
            System.out.println("Move blocked by a wall.");
        }
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getHealth() {
        return health;
    }

    public int setHealth(int health) {
        return this.health = health;
    }

    public void upgradeWeapon(String newWeapon) {
        this.weapon = newWeapon;
        System.out.println("You have equipped the " + newWeapon + "!");
    }

    public int getDamage() {
        if (weapon.equals("Long Sword")) {
            return 100; // Long Sword gör 100 i skada
        }
        return 50; // Broad Sword gör 50 i skada
    }

    public String getWeapon() {
        return weapon;
    }

    @Override
    public String toString() {
        return String.format("Player %s at (%d, %d) with health %d and strength %d", name, position.getX(), position.getY(), health, strength);
    }
}
