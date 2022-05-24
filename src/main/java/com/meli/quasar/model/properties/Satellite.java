package com.meli.quasar.model.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Satellite {

    private String name;
    private double positionX;
    private double positionY;
}
