package org.example.Project3.util.validators;


import org.example.Project3.models.Sensor;
import org.example.Project3.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz); //указываем на каком классе используется валидатор
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorsService.getSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Датчик с таким именем уже существует");
            //благодаря Optional вызываем метод isPresent
        }

    }
}
