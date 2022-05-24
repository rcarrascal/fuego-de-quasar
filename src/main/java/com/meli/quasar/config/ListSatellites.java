package com.meli.quasar.config;

import com.meli.quasar.model.properties.Satellite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "satellites")
public class ListSatellites {
    List<Satellite> list;
}
