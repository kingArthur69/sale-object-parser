package com.adfenix.models;

import com.adfenix.readers.json.SaleObjectDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
