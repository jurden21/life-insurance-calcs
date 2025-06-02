package ru.jurden.calcs.model;

import ru.jurden.calcs.enums.ContractType;
import ru.jurden.calcs.enums.RiskType;

public interface Risk {
    RiskType riskType();
    ContractType contractType();
    Double calculate(CalculationContext context);
}
