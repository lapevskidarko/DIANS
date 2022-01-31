package com.example.chargingstation.model.converters;

import com.example.chargingstation.model.LatLong;

import javax.persistence.AttributeConverter;

public class LatLongConverter implements AttributeConverter<LatLong,String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(LatLong latlng) {
        if (latlng == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (latlng.getLat() != null && !latlng.getLat()
                .isEmpty()) {
            sb.append(latlng.getLat());
            sb.append(SEPARATOR);
        }

        if (latlng.getLng() != null
                && !latlng.getLng().isEmpty()) {
            sb.append(latlng.getLng());
        }

        return sb.toString();
    }

    @Override
    public LatLong convertToEntityAttribute(String dbLatLng) {
        if (dbLatLng == null || dbLatLng.isEmpty()) {
            return null;
        }

        String[] pieces = dbLatLng.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        LatLong latlng = new LatLong();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbLatLng.contains(SEPARATOR)) {
            latlng.setLat(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                latlng.setLng(pieces[1]);
            }
        } else {
            latlng.setLng(firstPiece);
        }

        return latlng;
    }
}
