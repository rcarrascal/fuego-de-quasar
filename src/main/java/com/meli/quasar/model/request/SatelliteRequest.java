package com.meli.quasar.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SatelliteRequest {
    private String name;
    private double distance;
    private List<String> message;
}
