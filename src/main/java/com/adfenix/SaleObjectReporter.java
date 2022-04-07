package com.adfenix;

import com.adfenix.consumers.SaleObjectConsumer;
import com.adfenix.models.SaleObject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SaleObjectReporter {

    private SaleObjectConsumer saleObjectConsumer;

    public SaleObjectReporter(SaleObjectConsumer saleObjectConsumer) {
        this.saleObjectConsumer = saleObjectConsumer;
    }

    public void reportObjects(List<SaleObject> saleObjects) {
        saleObjectConsumer.startSaleObjectTransaction();

        SaleObjectConsumer.PriorityOrderAttribute orderAttribute = saleObjectConsumer.getPriorityOrderAttribute();
        saleObjects = sortObjectsBy(orderAttribute, saleObjects);

        for (SaleObject object : saleObjects) {
            saleObjectConsumer.reportSaleObject(object.getSquareMeters(),
                    object.getPricePerSquareMeter(), object.getCity(), object.getStreet(), object.getFloor());
        }

        saleObjectConsumer.commitSaleObjectTransaction();
    }

    private List<SaleObject> sortObjectsBy(SaleObjectConsumer.PriorityOrderAttribute orderAttribute, List<SaleObject> saleObjects) {
        Comparator<SaleObject> comparator = getComparatorBasedOnOrderAttribute(orderAttribute);

        return saleObjects.stream().sorted(comparator).collect(Collectors.toList());
    }

    private static Comparator<SaleObject> getComparatorBasedOnOrderAttribute(SaleObjectConsumer.PriorityOrderAttribute orderAttribute) {
        switch (orderAttribute) {
            case City:
                return Comparator.comparing(SaleObject::getCity);
            case SquareMeters:
                return Comparator.comparing(SaleObject::getSquareMeters);
            case PricePerSquareMeter:
                return Comparator.comparing(SaleObject::getPricePerSquareMeter);
            default:
                throw new IllegalArgumentException("Order Attribute is not supported: " + orderAttribute);
        }
    }
}
