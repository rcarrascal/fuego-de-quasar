package com.meli.quasar.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageRequest {
    private List<SatelliteRequest> satellites;
}
