package org.example;

public class Treasure implements Displayable {
    private Item item;
    private int value;

    public Treasure(Item item, int value) {
        this.item = item;
        this.value = value;
    }

    // Expose the position methods of the item through the Treasure class
    public int getItemX() {
        return item.getX();
    }

    public int getItemY() {
        return item.getY();
    }

    @Override
    public void display() {
        System.out.println("Treasure value: " + value);
        item.display();
    }
}
