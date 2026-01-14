package com.runescape.logic.item;

/**
 * Matrix class would be item.java that matches this class ->
 */

public final class PossesedItem implements Item {
    private int id;
    private int amount;

    private transient ItemDefinition def = null;

    public PossesedItem(int id) {
        this(id, 1);
    }

    public PossesedItem(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    

    public ItemDefinition getDefinition() {
        if (def == null) {
            def = ItemDefinition.forId(id);
        }
        return def;
    }

    @Override
    public PossesedItem clone() {
        return new PossesedItem(id, amount);
    }
}
