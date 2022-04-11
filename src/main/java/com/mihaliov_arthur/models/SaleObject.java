package com.mihaliov_arthur.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mihaliov_arthur.readers.json.SaleObjectDeserializer;
import lombok.Data;

@Data
@JsonDeserialize(using = SaleObjectDeserializer.class)
public class SaleObject {

    private int squareMeters;
    private String pricePerSquareMeter;
    private String city;
    private String street;
    private Integer floor;
}
