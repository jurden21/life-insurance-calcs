package ru.jurden.calcs.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.jurden.calcs.model.InterestRate;

@Data
@Accessors(chain = true)
public class InterestRateResponse {
    @JsonProperty("interest_rate")
    private InterestRate interestRate;
}
