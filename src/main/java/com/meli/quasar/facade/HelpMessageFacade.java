package com.meli.quasar.facade;

import com.meli.quasar.model.request.MessageRequest;
import com.meli.quasar.model.response.MessageResponse;

public interface HelpMessageFacade {
    MessageResponse getHelpMessage(MessageRequest messageRequest);
}
