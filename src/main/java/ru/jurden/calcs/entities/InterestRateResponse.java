package ru.jurden.calcs.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.jurden.calcs.models.InterestRate;

@Data
@Accessors(chain = true)
public class InterestRateResponse {
    @JsonProperty("interest_rate")
    private InterestRate interestRate;
}
