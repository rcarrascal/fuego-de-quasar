package com.meli.quasar.facade;

import com.meli.quasar.model.request.SatelliteRequest;
import com.meli.quasar.model.response.MessageResponse;

public interface HelpSplitMessageFacade {

    MessageResponse getHelpMessage();

    void saveMessage(SatelliteRequest satelliteRequest, String name);
}
