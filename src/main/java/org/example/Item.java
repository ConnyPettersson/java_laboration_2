package org.example;

public record Item(Position position) {
    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public void display() {
        System.out.println("Item at position (" + getX() + ", " + getY() + ")");
    }
}
