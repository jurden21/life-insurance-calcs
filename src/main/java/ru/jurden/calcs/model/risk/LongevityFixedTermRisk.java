package ru.jurden.calcs.model.risk;

import ru.jurden.calcs.enums.ContractType;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.enums.RiskType;
import ru.jurden.calcs.model.CalculationContext;
import ru.jurden.calcs.model.InterestRate;
import ru.jurden.calcs.model.MortalityTable;
import ru.jurden.calcs.model.Risk;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LongevityFixedTermRisk implements Risk {

    private final InterestRate interestRate;
    private final Map<MortalityTableType, MortalityTable> mortalityTables;

    public LongevityFixedTermRisk(InterestRate interestRate, List<MortalityTable> mortalityTables) {
        this.interestRate = interestRate;
        this.mortalityTables = mortalityTables.stream()
                .collect(Collectors.toMap(MortalityTable::getTableType,
                        mortalityTable -> mortalityTable));
    }

    @Override
    public RiskType riskType() {
        return RiskType.LONGEVITY;
    }

    @Override
    public ContractType contractType() {
        return ContractType.FIXED_TERM;
    }

    @Override
    public Double calculate(CalculationContext context) {
        MortalityTable mortalityTable = mortalityTables.get(context.getMortalityTableType());

        return Math.pow(interestRate.getV(), context.getContractTerm()) *
                mortalityTable.getLx(context.getAge() + context.getContractTerm()) /
                mortalityTable.getLx(context.getAge());
    }
}
