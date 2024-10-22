package org.example.Project3.controllers;

import jakarta.validation.Valid;
import org.example.Project3.DTO.MeasurementDTO;
import org.example.Project3.DTO.SensorDTO;
import org.example.Project3.models.Measurement;
import org.example.Project3.models.Sensor;
import org.example.Project3.services.MeasurementsService;
import org.example.Project3.services.SensorsService;
import org.example.Project3.util.measurement_ex.MeasurementErrorResponse;
import org.example.Project3.util.measurement_ex.MeasurementNotCreatedException;
import org.example.Project3.util.sensor_ex.SensorErrorResponse;
import org.example.Project3.util.sensor_ex.SensorNotCreatedException;
import org.example.Project3.util.validators.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, SensorsService sensorsService, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
        this.measurementValidator = measurementValidator;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        Measurement measurementToAdd = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurementToAdd, bindingResult);


        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new MeasurementNotCreatedException(errorMessage.toString());

        }

//        String sensorName = measurementDTO.getSensor().getName();
        //Sensor sensor = sensorsService.getSensorByName(measurementToAdd.getSensor().getName());

        measurementsService.save(measurementToAdd);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        return measurement;
    }

    @GetMapping()
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO).toList(); //Jackson конвертирует эти объекты в JSON
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        return measurementDTO;
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDays() {
        return measurementsService.CountRainyDays();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse errorResponse = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
