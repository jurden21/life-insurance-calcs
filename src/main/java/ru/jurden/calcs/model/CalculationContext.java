package ru.jurden.calcs.model;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.jurden.calcs.enums.MortalityTableType;

@Data
@Accessors(chain = true)
public class CalculationContext {
    int age;
    int contractTerm;
    MortalityTableType mortalityTableType;
}
