package org.example.Project3;

import org.example.Project3.DTO.MeasurementDTO;
import org.example.Project3.models.Measurement;
import org.example.Project3.models.Sensor;
import org.example.Project3.repositories.MeasurementsRepository;
import org.example.Project3.services.MeasurementsService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootApplication
public class Project3Application {

	public static void main(String[] args) {
		SpringApplication.run(Project3Application.class, args);

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
