package com.meli.quasar.service;

import com.meli.quasar.model.response.PositionResponse;

public interface LocationService {
    PositionResponse getLocation(double [] distances);
}
