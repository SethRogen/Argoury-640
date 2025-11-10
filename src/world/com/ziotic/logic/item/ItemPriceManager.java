package com.ziotic.logic.item;

public class ItemPriceManager {
    private static final ItemPriceLoader loader = new ItemPriceLoader();

    public static void init() {
        loader.readPrices();
    }

    public static int getPrice(int itemId) {
        return loader.getPrice(itemId);
    }
}