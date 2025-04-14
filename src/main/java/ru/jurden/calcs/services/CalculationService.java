package ru.jurden.calcs.services;

import org.springframework.http.ResponseEntity;
import ru.jurden.calcs.entities.InterestRateRequest;
import ru.jurden.calcs.entities.InterestRateResponse;
import ru.jurden.calcs.entities.MortalityTableRequest;
import ru.jurden.calcs.entities.MortalityTableResponse;

public interface CalculationService {
    ResponseEntity<InterestRateResponse> interestRate(InterestRateRequest request);
    ResponseEntity<MortalityTableResponse> mortalityTable(MortalityTableRequest request);
}
