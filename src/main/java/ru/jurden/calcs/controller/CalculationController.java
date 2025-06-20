package ru.jurden.calcs.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.jurden.calcs.controller.dto.InterestRateRequest;
import ru.jurden.calcs.controller.dto.InterestRateResponse;
import ru.jurden.calcs.controller.dto.MortalityTableRequest;
import ru.jurden.calcs.controller.dto.MortalityTableResponse;
import ru.jurden.calcs.service.CalculationService;

@RestController
@AllArgsConstructor
public class CalculationController {

    private static final String BASE_PREFIX = "/api";
    private static final String INTEREST_RATE = BASE_PREFIX + "/interest_rate";
    private static final String MORTALITY_TABLE = BASE_PREFIX + "/mortality_table";

    private final CalculationService calculationService;

    @PostMapping(INTEREST_RATE)
    public ResponseEntity<InterestRateResponse> interestRate(@Validated @RequestBody InterestRateRequest request) {
        return calculationService.interestRate(request);
    }

    @PostMapping(MORTALITY_TABLE)
    public ResponseEntity<MortalityTableResponse> mortalityTable(@Validated @RequestBody MortalityTableRequest request) {
        return calculationService.mortalityTable(request);
    }
}
