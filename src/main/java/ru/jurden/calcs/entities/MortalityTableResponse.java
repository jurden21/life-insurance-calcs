package ru.jurden.calcs.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MortalityTableResponse {
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("lx")
    private Integer lx;
    @JsonProperty("dx")
    private Integer dx;
}
