package com.mihaliov_arthur.readers.json;

import com.mihaliov_arthur.models.SaleObject;
import lombok.Data;

import java.util.List;

@Data
public class JsonSaleObjects {

    private int numberOfSaleObjects;
    private List<SaleObject> saleObjects;
}
