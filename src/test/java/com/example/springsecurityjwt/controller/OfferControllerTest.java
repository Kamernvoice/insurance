package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.service.OfferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;

import static com.example.springsecurityjwt.offer.OfferGetNewObject.getOffer;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OfferControllerTest {

    private MockMvc mockMvc;
    @Mock
    OfferService offerService;
    @InjectMocks
    private OfferController offerController;


    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(offerController).build();
    }

    @Test
    public void save() throws Exception {
        OfferDto offerDto = getOffer();
        mockMvc.perform(MockMvcRequestBuilders.post(new URI("http://localhost:8082/offers"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(offerDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

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
