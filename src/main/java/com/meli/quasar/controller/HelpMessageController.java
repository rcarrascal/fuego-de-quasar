package com.meli.quasar.controller;

import com.meli.quasar.exception.HelpMessageException;
import com.meli.quasar.facade.HelpMessageFacade;
import com.meli.quasar.model.request.MessageRequest;
import com.meli.quasar.model.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("topsecret")
public class HelpMessageController {

    private final HelpMessageFacade helpMessageFacade;

    public HelpMessageController(HelpMessageFacade helpMessageFacade) {
        this.helpMessageFacade = helpMessageFacade;
    }

    @Operation(summary = "Recibir informacion de los satelites")
    @PostMapping
    public MessageResponse topsecret(@RequestBody MessageRequest messageRequest) throws HelpMessageException {
        return helpMessageFacade.getHelpMessage(messageRequest);
    }
}
