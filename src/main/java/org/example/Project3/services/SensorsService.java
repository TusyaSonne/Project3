package org.example.Project3.services;

import org.example.Project3.models.Sensor;
import org.example.Project3.repositories.SensorsRepository;
import org.example.Project3.util.sensor_ex.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }


    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public Optional<Sensor> findOne(int id) {
        return sensorsRepository.findById(id);
//        Optional<Sensor> foundedSensor = sensorsRepository.findById(id);
//        return foundedSensor.orElseThrow(SensorNotFoundException::new);
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorsRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

}
