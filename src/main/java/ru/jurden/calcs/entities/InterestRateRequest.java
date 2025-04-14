package ru.jurden.calcs.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InterestRateRequest {
    @JsonProperty("percent")
    @NotNull(message = "Поле 'percent' должно быть указано")
    @DecimalMin(value = "0.01", message = "Поле 'percent' не может быть меньше {value}")
    @DecimalMax(value = "100.00", message = "Поле 'percent' не может быть больше {value}")
    private Double percent;
}
