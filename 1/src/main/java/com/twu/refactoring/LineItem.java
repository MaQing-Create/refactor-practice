package com.twu.refactoring;

public class LineItem {
	private String desciption;
	private double price;
	private int quantity;

	public LineItem(String desc, double p, int qty) {
		super();
		this.desciption = desc;
		this.price = p;
		this.quantity = qty;
	}

	public String getDescription() {
		return desciption;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

    double totalAmount() {
        return price * quantity;
    }
}