package org.example;

public class Item {
    protected int x;
    protected int y;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void display() {
        System.out.println("Item at position (" + x + ", " + y + ")");
    }
}

class Treasure extends Item {
    private int value;

    public Treasure(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Treasure value: " + value);
    }
}

class Upgrade extends Item {
    private String type;

    public Upgrade(int x, int y, String type) {
        super(x, y);
        this.type = type;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Upgrade type: " + type);
    }
}
