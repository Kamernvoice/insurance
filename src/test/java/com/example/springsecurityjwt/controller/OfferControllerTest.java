package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.assembler.OfferAssembler;
import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.repository.OfferRepository;
import com.example.springsecurityjwt.service.OfferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class OfferControllerTest {

    private MockMvc mockMvc;
    ObjectMapper objectMapper;
    @Mock
    OfferService offerService;
    OfferAssembler offerAssembler;
    OfferRepository offerRepository;

    @BeforeEach
    void setUp() {
        offerService = mock(OfferService.class);
        offerAssembler = mock(OfferAssembler.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new OfferController(offerService, offerAssembler)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void save() throws Exception {
        offerService = mock(OfferService.class);
        offerAssembler = mock(OfferAssembler.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new OfferController(offerService, offerAssembler)).build();
        objectMapper = new ObjectMapper();
        OfferDto offerDto = new OfferDto();
        Mockito.when(offerService.save(Mockito.any(OfferDto.class))).thenReturn(offerDto);
//        mockMvc.perform(MockMvcRequestBuilders.post("/offers"))
//                .content(asJsonString(offerDto))
//                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }
}
