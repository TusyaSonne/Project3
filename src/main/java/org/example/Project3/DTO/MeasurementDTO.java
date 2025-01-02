package org.example.Project3.DTO;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.Project3.models.Sensor;
import org.hibernate.validator.constraints.Range;

public class MeasurementDTO {


    @NotNull(message = "Значение value не может быть пустым")
    @Range(min = -100, max = 100, message = "value должна находиться в диапазоне от -100 до 100")
    private Float value;

    @NotNull(message = "Значение raining не может быть пустым")
    private Boolean raining;

    @NotNull
    @Valid
    private SensorDTO sensor;

    public MeasurementDTO(Float value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO() {}


    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }


    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
