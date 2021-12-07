package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.SpringSecurityJwtApplication;
import com.example.springsecurityjwt.assembler.OfferAssembler;
import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.repository.OfferRepository;
import com.example.springsecurityjwt.service.OfferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.net.URI;

import static com.example.springsecurityjwt.offer.OfferGetNewObject.getOffer;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes= SpringSecurityJwtApplication.class)
//@WebMvcTest(OfferController.class)
public class OfferControllerTest {

    private MockMvc mockMvc;
    @Mock
    OfferService offerService;
    @InjectMocks
    private OfferController offerController;
//    OfferAssembler offerAssembler;
//    OfferRepository offerRepository;
//    ObjectMapper objectMapper;



    @Before
    public void init() {
        //MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(offerController).build();
//        offerService = mock(OfferService.class);
//        offerAssembler = mock(OfferAssembler.class);
//        mockMvc = MockMvcBuilders.standaloneSetup(new OfferController(offerService, offerAssembler)).build();
//        objectMapper = new ObjectMapper();
    }

    @Test
    public void save() throws Exception {
        OfferDto offerDto = getOffer();
//        doNothing().when(offerService).save(offerDto);
        mockMvc.perform(MockMvcRequestBuilders.post(new URI("http://localhost:8082/offers"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(offerDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
//                .andExpect(MockMvcResultMatchers.content().json(asJsonString(offerDto)));
//                .andExpect(header().string("location", containsString("http://localhost:8082/")));

        verify(offerService, times(1)).save(offerDto);
        verifyNoMoreInteractions(offerService);

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
