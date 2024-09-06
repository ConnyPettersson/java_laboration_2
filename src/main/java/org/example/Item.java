package org.example;

public record Item(Position position) implements Displayable {

    public void display() {
        System.out.println("Item at position (" + position.getX() + ", " + position.getY() + ")");
    }
}

