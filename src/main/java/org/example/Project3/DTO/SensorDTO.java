package org.example.Project3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Название датчика не должно быть пустым")
    @Size(min = 3, max = 30, message = "Название должно находится в диапозоне от 3 до 30 символов")
    private String name;

    public SensorDTO() {}

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
