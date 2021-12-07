package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.Offer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.springsecurityjwt.offer.OfferGetNewObject.getOffer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OfferServiceTest {

    @Autowired
    private OfferService offerService;



    @Test
    public void save() {
        OfferDto backOffer = offerService.save(getOffer());
        Offer foundOffer = offerService.findById(backOffer.getId());
        Assertions.assertNotNull(foundOffer);
        Assertions.assertEquals(foundOffer.getInsuranceType().getId(), backOffer.getInsuranceTypeId());
        Assertions.assertEquals(foundOffer.getInsurer().getId(), backOffer.getInsurerId());
        Assertions.assertEquals(foundOffer.getTerm(), backOffer.getTerm());
        Assertions.assertEquals(foundOffer.getCost(), backOffer.getCost());
        Assertions.assertEquals(foundOffer.getDescription(), backOffer.getDescription());
        offerService.delete(foundOffer.getId());
    }
}
