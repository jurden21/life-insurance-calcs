package ru.jurden.calcs.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MortalityTableRequest {
    @JsonProperty("age")
    @NotNull(message = "Поле 'age' должно быть указано")
    @Min(value = 1, message = "Поле 'age' не может быть меньше {value}")
    @Max(value = 100, message = "Поле 'age' не может быть больше {value}")
    private int age;

    @JsonProperty("table_type")
    @NotNull(message = "Поле 'table_type' должно быть указано")
    @Pattern(regexp = "^(male|female|mixed)$", message = "Поле 'table_type' может быть равно: male / female / mixed")
    private String tableType;
}
