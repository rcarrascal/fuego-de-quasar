package com.meli.quasar.facade.impl;

import com.meli.quasar.exception.HelpMessageException;
import com.meli.quasar.facade.HelpMessageFacade;
import com.meli.quasar.facade.HelpSplitMessageFacade;
import com.meli.quasar.model.request.MessageRequest;
import com.meli.quasar.model.request.SatelliteRequest;
import com.meli.quasar.model.response.MessageResponse;
import com.meli.quasar.util.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HelpSplitMessageFacadeImpl implements HelpSplitMessageFacade {

    private final List<SatelliteRequest> messageRequestList;
    private final HelpMessageFacade helpMessageFacade;

    public HelpSplitMessageFacadeImpl(HelpMessageFacade helpMessageFacade) {
        this.messageRequestList = new ArrayList<>();
        this.helpMessageFacade = helpMessageFacade;
    }

    @Override
    public MessageResponse getHelpMessage() {
        if (messageRequestList.size() < 3) {
            throw new HelpMessageException(Constants.EXCEPTION_MESSAGESERROR);
        }
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setSatellites(messageRequestList);
        MessageResponse message = helpMessageFacade.getHelpMessage(messageRequest);
        messageRequestList.clear();
        return message;

    }

    @Override
    public void saveMessage(SatelliteRequest satelliteRequest, String name) {
        satelliteRequest.setName(name);
        messageRequestList.add(satelliteRequest);
    }
}
