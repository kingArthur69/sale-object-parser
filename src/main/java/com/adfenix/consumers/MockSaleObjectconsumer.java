package com.adfenix.consumers;

public class MockSaleObjectconsumer implements SaleObjectConsumer {
    @Override
    public PriorityOrderAttribute getPriorityOrderAttribute() {
        return null;
    }

    @Override
    public void startSaleObjectTransaction() {

    }

    @Override
    public void reportSaleObject(int squareMeters, String pricePerSquareMeter, String city, String street, Integer floor) throws TechnicalException {

    }

    @Override
    public void commitSaleObjectTransaction() {

    }
}
