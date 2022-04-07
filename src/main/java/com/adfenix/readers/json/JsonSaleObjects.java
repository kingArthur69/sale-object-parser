package com.adfenix.readers.json;

import com.adfenix.models.SaleObject;
import lombok.Data;

import java.util.List;

@Data
public class JsonSaleObjects {

    private int numberOfSaleObjects;
    private List<SaleObject> saleObjects;
}
