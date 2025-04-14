package ru.jurden.calcs.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.jurden.calcs.entities.InterestRateRequest;
import ru.jurden.calcs.entities.InterestRateResponse;
import ru.jurden.calcs.models.InterestRate;

@Slf4j
@Service
@AllArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    @Override
    public ResponseEntity<InterestRateResponse> interestRate(InterestRateRequest request) {
        InterestRate interestRate = new InterestRate(request.getPercent());
        return ResponseEntity.ok(new InterestRateResponse()
                .setInterestRate(interestRate));
    }

}
