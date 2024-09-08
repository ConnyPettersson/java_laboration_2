package org.example;

public class Monster implements Movable {
    private final String name;
    private Position position;
    private int health;

    public Monster(String name, Position position, int health) {
        this.name = name;
        this.position = position;
        this.health = health;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public char getSymbol() {
        if(name.equals("Zylox")) {
            return 'Z';
        }else if (name.equals("Xiltor")) {
            return 'x';
        }
        return 'M';
    }

    public String  toString() {
        return String.format("Monster %s at (%d, %d) with health %d", name, position.getX(), position.getY(), health);
    }

    @Override
    public void move(int dx, int dy, Maze maze) {
        int newX = position.getX() + dx;
        int newY = position.getY() + dy;
        Position newPosition = new Position(newX, newY);
        if (maze.isValid(newPosition)) {
            position = new Position(newX, newY);
        } else {
            System.out.println("Move blocked: " + name + " cannot move through walls.");
        }
    }
}