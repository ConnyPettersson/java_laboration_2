package org.example;

import java.util.ArrayList;

public class Player implements Movable {
    private String name;
    private Position position;
    private int health;
    private int strength;
    private ArrayList<Item> items = new ArrayList<>();

    public Player(String name, Position position, int health, int strength) {
        this.name = name;
        this.position = position;
        this.health = health;
        this.strength = strength;
        this.items = new ArrayList<>();


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


    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    @Override
    public String toString() {
        return String.format("Player %s at (%d, %d) with health %d and strength %d", name, position.getX(), position.getY(), health, strength);
    }
}
