package ru.jurden.calcs.model.risk;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.jurden.calcs.enums.ContractType;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.enums.RiskType;
import ru.jurden.calcs.model.CalculationContext;
import ru.jurden.calcs.model.InterestRate;
import ru.jurden.calcs.model.MortalityTable;
import ru.jurden.calcs.table.MortalityTableFactory;
import ru.jurden.calcs.table.MortalityTableParser;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MortalityOpenEndedRiskTest {

    private static final double DELTA = 1e-10;

    private final MortalityTableParser parser = new MortalityTableParser();
    private final MortalityTableFactory factory = new MortalityTableFactory(parser);
    private MortalityOpenEndedRisk risk;

    @Test
    void riskType() {
        InterestRate interestRate = new InterestRate(5);
        MortalityTable mortalityTable = factory.loadTable(MortalityTableType.MALE);
        risk = new MortalityOpenEndedRisk(interestRate, List.of(mortalityTable));
        RiskType riskType = risk.riskType();
        assertEquals("MORTALITY", riskType.name());
    }

    @Test
    void contractType() {
        InterestRate interestRate = new InterestRate(5);
        MortalityTable mortalityTable = factory.loadTable(MortalityTableType.MALE);
        risk = new MortalityOpenEndedRisk(interestRate, List.of(mortalityTable));
        ContractType contractType = risk.contractType();
        assertEquals("OPEN_ENDED", contractType.name());
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculate")
    void calculate(double percent, MortalityTableType mortalityTableType, int age, double value) {
        InterestRate interestRate = new InterestRate(percent);
        MortalityTable mortalityTable = factory.loadTable(mortalityTableType);
        risk = new MortalityOpenEndedRisk(interestRate, List.of(mortalityTable));
        CalculationContext context = new CalculationContext()
                .setAge(age)
                .setMortalityTableType(mortalityTableType);
        assertEquals(value, risk.calculate(context), DELTA);
    }

    static Stream<Arguments> provideDataToCalculate() {
        return Stream.of(
                Arguments.of(3.0, MortalityTableType.MALE, 40, 0.4895763275),
                Arguments.of(5.0, MortalityTableType.MALE, 40, 0.3321822628),
                Arguments.of(3.0, MortalityTableType.MALE, 50, 0.5895305369),
                Arguments.of(5.0, MortalityTableType.MALE, 50, 0.4406219594),
                Arguments.of(3.0, MortalityTableType.FEMALE, 40, 0.3707700000),
                Arguments.of(5.0, MortalityTableType.FEMALE, 40, 0.2104903450),
                Arguments.of(3.0, MortalityTableType.FEMALE, 50, 0.4738485963),
                Arguments.of(5.0, MortalityTableType.FEMALE, 50, 0.3084783949),
                Arguments.of(3.0, MortalityTableType.MIXED, 40, 0.3959137093),
                Arguments.of(5.0, MortalityTableType.MIXED, 40, 0.2357463379),
                Arguments.of(3.0, MortalityTableType.MIXED, 50, 0.4983452486),
                Arguments.of(5.0, MortalityTableType.MIXED, 50, 0.3361510741));
    }
}