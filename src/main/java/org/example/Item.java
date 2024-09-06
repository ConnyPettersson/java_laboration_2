package org.example;

public record Item(int x, int y) implements Displayable {

    public void display() {
        System.out.println("Item at position (" + x() + ", " + y() + ")");
    }
}

