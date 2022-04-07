package com.adfenix.consumers;

import java.util.Random;

public class MockSaleObjectConsumer implements SaleObjectConsumer {
    @Override
    public PriorityOrderAttribute getPriorityOrderAttribute() {
        PriorityOrderAttribute[] orderAttributes = PriorityOrderAttribute.values();
        PriorityOrderAttribute attribute = orderAttributes[new Random().nextInt(orderAttributes.length - 1)];

        System.out.println("Ordering by " + attribute);

        return attribute;
    }

    @Override
    public void startSaleObjectTransaction() {
        System.out.println("Starting Transaction");
    }

    @Override
    public void reportSaleObject(int squareMeters, String pricePerSquareMeter, String city, String street, Integer floor) throws TechnicalException {
        String message = "Reported object [sqrMeters: " + squareMeters + ", pricePerMeter: " + pricePerSquareMeter +
                ", city: " + city + ", street: " + street + ", floor: " + floor;
        System.out.println(message);
    }

    @Override
    public void commitSaleObjectTransaction() {
        System.out.println("Commit Sale Object Transaction");
    }
}
