package ru.jurden.calcs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.jurden.calcs.controller.dto.InterestRateRequest;
import ru.jurden.calcs.controller.dto.InterestRateResponse;
import ru.jurden.calcs.controller.dto.MortalityTableRequest;
import ru.jurden.calcs.controller.dto.MortalityTableResponse;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.model.InterestRate;
import ru.jurden.calcs.model.MortalityTable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CalculationServiceImpl implements CalculationService {

    private final Map<MortalityTableType, MortalityTable> mortalityTables;

    public CalculationServiceImpl(List<MortalityTable> mortalityTables) {
        this.mortalityTables = mortalityTables.stream()
                .collect(Collectors.toMap(MortalityTable::getTableType,
                        mortalityTable -> mortalityTable));
    }

    @Override
    public ResponseEntity<InterestRateResponse> interestRate(InterestRateRequest request) {
        InterestRate interestRate = new InterestRate(request.getPercent());
        return ResponseEntity.ok(new InterestRateResponse()
                .setInterestRate(interestRate));
    }

    @Override
    public ResponseEntity<MortalityTableResponse> mortalityTable(MortalityTableRequest request) {
        MortalityTableType mortalityTableType = MortalityTableType.getByName(request.getTableType());
        MortalityTable mortalityTable = mortalityTables.get(mortalityTableType);
        return ResponseEntity.ok(new MortalityTableResponse()
                .setAge(request.getAge())
                .setLx(mortalityTable.getLx(request.getAge()))
                .setDx(mortalityTable.getDx(request.getAge())));
    }
}
