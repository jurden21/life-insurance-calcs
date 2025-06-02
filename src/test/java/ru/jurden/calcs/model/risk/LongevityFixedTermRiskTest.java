package ru.jurden.calcs.model.risk;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
class LongevityFixedTermRiskTest {

    private static final double DELTA = 1e-10;

    private final MortalityTableParser parser = new MortalityTableParser();
    private final MortalityTableFactory factory = new MortalityTableFactory(parser);
    private LongevityFixedTermRisk risk;

    @Test
    void riskType() {
        InterestRate interestRate = new InterestRate(5);
        MortalityTable mortalityTable = factory.loadTable(MortalityTableType.MALE);
        risk = new LongevityFixedTermRisk(interestRate, List.of(mortalityTable));
        RiskType riskType = risk.riskType();
        assertEquals("LONGEVITY", riskType.name());
    }

    @Test
    void contractType() {
        InterestRate interestRate = new InterestRate(5);
        MortalityTable mortalityTable = factory.loadTable(MortalityTableType.MALE);
        risk = new LongevityFixedTermRisk(interestRate, List.of(mortalityTable));
        ContractType contractType = risk.contractType();
        assertEquals("FIXED_TERM", contractType.name());
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculate")
    void calculate(double percent, MortalityTableType mortalityTableType, int age, int contractTerm, double value) {
        InterestRate interestRate = new InterestRate(percent);
        MortalityTable mortalityTable = factory.loadTable(mortalityTableType);
        risk = new LongevityFixedTermRisk(interestRate, List.of(mortalityTable));
        CalculationContext context = new CalculationContext()
                .setAge(age)
                .setContractTerm(contractTerm)
                .setMortalityTableType(mortalityTableType);
        assertEquals(value, risk.calculate(context), DELTA);
    }

    static Stream<Arguments> provideDataToCalculate() {
        return Stream.of(
                Arguments.of(3.0, MortalityTableType.MALE, 40, 15, 0.5052885821),
                Arguments.of(3.0, MortalityTableType.MALE, 40, 20, 0.3739007726),
                Arguments.of(5.0, MortalityTableType.MALE, 40, 15, 0.3786677936),
                Arguments.of(5.0, MortalityTableType.MALE, 40, 20, 0.2545158746),
                Arguments.of(3.0, MortalityTableType.MALE, 50, 15, 0.4012371484),
                Arguments.of(3.0, MortalityTableType.MALE, 50, 20, 0.2613382461),
                Arguments.of(5.0, MortalityTableType.MALE, 50, 15, 0.3006907164),
                Arguments.of(5.0, MortalityTableType.MALE, 50, 20, 0.1778940755),
                Arguments.of(3.0, MortalityTableType.FEMALE, 40, 15, 0.5988631395),
                Arguments.of(3.0, MortalityTableType.FEMALE, 40, 20, 0.4925188534),
                Arguments.of(5.0, MortalityTableType.FEMALE, 40, 15, 0.4487934059),
                Arguments.of(5.0, MortalityTableType.FEMALE, 40, 20, 0.3352597158),
                Arguments.of(3.0, MortalityTableType.FEMALE, 50, 15, 0.5477453349),
                Arguments.of(3.0, MortalityTableType.FEMALE, 50, 20, 0.4145779943),
                Arguments.of(5.0, MortalityTableType.FEMALE, 50, 15, 0.4104852649),
                Arguments.of(5.0, MortalityTableType.FEMALE, 50, 20, 0.2822050355),
                Arguments.of(3.0, MortalityTableType.MIXED, 40, 15, 0.5811663364),
                Arguments.of(3.0, MortalityTableType.MIXED, 40, 20, 0.4671583411),
                Arguments.of(5.0, MortalityTableType.MIXED, 40, 15, 0.4355312630),
                Arguments.of(5.0, MortalityTableType.MIXED, 40, 20, 0.3179967052),
                Arguments.of(3.0, MortalityTableType.MIXED, 50, 15, 0.5171967642),
                Arguments.of(3.0, MortalityTableType.MIXED, 50, 20, 0.3830725330),
                Arguments.of(5.0, MortalityTableType.MIXED, 50, 15, 0.3875918921),
                Arguments.of(5.0, MortalityTableType.MIXED, 50, 20, 0.2607591316));
    }
}