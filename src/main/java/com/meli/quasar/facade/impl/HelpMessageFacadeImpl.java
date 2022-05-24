package com.meli.quasar.facade.impl;

import com.meli.quasar.facade.HelpMessageFacade;
import com.meli.quasar.model.request.MessageRequest;
import com.meli.quasar.model.response.MessageResponse;
import com.meli.quasar.model.response.PositionResponse;
import com.meli.quasar.service.LocationService;
import com.meli.quasar.service.MessageService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HelpMessageFacadeImpl implements HelpMessageFacade {
    private final LocationService locationService;
    private final MessageService messageService;

    public HelpMessageFacadeImpl(LocationService locationService, MessageService messageService) {
        this.locationService = locationService;
        this.messageService = messageService;
    }

    @Override
    public MessageResponse getHelpMessage(MessageRequest messageRequest) {

        List<List<String>> messageListRequest = messageRequest.getSatellites().stream()
                .map(satellite -> satellite.getMessage())
                .collect(Collectors.toList());
        String message = messageService.getMessage(messageListRequest);

        double[] distances = messageRequest.getSatellites()
                .stream()
                .mapToDouble(satellite -> satellite.getDistance())
                .toArray();
        PositionResponse positionResponse = locationService.getLocation(distances);

        return new MessageResponse(positionResponse, message);


    }
}
