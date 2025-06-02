package ru.jurden.calcs.model.risk;

import ru.jurden.calcs.model.CalculationContext;
import ru.jurden.calcs.enums.ContractType;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.enums.RiskType;
import ru.jurden.calcs.model.InterestRate;
import ru.jurden.calcs.model.MortalityTable;
import ru.jurden.calcs.model.Risk;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeathFixedTermRisk implements Risk {

    private final InterestRate interestRate;
    private final Map<MortalityTableType, MortalityTable> mortalityTables;

    public DeathFixedTermRisk(InterestRate interestRate, List<MortalityTable> mortalityTables) {
        this.interestRate = interestRate;
        this.mortalityTables = mortalityTables.stream()
                .collect(Collectors.toMap(MortalityTable::getTableType,
                        mortalityTable -> mortalityTable));
    }

    @Override
    public RiskType riskType() {
        return RiskType.DEATH;
    }

    @Override
    public ContractType contractType() {
        return ContractType.FIXED_TERM;
    }

    @Override
    public Double calculate(CalculationContext context) {
        MortalityTable mortalityTable = mortalityTables.get(context.getMortalityTableType());

        double result = 0;
        for (int i = context.getAge(); i < context.getAge() + context.getContractTerm(); i++) {
            result += Math.pow(interestRate.getV(), i + 1.0) * mortalityTable.getDx(i);
        }
        result = result / mortalityTable.getLx(context.getAge()) / Math.pow(interestRate.getV(), context.getAge());
        result = result * interestRate.getI() / interestRate.getB();
        return result;
    }
}
