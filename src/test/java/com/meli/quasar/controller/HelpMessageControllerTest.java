
package com.meli.quasar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.quasar.model.request.MessageRequest;
import com.meli.quasar.model.request.SatelliteRequest;
import com.meli.quasar.model.response.MessageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelpMessageControllerTest {

    private static final String URL = "/topsecret";

    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public WebApplicationContext context;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void whenAllOk() throws Exception {
        MessageRequest request = new MessageRequest();
        SatelliteRequest satelliteRequestKenobi=SatelliteRequest.builder()
                .distance(100)
                .name("kenobi")
                .message(Arrays.asList("este", "", "", "mensaje", ""))
                .build();

        SatelliteRequest satelliteRequestSkywalker=SatelliteRequest.builder()
                .distance(115.5)
                .name("skywalker")
                .message(Arrays.asList("", "es", "", "", "secreto"))
                .build();

        SatelliteRequest satelliteRequestSato=SatelliteRequest.builder()
                .distance(142.7)
                .name("sato")
                .message(Arrays.asList("este", "", "un", "", ""))
                .build();
        request.setSatellites(Arrays.asList(satelliteRequestKenobi,satelliteRequestSkywalker,satelliteRequestSato));

        MvcResult reponse = mockMvc.perform(MockMvcRequestBuilders.post(URL )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("ISO-8859-1")
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        Assertions.assertNotNull(reponse.getResponse().getContentAsString());
        assertThat(reponse.getResponse().getStatus(), is(HttpStatus.OK.value()));
        assertThat(objectMapper.readValue(reponse.getResponse().getContentAsString(), MessageResponse.class).getMessage(), is("este es un mensaje secreto"));
    }


}