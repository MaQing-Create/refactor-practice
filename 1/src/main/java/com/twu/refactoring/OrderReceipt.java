package com.twu.refactoring;

import static java.lang.String.format;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private Order order;
    private final double saleTaxRate = 0.10;

    public OrderReceipt(Order o) {
        this.order = o;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();

		// print headers
		output.append("======Printing Orders======\n");

		// print date, bill no, customer name
//        output.append("Date - " + order.getDate();
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
//        output.append(order.getCustomerLoyaltyNumber());

		// prints lineItems
		double totSalesTx = 0d;
		double totAmount = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			output.append(format("%s\t%S\t%s\t%s\n",lineItem.getDescription(), lineItem.getPrice(),
					lineItem.getQuantity(), lineItem.totalAmount()));

			// calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * saleTaxRate;
            totSalesTx += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totAmount += lineItem.totalAmount() + salesTax;
		}

		// prints the state tax
		output.append("Sales Tax").append('\t').append(totSalesTx);

        // print total amount
		output.append("Total Amount").append('\t').append(totAmount);
		return output.toString();
	}
}