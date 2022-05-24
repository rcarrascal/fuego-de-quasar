package com.meli.quasar;

import com.meli.quasar.config.ListSatellites;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ListSatellites.class)
public class QuasarFireApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuasarFireApplication.class, args);
	}

}
