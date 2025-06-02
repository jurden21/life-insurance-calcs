package ru.jurden.calcs.service;

import org.springframework.http.ResponseEntity;
import ru.jurden.calcs.controller.dto.InterestRateRequest;
import ru.jurden.calcs.controller.dto.InterestRateResponse;
import ru.jurden.calcs.controller.dto.MortalityTableRequest;
import ru.jurden.calcs.controller.dto.MortalityTableResponse;

public interface CalculationService {
    ResponseEntity<InterestRateResponse> interestRate(InterestRateRequest request);
    ResponseEntity<MortalityTableResponse> mortalityTable(MortalityTableRequest request);
}
