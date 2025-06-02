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
class MortalityFixedTermRiskTest {

    private static final double DELTA = 1e-10;

    private final MortalityTableParser parser = new MortalityTableParser();
    private final MortalityTableFactory factory = new MortalityTableFactory(parser);
    private MortalityFixedTermRisk risk;

    @Test
    void riskType() {
        InterestRate interestRate = new InterestRate(5);
        MortalityTable mortalityTable = factory.loadTable(MortalityTableType.MALE);
        risk = new MortalityFixedTermRisk(interestRate, List.of(mortalityTable));
        RiskType riskType = risk.riskType();
        assertEquals("MORTALITY", riskType.name());
    }

    @Test
    void contractType() {
        InterestRate interestRate = new InterestRate(5);
        MortalityTable mortalityTable = factory.loadTable(MortalityTableType.MALE);
        risk = new MortalityFixedTermRisk(interestRate, List.of(mortalityTable));
        ContractType contractType = risk.contractType();
        assertEquals("FIXED_TERM", contractType.name());
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculate")
    void calculate(double percent, MortalityTableType mortalityTableType, int age, int contractTerm, double value) {
        InterestRate interestRate = new InterestRate(percent);
        MortalityTable mortalityTable = factory.loadTable(mortalityTableType);
        risk = new MortalityFixedTermRisk(interestRate, List.of(mortalityTable));
        CalculationContext context = new CalculationContext()
                .setAge(age)
                .setContractTerm(contractTerm)
                .setMortalityTableType(mortalityTableType);
        assertEquals(value, risk.calculate(context), DELTA);
    }

    static Stream<Arguments> provideDataToCalculate() {
        return Stream.of(
                Arguments.of(3.0, MortalityTableType.MALE, 40, 15, 0.1668703106),
                Arguments.of(3.0, MortalityTableType.MALE, 40, 20, 0.2335272010),
                Arguments.of(5.0, MortalityTableType.MALE, 40, 15, 0.1437087335),
                Arguments.of(5.0, MortalityTableType.MALE, 40, 20, 0.1913351662),
                Arguments.of(3.0, MortalityTableType.MALE, 50, 15, 0.2972096056),
                Arguments.of(3.0, MortalityTableType.MALE, 50, 20, 0.3885472346),
                Arguments.of(5.0, MortalityTableType.MALE, 50, 15, 0.2577321395),
                Arguments.of(5.0, MortalityTableType.MALE, 50, 20, 0.3230614463),
                Arguments.of(3.0, MortalityTableType.FEMALE, 40, 15, 0.0519944017),
                Arguments.of(3.0, MortalityTableType.FEMALE, 40, 20, 0.0777954249),
                Arguments.of(5.0, MortalityTableType.FEMALE, 40, 15, 0.0444599874),
                Arguments.of(5.0, MortalityTableType.FEMALE, 40, 20, 0.0628546396),
                Arguments.of(3.0, MortalityTableType.FEMALE, 50, 15, 0.1133395357),
                Arguments.of(3.0, MortalityTableType.FEMALE, 50, 20, 0.1754768636),
                Arguments.of(5.0, MortalityTableType.FEMALE, 50, 15, 0.0966895483),
                Arguments.of(5.0, MortalityTableType.FEMALE, 50, 20, 0.1410128511),
                Arguments.of(3.0, MortalityTableType.MIXED, 40, 15, 0.0735336338),
                Arguments.of(3.0, MortalityTableType.MIXED, 40, 20, 0.1102212455),
                Arguments.of(5.0, MortalityTableType.MIXED, 40, 15, 0.0629604070),
                Arguments.of(5.0, MortalityTableType.MIXED, 40, 20, 0.0891459440),
                Arguments.of(3.0, MortalityTableType.MIXED, 50, 15, 0.1515162957),
                Arguments.of(3.0, MortalityTableType.MIXED, 50, 20, 0.2192549030),
                Arguments.of(5.0, MortalityTableType.MIXED, 50, 15, 0.1300049165),
                Arguments.of(5.0, MortalityTableType.MIXED, 50, 20, 0.1783568707));
    }
}