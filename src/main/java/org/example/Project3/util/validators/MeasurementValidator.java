package org.example.Project3.util.validators;

import org.example.Project3.models.Measurement;
import org.example.Project3.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurement.getSensor() == null) {
            return;
        }

        if (!sensorsService.getSensorByName(measurement.getSensor().getName()).isPresent()) {
            errors.rejectValue("sensor", "", "Нет сенсора с таким именем");
        }

    }
}
