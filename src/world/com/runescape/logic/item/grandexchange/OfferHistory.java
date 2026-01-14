package com.runescape.logic.item.grandexchange;

public class OfferHistory{

    private int id, quantity, price;
    private boolean bought;

    public OfferHistory(int id, int quantity, int price, boolean bought) {
	this.id = id;
	this.quantity = quantity;
	this.price = price;
	this.bought = bought;
    }

    public int getId() {
	return id;
    }

    public int getQuantity() {
	return quantity;
    }

    public int getPrice() {
	return price;
    }

    public boolean isBought() {
	return bought;
    }
}
