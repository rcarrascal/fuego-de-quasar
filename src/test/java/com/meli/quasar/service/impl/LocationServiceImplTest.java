package com.meli.quasar.service.impl;

import com.meli.quasar.exception.HelpMessageException;
import com.meli.quasar.model.response.PositionResponse;
import com.meli.quasar.service.LocationService;
import com.meli.quasar.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LocationServiceImplTest {

    @Autowired
    private LocationService locationService;

    @Test
    void getMessage() {
        double[] distances = new double[]{100.0, 115.5, 142.7};
        PositionResponse expected= new PositionResponse(-58.315252587138595, -69.55141837312165);
        assertEquals(expected, locationService.getLocation(distances));
    }


    @Test
    void WithPositionNotDeterminate() {
        Exception exception = assertThrows(HelpMessageException.class, () -> {
            double[] distances = new double[]{100.0, 115.5};
            PositionResponse expected= new PositionResponse(-58.315252587138595, -69.55141837312165);
            assertEquals(expected, locationService.getLocation(distances));
        });

        String expectedMessage = "exception.message.position";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
}