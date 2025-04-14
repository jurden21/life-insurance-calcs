package ru.jurden.calcs.services;

import org.springframework.http.ResponseEntity;
import ru.jurden.calcs.entities.InterestRateRequest;
import ru.jurden.calcs.entities.InterestRateResponse;

public interface CalculationService {
    ResponseEntity<InterestRateResponse> interestRate(InterestRateRequest request);
}
