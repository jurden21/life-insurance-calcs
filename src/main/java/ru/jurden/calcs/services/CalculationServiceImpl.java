package ru.jurden.calcs.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.jurden.calcs.entities.InterestRateRequest;
import ru.jurden.calcs.entities.InterestRateResponse;
import ru.jurden.calcs.entities.MortalityTableRequest;
import ru.jurden.calcs.entities.MortalityTableResponse;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.models.InterestRate;
import ru.jurden.calcs.models.MortalityTable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    List<MortalityTable> mortalityTables;

    @Override
    public ResponseEntity<InterestRateResponse> interestRate(InterestRateRequest request) {
        InterestRate interestRate = new InterestRate(request.getPercent());
        return ResponseEntity.ok(new InterestRateResponse()
                .setInterestRate(interestRate));
    }

    @Override
    public ResponseEntity<MortalityTableResponse> mortalityTable(MortalityTableRequest request) {
        Map<MortalityTableType, MortalityTable> mortalityTablesMap = mortalityTables.stream()
                .collect(Collectors.toMap(MortalityTable::getTableType,mortalityTable -> mortalityTable));
        MortalityTableType mortalityTableType = MortalityTableType.getByName(request.getTableType());
        MortalityTable mortalityTable = mortalityTablesMap.get(mortalityTableType);
        return ResponseEntity.ok(new MortalityTableResponse()
                .setAge(request.getAge())
                .setLx(mortalityTable.getLx(request.getAge()))
                .setDx(mortalityTable.getDx(request.getAge())));
    }
}
