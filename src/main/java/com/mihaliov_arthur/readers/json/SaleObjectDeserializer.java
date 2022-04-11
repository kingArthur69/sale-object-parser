package com.mihaliov_arthur.readers.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mihaliov_arthur.models.SaleObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SaleObjectDeserializer extends StdDeserializer<SaleObject> {

    public SaleObjectDeserializer() {
        this(null);
    }

    protected SaleObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SaleObject deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        SaleObject saleObject = new SaleObject();

        int squareMeters = node.get("sizeSqm").asInt();
        saleObject.setSquareMeters(squareMeters);

        String pricePerSquareMeter = new BigDecimal(node.get("startingPrice").longValue())
                .divide(BigDecimal.valueOf(squareMeters), 3, RoundingMode.HALF_UP)
                .toPlainString();
        saleObject.setPricePerSquareMeter(StringUtils.remove(pricePerSquareMeter, "."));

        JsonNode postalAddress = node.get("postalAddress");
        saleObject.setCity(postalAddress.get("city").textValue());
        saleObject.setStreet(postalAddress.get("street").textValue());

        if(StringUtils.equals(node.get("type").textValue(), "APT")) {
            saleObject.setFloor(postalAddress.get("floor").intValue());
        }

        return saleObject;
    }
}
