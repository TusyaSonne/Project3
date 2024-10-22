package org.example.Project3.repositories;

import org.example.Project3.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {

    public Optional<Sensor> findByName(String name);
}
