package org.example.Project3.DTO;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.Project3.models.Sensor;
import org.hibernate.validator.constraints.Range;

public class MeasurementDTO {


    @NotNull(message = "Value should not be empty")
    @Range(min = -100, max = 100, message = "Name should be between 2 and 30 characters")
    private float value;

    @NotNull(message = "variable raining should not be empty")
    private boolean raining;

    @NotNull
    @Valid
    private SensorDTO sensor;

    public MeasurementDTO(float value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO() {}


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }


    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
