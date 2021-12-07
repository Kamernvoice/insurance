package com.example.springsecurityjwt.offer;

import com.example.springsecurityjwt.dto.OfferDto;

import java.math.BigDecimal;

public class OfferGetNewObject {
    public static OfferDto getOffer() {
        return new OfferDto(null, 13, null, 13, null, 13, new BigDecimal("13.00"), "13", null);
    }
}
