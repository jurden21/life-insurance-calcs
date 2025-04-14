package ru.jurden.calcs.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InterestRateRequest {
    @JsonProperty("percent")
    private Double percent;
}
