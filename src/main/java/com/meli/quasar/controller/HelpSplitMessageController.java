package com.meli.quasar.controller;

import com.meli.quasar.exception.HelpMessageException;
import com.meli.quasar.facade.HelpSplitMessageFacade;
import com.meli.quasar.model.request.MessageRequest;
import com.meli.quasar.model.request.SatelliteRequest;
import com.meli.quasar.model.response.MessageResponse;
import com.meli.quasar.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("topsecret_split")
public class HelpSplitMessageController {

    private final HelpSplitMessageFacade helpMessageFacade;

    public HelpSplitMessageController(HelpSplitMessageFacade helpMessageFacade) {
        this.helpMessageFacade = helpMessageFacade;
    }

    @Operation(summary = "Recibir informacion de los satelites")
    @PostMapping("/{satelliteName}")
    public Response topsecretSplit(@Parameter(description = "Nombre del satelite") @PathVariable("satelliteName")  String satelliteName,
                                   @RequestBody SatelliteRequest satelliteRequest) throws HelpMessageException {
        helpMessageFacade.saveMessage(satelliteRequest,satelliteName);
        return new Response(HttpStatus.CREATED.name(), "Satelite agregado con exito " +satelliteName );
    }
    @Operation(summary = "Recibir informacion de los satelites")
    @GetMapping
    public MessageResponse getMessage() throws HelpMessageException {
        return helpMessageFacade.getHelpMessage();
    }
}
