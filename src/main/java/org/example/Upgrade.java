package org.example;

public record Upgrade(Item item, String type) implements Displayable {

    @Override
    public void display() {
        item.display();
        System.out.println("Upgrade type: " + type);

    }
}
