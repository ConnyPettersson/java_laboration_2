package org.example;

public record Treasure(Item item, int value) implements Displayable {

    @Override
    public void display() {
        item.display();
        System.out.println("Treasure value: " + value);
    }
}
