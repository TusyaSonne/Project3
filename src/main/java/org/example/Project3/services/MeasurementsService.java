package org.example.Project3.services;

import org.example.Project3.models.Measurement;
import org.example.Project3.models.Sensor;
import org.example.Project3.repositories.MeasurementsRepository;
import org.example.Project3.util.measurement_ex.MeasurementNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public Measurement findOne(int id) {
        Optional<Measurement> foundMeasurement = measurementsRepository.findById(id);
        return foundMeasurement.orElseThrow(MeasurementNotFoundException::new);
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }


    public void enrichMeasurement(Measurement measurement) {
        measurement.setTimeOfMeasurement(LocalDateTime.now());
        //Он сам не добавится, необходимо явно указать сенсор для измерения из persistance context'a Hibernate
        measurement.setSensor(sensorsService.getSensorByName(measurement.getSensor().getName()).get());
    }

    public int CountRainyDays() {
        List<Measurement> measurements = findAll();
        int count = 0;
        for (Measurement measurement : measurements) {
            if (measurement.isRaining()) {
                count++;
            }
        }
        return count;
    }
}
