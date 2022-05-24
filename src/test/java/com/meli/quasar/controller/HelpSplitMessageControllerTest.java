
package com.meli.quasar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.quasar.facade.HelpSplitMessageFacade;
import com.meli.quasar.model.request.MessageRequest;
import com.meli.quasar.model.request.SatelliteRequest;
import com.meli.quasar.model.response.MessageResponse;
import com.meli.quasar.model.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelpSplitMessageControllerTest {

    private static final String URL = "/topsecret_split";

    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public WebApplicationContext context;

    @Autowired
    private HelpSplitMessageFacade helpSplitMessageFacade;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void whenAllOk() throws Exception {
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

        helpSplitMessageFacade.saveMessage(satelliteRequestSato,"sato");
        helpSplitMessageFacade.saveMessage(satelliteRequestSkywalker,"skywalker");
        helpSplitMessageFacade.saveMessage(satelliteRequestKenobi,"kenobi");
        MvcResult reponse = mockMvc.perform(MockMvcRequestBuilders.get(URL )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("ISO-8859-1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        Assertions.assertNotNull(reponse.getResponse().getContentAsString());
        assertThat(reponse.getResponse().getStatus(), is(HttpStatus.OK.value()));
        assertThat(objectMapper.readValue(reponse.getResponse().getContentAsString(), MessageResponse.class).getMessage(), is("este es un mensaje secreto"));
    }

    @Test
    void whenOnlySendTwoMessage() throws Exception {
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

        helpSplitMessageFacade.saveMessage(satelliteRequestSkywalker,"skywalker");
        helpSplitMessageFacade.saveMessage(satelliteRequestKenobi,"kenobi");
        MvcResult reponse = mockMvc.perform(MockMvcRequestBuilders.get(URL )
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("ISO-8859-1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        Assertions.assertNotNull(reponse.getResponse().getContentAsString());
        assertThat(reponse.getResponse().getStatus(), is(HttpStatus.NOT_FOUND.value()));
        assertThat(objectMapper.readValue(reponse.getResponse().getContentAsString(), MessageResponse.class).getMessage(), is("Mensaje no puede ser determinado"));
    }



}