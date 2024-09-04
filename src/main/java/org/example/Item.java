package org.example;

public class Item {
    protected int x;
    protected int y;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Treasure extends Item {
    private int value;

    public Treasure(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }
}

class Upgrade extends Item {
    private String type;

    public Upgrade(int x, int y, String type) {
        super(x, y);
        this.type = type;
    }
}
